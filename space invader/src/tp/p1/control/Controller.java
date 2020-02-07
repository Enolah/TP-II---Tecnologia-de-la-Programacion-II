package tp.p1.control;

import java.util.Scanner;

import tp.p1.command.Command;
import tp.p1.command.CommandGenerator;
import tp.p1.logic.Game;

public class Controller {

	private Game game;
	private Scanner in;
	private String PROMPT = "Command >> ";
	private String unknownCommandMsg = "Comando no valido.";

	public Controller(Game juego, Scanner in) {
		this.game = juego;
		this.in = in;
	}

	public void run() {

		System.out.println(game.infoToString());
		while (!game.isFinished()) {
			System.out.println(PROMPT);
			String[] words = in.nextLine().toLowerCase().trim().split(" ");
			Command command = CommandGenerator.parseCommand(words);
			if (command != null) {
				if (command.execute(game)){
					game.update();
					System.out.println(game.infoToString());
				}
				
			} else {
				System.out.format(unknownCommandMsg);
			}
		}

	}
}
