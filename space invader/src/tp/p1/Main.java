package tp.p1;

import java.util.Random;
import java.util.Scanner;

import tp.p1.control.Controller;
import tp.p1.logic.Game;
import tp.p1.logic.Level;

public class Main {
	private static Random rand;
	public static void main(String[] args) {
		
		int seed = new Random().nextInt(1000);
		
		if (args.length == 1) { 
			Level nivel = Level.valueOf(args[0]);
			rand = new Random (seed);
			Game juego= new Game (nivel, rand); 
			Scanner in = new Scanner (System.in); 
			Controller c = new Controller (juego, in); 
			c.run(); 
			
        } else if (args.length == 2){
        	Level nivel = Level.valueOf(args[0]);
        	seed = Integer.parseInt(args[1]);
			rand = new Random (seed);
			Game juego= new Game (nivel, rand);
			Scanner in = new Scanner (System.in);
			Controller c = new Controller (juego, in);
			c.run();
			
        } else if (args.length == 0) {      
        	System.out.println("No hay argumentos");
        }
        else {
        	System.out.println("Demasiados argumentos");
        }
		

	}

}
