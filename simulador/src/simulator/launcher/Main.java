package simulator.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
import simulator.factories.NewRoadEventBuilder;
import simulator.factories.NewVehicleEventBuilder;
import simulator.factories.RoundRobinStrategyBuilder;
import simulator.factories.SetContClassEventBuilder;
import simulator.factories.SetWeatherEventBuilder;
import simulator.misc.SortedArrayList;
import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LigthSwitchingStrategy;
import simulator.model.Road.MiCompi;
import simulator.model.RoadMap;
import simulator.model.TrafficSimulator;
import simulator.model.Vehicle;

public class Main {

	private int posJ = -1;

	private final static Integer _timeLimitDefaultValue = 10;
	private static String _inFile = null; //fichero de entrada
	private static Integer _timeLimit = null;//numnero de pasos
	private static String _outFile = null; //fichero de salida
	private static Factory<Event> _eventsFactory = null;
	
	
	
	
	
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
		if (_inFile == null) {
			throw new ParseException("An events file is missing");
		}
	}

	private static void parseOutFileOption(CommandLine line) throws ParseException {
		_outFile = line.getOptionValue("o");
	}
	
	private static void parseTickOption(CommandLine line) { //indica el numero de ticks que necesita el simulador

		String s=line.getOptionValue("t");
		_timeLimit = Integer.valueOf(s);
		if (_timeLimit == null)
			_timeLimit = 10;

	}

	private static void initFactories() {

		// TODO complete this method to initialize _eventsFactory
		//se crean las estrategias de cambio de semaforo
		ArrayList<Builder<LigthSwitchingStrategy>> lsbs = new ArrayList<>();
		lsbs.add(new RoundRobinStrategyBuilder("round_robin_lss"));
		lsbs.add(new MostCrowdedStrategyBuilder("most_crowded_lss"));
		Factory<LigthSwitchingStrategy> lssFactory = new BuilderBasedFactory<>(lsbs);
		
		//se crean las estrategias de extraccion de la cola
		ArrayList<Builder<DequeuingStrategy>> dqbs = new ArrayList<>();
		dqbs.add(new MoveFirstStrategyBuilder("move_first_dqs"));
		dqbs.add(new MoveAllStrategyBuilder("most_all_dqs"));
		Factory<DequeuingStrategy> dqsFactory = new BuilderBasedFactory<>(dqbs);
		
		//se crea la lista de builders
		 List<Builder<Event>> eventBuilders = new ArrayList<>();
		
		eventBuilders.add(new NewJunctionEventBuilder("new_junction",lssFactory, dqsFactory));
		eventBuilders.add(new NewCityRoadEventBuilder("new_city_road"));
		eventBuilders.add(new NewInterCityRoadEventBuilder("new_inter_city_road"));
		eventBuilders.add(new NewVehicleEventBuilder("new_vehicle"));
		eventBuilders.add(new SetContClassEventBuilder("set_cont_class"));
		eventBuilders.add(new SetWeatherEventBuilder("set_weather"));

		
		_eventsFactory = new BuilderBasedFactory<>(eventBuilders);

	}

	private static void startBatchMode() throws IOException {
		// TODO complete this method to start the simulation
		InputStream in = new FileInputStream(new File(_inFile));
		OutputStream out = _outFile == null ?
		System.out : new FileOutputStream(new File(_outFile));
		TrafficSimulator sim = new TrafficSimulator();
		Controller ctrl = new Controller(sim, _eventsFactory);
		ctrl.loadEvents(in);
		ctrl.run(_timeLimit, out);
		in.close();
		System.out.println("Done!");
		
	}

	private static void start(String[] args) throws IOException {
		initFactories(); 
		parseArgs(args); //procesa los argumentos de entrada
		startBatchMode(); //inicia la simulacion con E/S estandar
	}

	// example command lines:
	//
	// -i resources/examples/ex1.json
	// -i resources/examples/ex1.json -t 300
	// -i resources/examples/ex1.json -o resources/tmp/ex1.out.json
	// --help

	public static void main(String[] args) {
		try {
			start(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
	}

}
