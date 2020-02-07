package tp.p1.logic.gameObject;

import tp.p1.logic.Game;
import tp.p1.logic.Move;

public abstract class EnemyShip extends Ship{
	protected static int points;
	protected static Move dir;
	
	
	public EnemyShip(Game game, int x, int y, int live) {
		super(game, x, y, live);
		
	}
	@Override
	public void computerAction() {
		move();
		
	}
	
	public abstract int getPoints();
	@Override
	public void onDelete() {
		game.receivePoints(getPoints());
	}
	
	public abstract boolean receiveMissileAttack(int damage);
}
