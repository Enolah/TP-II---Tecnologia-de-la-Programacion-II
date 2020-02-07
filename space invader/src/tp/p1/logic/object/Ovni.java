package tp.p1.logic.object;

import java.util.Random;

import javax.xml.crypto.dsig.SignatureMethod;

import tp.p1.logic.Game;
import tp.p1.logic.IExecuteRandomActions;
import tp.p1.logic.Level;
import tp.p1.logic.gameObject.EnemyShip;


public class Ovni extends EnemyShip implements IExecuteRandomActions{



	private static int num_ovni = 0;
	private static int max_ovni = 1;
	private String dibu = ":O:";
	private int points=25;
	
	private boolean enabled;


	public Ovni(Game game, int x, int y, int live){
		super(game, x, y, live);
	
	}
	/*--------------------------------*/
	// get & set

	public int getNum_ovni() {
		return num_ovni;
	}

	public void setNum_ovni(int num_ovni) {
		Ovni.num_ovni = num_ovni;
	}

	public int getPoints() {
		return points;
	}
	
	public boolean getEnabled(){
		return enabled;
	}

	
	/*-------------------------------*/
	// METODOS

	public boolean receiveShockWaveAttack(int damage) {return false;};
	public boolean receiveMissileAttack(int  damage) {
		num_ovni --;
		live = live-damage;
		return false;
		}
	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDelete() {
		//invoca al ondelete() de la superclase (para dar puntos)
		super.onDelete();
		//activan la posibilidad de lanzar onda de choque
		enabled=true;
	}
	
	
	@Override
	public void move() {
		
		if (num_ovni == max_ovni) {
			if (y != 0) {
				y--;
				boolean ok = true;
			} 
			else {
				num_ovni--;
				
			}
		}	
	}

	
	

	public static boolean aparecer(Level level, Random rand) {
		boolean aparecer = false;
		double num = 0;

		switch (level) {
		case EASY:
			num = 0.5;
			break;
		case HARD:
			num = 0.2;
			break;
		case INSANE:
			num = 0.1;
			break;
		}

		if (num_ovni < max_ovni) {
			if (num * 10 >= rand.nextInt(10) + 1) {
				num_ovni++;
				aparecer = true;
			}
		}

		return aparecer;
	}


	public void recibirWave(int daño) {
		if (num_ovni == max_ovni) {
			
		}

	}

	public void recibirDisparo(int daño) {
		num_ovni --;
		live = live-daño;
		
	}

	/*--------------------------------*/
	// PINTAR OVNI

	public String toString() {
		if (live == 0) {
			dibu = " ";
		}
		return String.format(dibu, live);
	}

	public void toStringList() {
		if (num_ovni == max_ovni)
			System.out.println("[O]vni: Points: " + points + " - Shield: " + live);
	}
	



}
