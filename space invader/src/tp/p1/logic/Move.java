package tp.p1.logic;

public enum Move {
	LEFT, RIGHT, DOWN;

	public Move flip(Move move) {
		if(move== LEFT)
			move=RIGHT;
		else if (move== RIGHT)
			move=LEFT;
		
		return move;
	}
}

