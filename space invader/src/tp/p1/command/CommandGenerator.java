package tp.p1.command;

public class CommandGenerator {
	private static Command[] availableCommands = {
			new ListCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new MoveCommand(),
			new ShockwaveCommand(),
			new ShootCommand()
	};
	
	public static Command parseCommand (String[] commandWords){
		Command cm=null;
		if(commandWords.length==0 || commandWords.length == 2) return cm;
		for (Command c:availableCommands){
			cm=c.parse(commandWords);
			if (cm!=null) break;
		}
		return cm;
	}
	public static String commandHelp(){ 
		String s="";
		for (Command c:availableCommands)
			s+=c.helpText();
		return s;
	}
}
