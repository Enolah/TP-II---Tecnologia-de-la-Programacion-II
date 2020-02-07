package tp.p1.logic.object;

import tp.p1.logic.Game;
import tp.p1.logic.GameObject;
import tp.p1.logic.gameObject.Weapon;

public class ShockWave extends Weapon{

	public ShockWave(Game game, int x, int y, int live) {
		super(game, x, y, live);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		if(!game.isOnBoard(x, y))
			live=0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	protected boolean weaponAttack(GameObject other) {
		other.receiveShockWaveAttack(1); //...
		return false;
	}
	
	public boolean performAttack(GameObject other){
		return false;
		//es necesario hacer que la onda de choque pierda 1 de su vidda cada
		//vez que una llamada weaponAttack() tenga exito.
	}



	public static void setlive(int i) {
		live=i;
		
	}


}
