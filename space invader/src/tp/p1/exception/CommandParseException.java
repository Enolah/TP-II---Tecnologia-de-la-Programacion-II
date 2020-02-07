package tp.p1.exception;

public class CommandParseException extends Exception {

	public CommandParseException(){
		super();
	}
	
	public CommandParseException(String msg){
		super(msg);
	}
}
