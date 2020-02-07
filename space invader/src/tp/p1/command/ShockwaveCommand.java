package tp.p1.command;

import tp.p1.logic.Game;

public class ShockwaveCommand extends Command {

	public ShockwaveCommand() {
		super("W","shockwave","Shock[W]ave", "UCMShip lanza una shock wave");
		
	}

	@Override
	public boolean execute(Game game) {
		game.shockWave();
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords[0].equalsIgnoreCase("shockwave") || commandWords[0].equalsIgnoreCase("w"))
			return new ShockwaveCommand();
		else
			return null;
	}

}
