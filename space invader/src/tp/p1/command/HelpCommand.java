package tp.p1.command;

import tp.p1.logic.Game;

public class HelpCommand extends Command {

	public HelpCommand() {
		super("H", "help","[H]elp", " Muestra por pantalla el mensaje de ayuda");
	}

	@Override
	public boolean execute(Game game) {
		String s= CommandGenerator.commandHelp();
		System.out.println(s);
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords[0].equalsIgnoreCase("help") || commandWords[0].equalsIgnoreCase("h"))
			return new HelpCommand();
		else
			return null;
		
	}

}
