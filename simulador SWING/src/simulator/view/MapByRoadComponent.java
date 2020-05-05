package simulator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;
import simulator.model.VehicleStatus;
import simulator.model.Weather;

public class MapByRoadComponent extends JComponent implements TrafficSimObserver{

	private static final long serialVersionUID = 1L;

	private static final int _JRADIUS = 10;

	private static final Color _BG_COLOR = Color.WHITE;
	private static final Color _JUNCTION_COLOR = Color.BLUE;
	private static final Color _JUNCTION_LABEL_COLOR = new Color(200, 100, 0);
	private static final Color _GREEN_LIGHT_COLOR = Color.GREEN;
	private static final Color _RED_LIGHT_COLOR = Color.RED;

	private RoadMap _map;

	private Image _car;

	MapByRoadComponent(Controller ctrl) {
		setPreferredSize(new Dimension(300,200)); //comprobar esto
		initGUI();
		ctrl.addObserver(this);
	}

	private void initGUI() {
		_car = loadImage("car.png");
	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// clear with a background color
		g.setColor(_BG_COLOR);
		g.clearRect(0, 0, getWidth(), getHeight());

		if (_map == null || _map.getJunctions().size() == 0) {
			g.setColor(Color.red);
			g.drawString("No map yet!", getWidth() / 2 - 50, getHeight() / 2);
		} else {
			updatePrefferedSize(); //mirar esto si sale bien
			drawMap(g);
		}
	}

	private void drawMap(Graphics g) {
		drawRoads(g);
	}

	private void drawRoads(Graphics g) {
		int i =0;
		for (Road r : _map.getRoads()) {

			// the road goes from (x1,y) to (x2,y)
			int x1 = 50;
			int x2 =getWidth()-100;
			int y= (i+1)*50;
			
			
			// choose a color for the road depending on the total contamination, the darker
			// the
			// more contaminated (wrt its co2 limit)
			int roadColorValue = 200 - (int) (200.0 * Math.min(1.0, (double) r.getTotalPollution() / (1.0 + (double) r.getContLimit())));
			Color roadColor = new Color(roadColorValue, roadColorValue, roadColorValue);

			// draw line from (x1,y1) to (x2,y2) with arrow of color arrowColor and line of
			// color roadColor. The size of the arrow is 15px length and 5 px width
			g.setColor(roadColor);
			g.drawLine(x1, y, x2, y);
			
			//dibujar dos circulos de extremo de la carretera
			//cruce origen
			g.setColor(_JUNCTION_COLOR);
			g.fillOval(x1 - _JRADIUS / 2, y - _JRADIUS / 2, _JRADIUS, _JRADIUS);
			
			//cruce destino
			//color cruce destino dependiendo del semaforo
			int idx = r.getDest().getGreenLightIndex();
			if (idx != -1 && r.equals(r.getDest().getInRoads().get(idx))) 
				g.setColor(_GREEN_LIGHT_COLOR);
			else 
				g.setColor(_RED_LIGHT_COLOR);
			g.fillOval(x2 - _JRADIUS / 2, y - _JRADIUS / 2, _JRADIUS, _JRADIUS);
			
			//3.Dibuja los vehículos utilizando la imagen car.png
			drawVehicles(g,x1, x2,y,y,r);
			
			//4.Dibujamos el identificador de la carretera a la izq
			g.setColor(Color.BLACK);
			g.drawString(r.getId(), x1, y);
			
		
			//5.Dibuja una imagen de 32x32 para las condiciones climatológicas de la carretera
			drawWeather(g,x2,y,r);
			// hacer un switch con cada caso del tiempo y cargar la imagen
			//despues dibujarla con g.drawImage
			
		
			//6.Dibuja una imagen de 32x32 para el nivel de contaminación
			drawContClass(g,x2,y,r);
			//co2(grafic, int x ....)
			//mirar apuntes
			
			
			i++;
		}

	}
	
	private void drawWeather(Graphics g, int x, int y, Road r){
		
		Image img= null;
		Weather w=r.getWeather();
		
		switch (w) {
		case SUNNY:
			img= loadImage("sun.png");
			break;
		case CLOUDY:
			img= loadImage("cloud.png");		
			break;
		case RAINY:
			img= loadImage("rain.png");	
			break;
		case WINDY:
			img= loadImage("wind.png");	
			break;
		case STORM:
			img= loadImage("storm.png");
			break;
		}
		g.drawImage(img, x, y, 32, 32, this);
	}

