package tp.p1.logic.object;

import tp.p1.logic.Game;
import tp.p1.logic.gameObject.AlienShip;

public class RegularShip extends AlienShip{


	private String dibu = "R[%s]";
	private int points=5;

	
	public RegularShip(Game game, int x, int y, int live){
		super(game, x, y, live);
		
	}
	/*--------------------------------*/
	// get & set

	public int getPoints() {
		return points;
	}


	/*-------------------------------*/
			//PINTAR NAVE
	
	public String toString(){
		if (live==0){
			dibu = " ";
		}
		return String.format(dibu, live);
	}

	public void toStringList() {
		System.out.println("R[%s]: Harm: " +" - Shield: " + live);
	}

	//------------------------------


	@Override
	public void onDelete() {
//		this.game.receivePoints(this.points);
//		AlienShip.remainingAliens--;
	}
	@Override
	public void computerAction() {}


	public boolean receiveShockWaveAttack(int damage) {
		getDamage(1);
		ShockWave.setlive(0);
		return true;
		}
	public boolean receiveMissileAttack(int  damage) {

		getDamage(damage);
		return true;
	
	}


	



	

}
