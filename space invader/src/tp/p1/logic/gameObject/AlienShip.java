package tp.p1.logic.gameObject;

import tp.p1.logic.Game;
import tp.p1.logic.Level;
import tp.p1.logic.Move;

public abstract class AlienShip extends EnemyShip {

	protected static  int Speed, aliensNumber,SHIP_ON_BORDER;
	protected int CTM; //cycles to move
	private Move move;
	protected static boolean landed = false;
	protected static boolean allDead=false;
	
	public AlienShip(Game game, int x, int y, int live) {
		super(game, x, y, live);
		this.move = Move.LEFT;
		aliensNumber++;
	}

	public abstract int getPoints();
	public abstract void computerAction();
	
	public boolean receiveShockWaveAttack(int damage) {
		return false;
	}
	 
	@Override
	public void onDelete() {
		//invoca al ondelete() de la superclase
		super.onDelete();
		aliensNumber--;
	}

	public static boolean allDead() {
		return allDead;
	}

	public static boolean haveLanded() {
		return landed;
	}

	
	public static int getRemainingAliens() {
		return aliensNumber;
	}

	@Override
	public void move() {
		// TODO hacer mover las naves enemigas, cuando una nave llegue al suelo
		// hacer landed = true
		CTM = (int) (game.currentCycle % game.getLevel().getNumCyclesToMoveOneCell());
	
		if(CTM==0){
			
			if (move==Move.LEFT)
				y--;
			else if (move==Move.RIGHT)
				y++;

			
			
//			if(game.getLevel()==Level.INSANE && isInFinalRow()){
//				starShipDescent();
//				for (int i = 0; i <SHIP_ON_BORDER; i++) {
//					descent();
//				}
//			}
			  
		}
		else if(CTM!=0 && aliensNumber!=0){
			descent();
		}
		else if (CTM!=0 && aliensNumber==0)
			CTM--;
	}

	private void descent() {
		if(isInFinalRow()) {
			this.x++;
			move= move.flip(move); //Cambia Lefta Righty viceversa
			starShipDescent();
			SHIP_ON_BORDER -= 1;
		}	
		else{
			if (shipsDescending()){
				this.x++;
				move= move.flip(move); 
				SHIP_ON_BORDER -= 1;
			}
		}
		
		if (x==game.DIM_X) 
			landed=true;
	}
	
	private boolean isInFinalRow() {
		return (move==Move.LEFT && y==0) || (move==Move.RIGHT && y == game.DIM_Y);
	}

	private boolean shipsDescending(){
		return SHIP_ON_BORDER > 0;
		}
	private void starShipDescent() {
		 SHIP_ON_BORDER = aliensNumber;
	}

	public boolean isOnBorder(){
		int border= (move==Move.LEFT)? 0: game.DIM_X-1;
		return getY()==border;
	}
	
	
	//move()
//		for (int i = 0; i < a_regular.length; i++) {
//
//			if (a_regular[i] != null) {
//				
//					if (a_regular[i].getMove() == Move.LEFT)
//						a_regular[i].setPoscion(a_regular[i].getPoscion().getX(),
//								a_regular[i].getPoscion().getY() - 1);
//					else if (a_regular[i].getMove() == Move.RIGHT)
//						a_regular[i].setPoscion(a_regular[i].getPoscion().getX(),
//								a_regular[i].getPoscion().getY() + 1);
//					else if (a_regular[i].getMove() == Move.DOWN) {
//						a_regular[i].setPoscion(a_regular[i].getPoscion().getX() + 1,a_regular[i].getPoscion().getY());
//						if (a_regular[i].getPoscion().getX()==(fil-1))
//							ult=true;
//					}
//
//				}
//	
//				
		
//		
//		if(Move.LEFT != null)
//			y=y-1;
//		else if ( Move.RIGHT != null)
//			y=y+1;
//		else if (Move.DOWN != null)
//			x=x+1;
//		
//
//	}
	

}
