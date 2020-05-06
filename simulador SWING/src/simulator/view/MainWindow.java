package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import extra.jtable.EventsTableModel;
import extra.jtable.JunctionsTableModel;
import extra.jtable.RoadsTableModel;
import extra.jtable.VehiclesTableModel;
import simulator.control.Controller;

public class MainWindow extends JFrame {


	private static final long serialVersionUID = 1L;
	private Controller _ctrl;

	public MainWindow(Controller ctrl) {
		super("Traffic Simulator");
		_ctrl = ctrl;
		initGUI();
	}
	

	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		mainPanel.add(new ControlPanel(_ctrl), BorderLayout.PAGE_START);
		mainPanel.add(new StatusBar(_ctrl), BorderLayout.PAGE_END);
		JPanel viewsPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(viewsPanel, BorderLayout.CENTER);
		JPanel tablesPanel = new JPanel();
		tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.Y_AXIS));
		viewsPanel.add(tablesPanel);
		JPanel mapsPanel = new JPanel();
		mapsPanel.setLayout(new BoxLayout(mapsPanel, BoxLayout.Y_AXIS));
		viewsPanel.add(mapsPanel);
		// tables
		JPanel eventsView = createViewPanel(new JTable(new EventsTableModel(_ctrl)), "Events");
		eventsView.setPreferredSize(new Dimension(500, 200));
		tablesPanel.add(eventsView);
		
		// TODO add other tables
		JPanel vehiclesView = createViewPanel(new JTable(new VehiclesTableModel()), "Vehicles");
		JPanel roadsView = createViewPanel(new JTable(new RoadsTableModel()), "Roads");
		JPanel junctionsView = createViewPanel(new JTable(new JunctionsTableModel()), "Junctions");
		tablesPanel.add(vehiclesView);
		tablesPanel.add(roadsView);
		tablesPanel.add(junctionsView);
		// ...
		
		// AÑADIR MAPAS
		//maps
		
		JPanel mapView = createViewPanel(new MapComponent(_ctrl), "Map");
		mapView.setPreferredSize(new Dimension(500, 400));
		mapsPanel.add(mapView);
	
		//maps by road
		JPanel byRoadView = createViewPanel(new MapByRoadComponent(_ctrl), "ByRoad");
		mapView.setPreferredSize(new Dimension(500, 400));
		mapsPanel.add(byRoadView);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	private JPanel createViewPanel(JComponent c, String title) {
		JPanel p = new JPanel(new BorderLayout());
		//add a framed border to p with title
		TitledBorder tit = BorderFactory.createTitledBorder(title);
		c.setBorder(tit);
		p.add(new JScrollPane(c));
		return p;
	}
	
}
