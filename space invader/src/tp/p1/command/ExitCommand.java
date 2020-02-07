package tp.p1.command;

import tp.p1.logic.Game;

public class ExitCommand extends Command {

	public ExitCommand() {
		super("E", "exit", "[E]xit"," Termina el juego");
	}

	@Override
	public boolean execute(Game game) {
		System.out.println("FIN DEL JUEGO");
		game.exit();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords[0].equalsIgnoreCase("exit") || commandWords[0].equalsIgnoreCase("e"))
			return new ExitCommand();
		else
			return null;
	}

}
