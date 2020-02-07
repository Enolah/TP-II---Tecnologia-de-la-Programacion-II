package tp.p1.logic;

import tp.p1.logic.object.DestroyerShip;
import tp.p1.logic.object.Ovni;
import tp.p1.logic.object.RegularShip;

public class BoardInitializer {
	
	private Level level;
	private GameObjectBoard board;
	private Game game;
	
	public  GameObjectBoard initialize(Game game, Level level) {
		this.level = level;
		this.game = game;
		board = new GameObjectBoard(Game.DIM_X, Game.DIM_Y);
		
		initializeOvni();
		initializeRegularAliens();
		initializeDestroyerAliens();
		
		return board;
	}
	
	private void initializeOvni () {

		Ovni ovni = new Ovni(game, 0, 9, 0); //no se que poner
		board.add(ovni);
	}

	private void initializeRegularAliens () {
		 
		for (int i = 0; i < level.getNumRegularAliens(); i++) {
			RegularShip regular = new RegularShip(game,1 + (i / 4), 3 + (i % 4), 2);
			board.add(regular);
		}
	}
	
	private void initializeDestroyerAliens() {
		int offsetRow = 3;
		int offsetCol = 4;
		switch (level) {
		case EASY: 
			offsetRow = 2;
			break;
		case INSANE:
			offsetCol = 3;
			break;
		default:
			break;
		}
		for (int i = 0; i < level.getNumDestroyerAliens(); i++) {
			DestroyerShip destroyer = new DestroyerShip(game,offsetRow + (i / 4), offsetCol + (i % 4),1);
			board.add(destroyer);
		}
	}
}