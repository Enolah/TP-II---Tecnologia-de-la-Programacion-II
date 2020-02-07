package tp.p1.command;

import tp.p1.exception.CommandParseException;
import tp.p1.logic.Game;

public class MoveCommand extends Command {

	private int num;
	private int y;
	
	public MoveCommand() {
		super("M","move","[M]ove <left|right> <1|2>"," Mueve UCMShip a la direccion indicada y el numero de casillas");
	}

//	public MoveCommand(int num) {
//		super("M","move","[M]ove <left|right> <1|2>"," Mueve UCMShip a la direccion indicada y el numero de casillas");
//		this.num=num;
//	}

	@Override
	public boolean execute(Game game) {
		return game.move(num);
	}

	@Override
	public Command parse(String[] commandWords) /*throws CommandParseException*/ {
		MoveCommand resultado= null;
//		if (commandWords.length == 1 ||commandWords.length == 2 || commandWords.length > 3 ){
//			System.out.println(""); //TODO cambiar esto del move
//			
//		}
//		else{
	
			if (commandWords[0].equalsIgnoreCase("move") || commandWords[0].equalsIgnoreCase("m")){
				try {
					if(commandWords[1].equalsIgnoreCase("left") ||commandWords[1].equalsIgnoreCase("l")){
						
//						if (y<=0) /*throw new CommandParseException("Posicion invalida");*/
//							System.out.println("Movimiento invalido");
//						else
							resultado=new MoveCommand();
							resultado.num=Integer.parseInt(commandWords[2]);
							
					}
					else if (commandWords[1].equalsIgnoreCase("right") || commandWords[1].equalsIgnoreCase("r")){
						
//						if ( y >= 8) /*throw new CommandParseException("Posicion invalida");*/
//							System.out.println("Movimiento invalido");
//						else
						//y=y-(2*y);
						resultado=new MoveCommand();
						resultado.num=Integer.parseInt(commandWords[2]);
					}
						
//					else {
//						System.out.println("No se reconoce la direccion del movimiento");
//					
//					}
				} catch (NumberFormatException e) {
					/*throw new CommandParseException("Se ha producido un error en el parseo \n "+ e.toString());*/
					System.out.println("Se ha producido un error en el parseo \n "+ e.toString());
				}
			
			}
			
		
		return resultado;
	}

}
