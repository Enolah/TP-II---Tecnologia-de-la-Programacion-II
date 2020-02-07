package tp.p1.command;

import tp.p1.logic.Game;

public class UpdateCommand extends Command {

	public UpdateCommand() {
		super("N","none","[N]one"," [none] se salta un ciclo sin hacer nada");
		
	}

	@Override
	public boolean execute(Game game) {
		//none
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords[0].equalsIgnoreCase("none") || commandWords[0].equalsIgnoreCase("n") || commandWords[0].equalsIgnoreCase(""))
			return new UpdateCommand();
		else
			return null;
	}

}
