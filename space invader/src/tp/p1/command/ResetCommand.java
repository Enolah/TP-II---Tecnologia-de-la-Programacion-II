package tp.p1.command;

import tp.p1.logic.Game;

public class ResetCommand extends Command {

	public ResetCommand() {
		super("R","reset","[R]eset"," Empiezas un nuevo juego");
		
	}

	@Override
	public boolean execute(Game game) {
		game.reset();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords[0].equalsIgnoreCase("reset") || commandWords[0].equalsIgnoreCase("r"))
			return new ResetCommand();
		else
			return null;
	}

}
