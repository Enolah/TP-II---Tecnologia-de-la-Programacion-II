package tp.p1.command;

import tp.p1.logic.Game;

public class ShootCommand extends Command {

	public ShootCommand() {
		super("S", "shoot","[S]hoot"," UCMShip dispara un misil");
		
	}

	@Override
	public boolean execute(Game game) {
		return game.shootLaser();
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords[0].equalsIgnoreCase("shoot") || commandWords[0].equalsIgnoreCase("s"))
			return new ShootCommand();
		else
			return null;
	}

}
