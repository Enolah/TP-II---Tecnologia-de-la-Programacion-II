package tp.p1.logic.object;


import tp.p1.logic.Game;
import tp.p1.logic.GameObject;
import tp.p1.logic.gameObject.Weapon;

public class UCMShipLaser extends Weapon {

	private String dibu= "oo";
	public static int num_disparos=0;
	public static int damage=1;
	
	
	
	public UCMShipLaser(Game game, int x, int y, int live) {
		super(game, x, y, live);
	}

	
	
	/*-------------------------------*/
				//METODOS
	
	
	@Override
	public void onDelete() {
		game.enableMissile();
	}

	@Override
	public void move() {
		if (!game.isOnBoard(x-1, y)){
			num_disparos=0;
			live=0;
		}
		else
			x-=1;
	}

	public boolean receiveBombAttack(int damage) {
		getDamage(damage);
		//...
		return false;
	}
	@Override
	protected boolean weaponAttack(GameObject other) {
		other.receiveMissileAttack(1); //...
		return false;
	}
	
	/*--------------------------------*/
			//PINTAR LASER	
	public String toString() {
		if(live==0){
			dibu=" ";
		}
		return dibu;
	}



	

  


}