	private void drawContClass(Graphics g, int x, int y, Road r){
		
		Image img= null;
		int C=( int ) Math.floor(Math.min(( double ) r.getTotalPollution()/(1.0 + ( double ) r.getContLimit()),1.0) / 0.19);
		switch(C){
		case 0:
			img= loadImage("cont_0");
			break;
		case 1:
			img= loadImage("cont_1");
			break;
		case 2:
			img= loadImage("cont_2");
			break;
		case 3:
			img= loadImage("cont_3");
			break;
		case 4:
			img= loadImage("cont_4");
			break;
		case 5:
			img= loadImage("cont_5");
			break;
		}
		g.drawImage(img, x, y, 32, 32, this);
	}
	
	
	private void drawVehicles(Graphics g, int x1, int x2, int y1, int y2, Road r_actual) {
		for (Vehicle v : _map.getVehicles()) {
			if (v.getStatus() != VehicleStatus.ARRIVED) {

				// The calculation below compute the coordinate (vX,vY) of the vehicle on the
				// corresponding road. It is calculated relativly to the length of the road, and
				// the location on the vehicle.
				Road r_vehicle = v.getRoad(); //es la carreetera de mi vehiculo
				if (r_vehicle == r_actual){} //es la carretera que estoy pintando
												//si es igual a la del vehiculo, pinto el coche
				double roadLength = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
				double alpha = Math.atan(((double) Math.abs(x1 - x2)) / ((double) Math.abs(y1 - y2)));
				double relLoc = roadLength * ((double) v.getLocation()) / ((double) r_vehicle.getLength());
				int x = x1 + ( int ) ((x2 - x1) * (( double ) v.getLocation() / ( double ) r_vehicle.getLength()));
				int y = x1 + ( int ) ((y2 - y1) * (( double ) v.getLocation() / ( double ) r_vehicle.getLength()));
				int xDir = x1 < x2 ? 1 : -1;
				int yDir = y < y ? 1 : -1;

				int vX = x1 + xDir * ((int) x);
				int vY = y + yDir * ((int) y);

				// Choose a color for the vehcile's label and background, depending on its
				// contamination class
				int vLabelColor = (int) (25.0 * (10.0 - (double) v.getContClass()));
				g.setColor(new Color(0, vLabelColor, 0));

				// draw an image of a car (with circle as background) and it identifier
				g.fillOval(vX - 1, vY - 6, 14, 14);
				g.drawImage(_car, vX, vY - 6, 16, 16, this);
				g.drawString(v.getId(), vX, vY - 6);
			}
		}
	}

	
	private void drawJunctions(Graphics g) {
		for (Junction j : _map.getJunctions()) {

			// (x,y) are the coordinates of the junction
			int x = j.getX();
			int y = j.getY();

			// draw a circle with center at (x,y) with radius _JRADIUS
			g.setColor(_JUNCTION_COLOR);
			g.fillOval(x - _JRADIUS / 2, y - _JRADIUS / 2, _JRADIUS, _JRADIUS);
			
			// draw the junction's identifier at (x,y)
			g.setColor(_JUNCTION_LABEL_COLOR);
			g.drawString(j.getId(), x, y);
		}
	}

	// this method is used to update the preffered and actual size of the component,
	// so when we draw outside the visible area the scrollbars show up
	private void updatePrefferedSize() {
		int maxW = 200;
		int maxH = 200;
		for (Junction j : _map.getJunctions()) {
			maxW = Math.max(maxW, j.getX());
			maxH = Math.max(maxH, j.getY());
		}
		maxW += 20;
		maxH += 20;
		setPreferredSize(new Dimension(maxW, maxH));
		setSize(new Dimension(maxW, maxH));
	}

	// This method draws a line from (x1,y1) to (x2,y2) with an arrow.
	// The arrow is of height h and width w.
	// The last two arguments are the colors of the arrow and the line
//	private void drawLineWithArrow(//
//			Graphics g, //
//			int x1, int y, //
//			int x2, //
//			int w, int h, //
//			Color lineColor, Color arrowColor) {
//
//		int dx = x2 - x1, dy = y - y;
//		double D = Math.sqrt(dx * dx + dy * dy);
//		double xm = D - w, xn = xm, ym = h, yn = -h, x;
//		double sin = dy / D, cos = dx / D;
//
//		x = xm * cos - ym * sin + x1;
//		ym = xm * sin + ym * cos + y;
//		xm = x;
//
//		x = xn * cos - yn * sin + x1;
//		yn = xn * sin + yn * cos + y;
//		xn = x;
//
//		int[] xpoints = { x2, (int) xm, (int) xn };
//		int[] ypoints = { y, (int) ym, (int) yn };
//
//		g.setColor(lineColor);
//		g.drawLine(x1, y, x2, y);
//		g.setColor(arrowColor);
//		g.fillPolygon(xpoints, ypoints, 3);
//	}

	// loads an image from a file
	private Image loadImage(String img) {
		Image i = null;
		try {
			return ImageIO.read(new File("resources/icons/" + img));
		} catch (IOException e) {
		}
		return i;
	}

	public void update(RoadMap map) {
		_map = map;
		repaint();
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		update(map);
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		update(map);
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		update(map);
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		update(map);
	}

	@Override
	public void onError(String err) {
	}
}
