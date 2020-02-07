package tp.p1.logic.object;

import tp.p1.logic.Game;
import tp.p1.logic.gameObject.AlienShip;
import tp.p1.logic.gameObject.Ship;

public class UCMShip extends Ship {

	private String dibu = "^[%s]^";
	
	static boolean hasShockWave, canShootLaser;

	public UCMShip(Game game, int x, int y, int live) {
		super(game, x, y, live);
	}

	/*-------------------------------*/
	// METODOS

	
	public boolean move(int cells) {
		boolean ok= true;
		if (!game.isOnBoard(x, y+cells)){
			System.out.println("No se puede mover la nave en esa direccion.");
			ok=false;
		}
		else
			y+=cells;
		return ok;
	}
	
	public void executeShockwave() {
		if (!hasShockWave)
			System.out.println("No tienes wave");
		else
			game.addObject(new ShockWave (game, x, y, AlienShip.getRemainingAliens()));
	
	}
	
	
	public void enableShockWave() {
		hasShockWave=true;	
	}
	public void disableShockWave() {
		hasShockWave=false;
		
	}



	public void shootLaser() {
		if (canShootLaser)
			game.addObject(new UCMShipLaser(game, x, y, 1));
		else
			System.out.println("Ya hay un laser en juego");
	}
	
	public void enableLaser() {
//		if (UCMShipLaser.num_disparos==0){
//			UCMShipLaser.num_disparos++;
//			canShootLaser=true;
//		}
//		else
//			canShootLaser=false;
		
		canShootLaser=true;
	}
	
	public boolean receiveBombAttack(int damage) {
		getDamage(damage);//...
		return false;
	}


	public void getPoints() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	@Override
	public void move() {}
	@Override
	public void computerAction() {}
	@Override
	public void onDelete() {}
	

	
	/*--------------------------------*/
	// PINTAR NAVE

	public String toString() {
		if (live == 0) {
			dibu = "x_x";
		}
		return String.format(dibu, live);
	}

	public void toStringList() {
		System.out.println("^_^: Harm: " + UCMShipLaser.damage + " - Shield: " + live);
	}
	
	
	public String stateToString() {
		return "^_^: Live: " + live;
	}



	

	

}
