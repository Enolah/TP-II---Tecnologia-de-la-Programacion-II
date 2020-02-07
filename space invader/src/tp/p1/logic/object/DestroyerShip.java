package tp.p1.logic.object;

import tp.p1.logic.Game;
import tp.p1.logic.IExecuteRandomActions;
import tp.p1.logic.gameObject.AlienShip;

public class DestroyerShip extends AlienShip implements IExecuteRandomActions{

	private boolean canShootBomb=true;
	private String dibu = "D[%s]";
	private int points=10;

	
	public DestroyerShip(Game game, int x, int y, int live){
		super(game, x, y, live);
		this.points=10;
	}

	/*--------------------------------*/
	// get & set

	public int getPoints() {
		
		return points;
	}

	
	/*--------------------------------*/
	// PINTAR NAVE

	public String toString() {

		if (live== 0) {
			dibu = " ";
		}
		return String.format(dibu, live);
	}
	
	public void toStringList() {
		System.out.println("D[%s]: Harm: " + " - Shield: " + live);
	}

	//-------------------------------


	@Override
	public void onDelete() {
//		this.game.receivePoints(this.points);
//		AlienShip.remainingAliens--;
	}


	public boolean receiveShockWaveAttack(int damage) {
		getDamage(1);
		ShockWave.setlive(0);
		return true;
	}


	public void enableBomb() {
		canShootBomb=true;
	}


	

	@Override
	public void computerAction() {
		CTM = (int) (game.currentCycle * game.getLevel().getShootFrequency());
		if (CTM!=0 && canShootBomb){
			canGenerateRandomBomb(game);
		}
		
	}


	private void canGenerateRandomBomb(Game game) {
		game.addObject(new Bomb(game, x, y, 1));
		canShootBomb=false;
	}

	@Override
	public boolean receiveMissileAttack(int damage) {
		getDamage(damage);
		return true;
	}


	

}
