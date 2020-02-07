package tp.p1.logic.object;

import tp.p1.logic.Game;
import tp.p1.logic.GameObject;
import tp.p1.logic.gameObject.Weapon;

public class Bomb extends Weapon {

	String dibu = "*";
	DestroyerShip destroyer;
	public Bomb(Game game, int x, int y, int live) {
		super(game, x, y, live);
	
	} 
	
	/*-------------------------------*/
	// METODOS

	@Override
	public void onDelete() {
		destroyer.enableBomb();
	}

	@Override
	public void move() {
	
		if (!game.isOnBoard(x+1, y)){
			live=0;
		}
		else
			x+=1;
	}
	public boolean receiveMissileAttack(int  damage) {
		getDamage(damage);
		return false;
	}
	
	protected boolean weaponAttack(GameObject other) {
		other.receiveBombAttack(1);
		return false;
	}


	/*--------------------------------*/
	// PINTAR LASER
	public String toString() {
		return dibu;
	}

	



}
