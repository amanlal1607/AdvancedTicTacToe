
public class InvalidCommandException extends Exception {
	
	InvalidCommandException(String command){
		String[] callingCommand = command.split(" ");
		System.out.println("'"+callingCommand[0]+"' "+ "is not a valid command.");
	}

}
