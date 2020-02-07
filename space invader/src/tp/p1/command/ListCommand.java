package tp.p1.command;

import tp.p1.logic.Game;

public class ListCommand extends Command {

	public ListCommand() {
		super("L","list","[L]ist","Muestra por pantalla la lista de naves");
		
	}

	@Override
	public boolean execute(Game game) {
		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords[0].equalsIgnoreCase("list") || commandWords[0].equalsIgnoreCase("l"))
			return new ListCommand();
		else
			return null;
	}

}
