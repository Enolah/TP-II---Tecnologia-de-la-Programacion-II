package tp.p1.logic;

import java.util.Random;

import tp.p1.logic.gameObject.AlienShip;
import tp.p1.logic.object.DestroyerShip;
import tp.p1.logic.object.Ovni;
import tp.p1.logic.object.RegularShip;
import tp.p1.logic.object.UCMShip;
import tp.p1.logic.object.UCMShipLaser;



public class Game implements IPlayerController{

	public final static int DIM_X = 9;
	public final static int DIM_Y = 8;

	public int currentCycle;
	private Random rand;
	private Level level;

	GameObjectBoard board;

	private UCMShip player;
	
	private boolean doExit;
	private BoardInitializer initializer;
	private GamePrinter gp;
	
	public Game (Level level, Random random){
		this.rand = random;
		this.level = level;
		initializer = new BoardInitializer();
		initGame();
	}
	
	public void initGame () {
		//TODO poner a cero AlienShip.remainingAliens 
		currentCycle = 0;
		board = initializer.initialize(this, level);
		this.gp = new GamePrinter(this, DIM_X, DIM_Y);
		player = new UCMShip(this, DIM_Y - 1 ,DIM_X / 2,3);
		board.add(player);
	}

	public Random getRandom() {
		return rand;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void reset() {
		initGame();
	}
	
	public void addObject(GameObject object) {
		board.add(object);
	}
	
	public String positionToString(int x, int y) {
		return board.toString(x, y);
	}

	public boolean isFinished() {
		return playerWin() || aliensWin() || doExit;
	}
	
	public boolean aliensWin() {
		return !player.isAlive() || AlienShip.haveLanded();
	}
	
	private boolean playerWin () {
		return AlienShip.allDead();
	}
	
	public void update() {//none
		board.computerAction();
		board.update();
		currentCycle += 1;
	}
	
	public boolean isOnBoard(int x, int y) {
		return x >= 0 && y >= 0 && x < DIM_Y && y < DIM_X;
	}
	
	public void exit() {
		doExit = true;
	}
	
	public String infoToString() { //draw
		return "Cycles: " + currentCycle + "\n" +
			player.stateToString() + "\n" +
			"Remaining aliens: " + (AlienShip.getRemainingAliens()) + "\n"+
			"ShockWave: " + "\n"+
			"Points: " +
			"\n"+ gp.toString(); 
	
	}
	
	public String getWinnerMessage () {
		if (playerWin()) return "Player win!";
		else if (aliensWin()) return "Aliens win!";
		else if (doExit) return "Player exits the game";
		else return "This should not happen";
	}
	
	// TODO implementar los métodos del interfaz IPlayerController
	//acciones jugador
	
	/***MOVE***/
	
	@Override
	public boolean move(int numCells) {
//		boolean ok= true;
//		if (!player.move(numCells)){
//			System.out.println("No se puede mover la nave en esa direccion.");
//			ok= false;
//		}
		 return player.move(numCells);
	}

	/***SHOOT**/
	public boolean shootLaser() {
		enableMissile();
		player.shootLaser();
	
		return true;
	}

	
	/***SHOOckWAVE***/
	public boolean shockWave() {
		boolean ok = false;
		player.executeShockwave();
		player.disableShockWave();
		
//		if (player.getWave() == true) {
//			ok = true;
//			player.setWave(false);
//			regular.recibirWave(1);
//			destroyer.recibirWave(1);
//			if (ovni != null){
//				cont_ovni++;
//				ovni=null;
//				player.setWave(true);
//			}
//		}
		return ok;
	}

	//callback
	
	public void receivePoints(int points){
		player.getPoints();
	}
	public void enableShockWave(){
		 player.enableShockWave();
	}
	public void enableMissile(){
		player.enableLaser();
	}

	

}
