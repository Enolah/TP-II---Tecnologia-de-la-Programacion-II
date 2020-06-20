package simulator.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import simulator.control.Controller;
import simulator.factories.Builder;
import simulator.factories.BuilderBasedFactory;
import simulator.factories.Factory;
import simulator.factories.MostCrowdedStrategyBuilder;
import simulator.factories.MoveAllStrategyBuilder;
import simulator.factories.MoveFirstStrategyBuilder;
import simulator.factories.NewCityRoadEventBuilder;
import simulator.factories.NewInterCityRoadEventBuilder;
import simulator.factories.NewJunctionEventBuilder;
import simulator.factories.NewVehicleEventBuilder;
import simulator.factories.RoundRobinStrategyBuilder;
import simulator.factories.SetContClassEventBuilder;
import simulator.factories.SetWeatherEventBuilder;
import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LigthSwitchingStrategy;
import simulator.model.TrafficSimulator;
import simulator.view.MainWindow;

public class Main {
	
	//esto es solo una prueba
	

	private final static Integer _timeLimitDefaultValue = 10;
	private static String _inFile = null; // fichero de entrada
	private static Integer _timeLimit = null;// numnero de pasos
	private static String _outFile = null; // fichero de salida
	private static Factory<Event> _eventsFactory = null;
	private final static String _modeDefaultValue = "gui";
	private static String _model = "";

	private static void parseArgs(String[] args) {

		// define the valid command line options
		//
		Options cmdLineOptions = buildOptions();

		// parse the command line as provided in args
		//
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseHelpOption(line, cmdLineOptions);
			parseModeOption(line);
			parseInFileOption(line);
			parseOutFileOption(line);
			parseTickOption(line);

			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			//
			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}

	private static Options buildOptions() {
		Options cmdLineOptions = new Options();

		cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Events input file").build());
		cmdLineOptions.addOption(
				Option.builder("o").longOpt("output").hasArg().desc("Output file, where reports are written.").build());
		cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message").build());
		cmdLineOptions.addOption(Option.builder("t").longOpt("tick").hasArg().build());
		cmdLineOptions.addOption(Option.builder("m").longOpt("mode").hasArg().build());

		return cmdLineOptions;
	}

	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	private static void parseInFileOption(CommandLine line) throws ParseException {

		_inFile = line.getOptionValue("i");
		if (_inFile == null && _model == "console") {
			throw new ParseException("An events file is missing");
		}
	}

	private static void parseOutFileOption(CommandLine line) throws ParseException {
		_outFile = line.getOptionValue("o");
		if (_outFile == null && _model == "console") {
			throw new ParseException("An events file is missing");
		}
	}

	private static void parseTickOption(CommandLine line) { // indica el numero de ticks que necesita el simulador

		String s = line.getOptionValue("t");
		if (s != null) {
			_timeLimit = Integer.valueOf(s);
			if (_timeLimit == null)
				_timeLimit = _timeLimitDefaultValue;
		} else
			_timeLimit = _timeLimitDefaultValue;

	}

	private static void parseModeOption(CommandLine line) { // indica el modo en el que se ejecuta el TS

		String s = line.getOptionValue("m");
		_model = s;
		if (_model == null) {
			_model = _modeDefaultValue;
		}
	}

	private static void initFactories() {

		// se crean las estrategias de cambio de semaforo
		ArrayList<Builder<LigthSwitchingStrategy>> lsbs = new ArrayList<>();
		lsbs.add(new RoundRobinStrategyBuilder("round_robin_lss"));
		lsbs.add(new MostCrowdedStrategyBuilder("most_crowded_lss"));
		Factory<LigthSwitchingStrategy> lssFactory = new BuilderBasedFactory<>(lsbs);

		// se crean las estrategias de extraccion de la cola
		ArrayList<Builder<DequeuingStrategy>> dqbs = new ArrayList<>();
		dqbs.add(new MoveFirstStrategyBuilder("move_first_dqs"));
		dqbs.add(new MoveAllStrategyBuilder("most_all_dqs"));
		Factory<DequeuingStrategy> dqsFactory = new BuilderBasedFactory<>(dqbs);

		// se crea la lista de builders
		List<Builder<Event>> eventBuilders = new ArrayList<>();

		eventBuilders.add(new NewJunctionEventBuilder("new_junction", lssFactory, dqsFactory));
		eventBuilders.add(new NewCityRoadEventBuilder("new_city_road"));
		eventBuilders.add(new NewInterCityRoadEventBuilder("new_inter_city_road"));
		eventBuilders.add(new NewVehicleEventBuilder("new_vehicle"));
		eventBuilders.add(new SetContClassEventBuilder("set_cont_class"));
		eventBuilders.add(new SetWeatherEventBuilder("set_weather"));

		_eventsFactory = new BuilderBasedFactory<>(eventBuilders);

	}

	private static void startBatchMode() throws IOException {

		InputStream in = new FileInputStream(new File(_inFile));
		OutputStream out = _outFile == null ? System.out : new FileOutputStream(new File(_outFile));
		TrafficSimulator sim = new TrafficSimulator();
		Controller ctrl = new Controller(sim, _eventsFactory);
		ctrl.loadEvents(in);
		ctrl.run(_timeLimit, out);
		in.close();
		System.out.println("Done!");

	}

	private static void startGUIMode() throws IOException {

		TrafficSimulator sim = new TrafficSimulator();
		Controller ctrl = new Controller(sim, _eventsFactory);
		ctrl.setNumPasos(_timeLimit);
		if (_inFile != null) { 
			InputStream in = new FileInputStream(new File(_inFile));
			ctrl.loadEvents(in);
			in.close(); 
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow(ctrl);
			}
		});

	}

	private static void start(String[] args) throws IOException {
		initFactories();
		parseArgs(args); // procesa los argumentos de entrada

		switch (_model) {
		case "gui":
			startGUIMode();
			break;
		case "console":
			startBatchMode(); // inicia la simulacion con E/S estandar
			break;
		default:
			startGUIMode();
		}
		

	}

	// example command lines:
	//
	// -i resources/examples/ex1.json
	// -i resources/examples/ex1.json -t 300
	// -i resources/examples/ex1.json -o resources/examples/ex1.out.json
	// -m console -i resources/examples/ex1.json -t 300
	// -m gui
	// --help

	public static void main(String[] args) {
		try {
			start(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
