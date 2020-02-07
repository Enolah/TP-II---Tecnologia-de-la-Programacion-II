package tp.p1.logic.gameObject;

import tp.p1.logic.Game;
import tp.p1.logic.GameObject;
import tp.p1.logic.Move;

public abstract class Weapon extends GameObject {

	protected static int damage;
	protected static Move dir;
	public Weapon(Game game, int x, int y, int live) {
		super(game, x, y, live);
		Weapon.damage=1;
	}
	@Override
	public void computerAction() {}
	
	public boolean performAttack(GameObject other) {
		if(live!=0 && (other.isAlive()&& other.isOnPosition(x, y))){
			this.weaponAttack(other);
			return true;
		}
		
		return false;
	}
	
	protected abstract boolean weaponAttack(GameObject other);
}
