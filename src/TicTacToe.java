/* TicTacToe - submission Aman Lal
 * Student Id - 791650
 * Email Id - amanl@student.unimelb.edu.au
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class TicTacToe {
	public static Scanner sc = new Scanner(System.in);
	public PlayerManager manager; // Player Manager instance to manage players
	public GameManager game; // Game Manager instance to manage gameplay
	private String Command;

	public String getCommand() { // accessors and mutators for player commands
		return Command;
	}

	public void setCommand(String command) {
		Command = command;
	}

	private void addPlayerCommand(String command)
			throws InvalidNumberOfArgumentsException { // analyzes the addplayer
		// command and adds a player
		String[] numberOfArgs = command.split(" ");
		String names = numberOfArgs[1];
		numberOfArgs = names.split(",");
		if (numberOfArgs.length != 3) {
			throw new InvalidNumberOfArgumentsException();
		}
		String userName = numberOfArgs[0];
		String familyName = numberOfArgs[1];
		String givenName = numberOfArgs[2];
		boolean check;
		check = manager.addPlayer(userName, familyName, givenName);
		if (check == false)
			System.out.println("The username has been used already.");
	}

	private void addAIPlayerCommand(String command)
			throws InvalidNumberOfArgumentsException { // analyzes the addplayer
		// command and adds a player
		String[] numberOfArgs = command.split(" ");
		String names = numberOfArgs[1];
		numberOfArgs = names.split(",");
		if (numberOfArgs.length != 3) {
			throw new InvalidNumberOfArgumentsException();
		}
		String userName = numberOfArgs[0];
		String familyName = numberOfArgs[1];
		String givenName = numberOfArgs[2];
		boolean check;
		check = manager.addAIPlayer(userName, familyName, givenName);
		if (check == false)
			System.out.println("The username has been used already.");
	}

	private void removePlayerCommand(String command)
			throws InvalidNumberOfArgumentsException { // analyzes the remove
		// player command and
		// removes the player
		String[] numberOfArgs = command.split(" ");
		String names = numberOfArgs[1];
		numberOfArgs = names.split(",");
		if (numberOfArgs.length != 1) {

			throw new InvalidNumberOfArgumentsException();
		}
		String userName = numberOfArgs[0];
		boolean check;
		check = manager.removePlayer(userName);
		if (check == false) {
			System.out.println("The player does not exist.");
		}
	}

	private void resetPlayerCommand(String command)
			throws InvalidNumberOfArgumentsException { /*
														 * analyzes the
														 * resetstats command
														 * and reset statistics
														 */
		String[] numberOfArgs = command.split(" ");
		String names = numberOfArgs[1];
		numberOfArgs = names.split(",");
		if (numberOfArgs.length != 1) {
			throw new InvalidNumberOfArgumentsException();
		}
		String userName = numberOfArgs[0];
		boolean check;
		check = manager.resetStats(userName);
		if (check == false) {
			System.out.println("The player does not exist.");
		}
	}

	private void displayPlayerCommand(String command)
			throws InvalidNumberOfArgumentsException { /*
														 * analyzes the display
														 * player oommand and
														 * displays player
														 * details
														 */
		String[] numberOfArgs = command.split(" ");
		String names = numberOfArgs[1];
		numberOfArgs = names.split(",");
		String userName = numberOfArgs[0];
		if (numberOfArgs.length != 1) {
			throw new InvalidNumberOfArgumentsException();
		}
		boolean check;
		check = manager.dislayPlayer(userName);
		if (check == false) {
			System.out.println("The player does not exist.");
		}
	}

	private void editPlayerCommand(String command)
			throws InvalidNumberOfArgumentsException { /*
														 * analyzes the edit
														 * player command and
														 * edits player details
														 */
		String[] numberOfArgs = command.split(" ");
		String names = numberOfArgs[1];
		numberOfArgs = names.split(",");
		if (numberOfArgs.length != 3) {
			throw new InvalidNumberOfArgumentsException();
		}
		String userName = numberOfArgs[0];
		String familyName = numberOfArgs[1];
		String givenName = numberOfArgs[2];
		boolean check;
		check = manager.editPlayer(userName, familyName, givenName);
		if (check == false)
			System.out.println("The player does not exist.");

	}

	private void playGameCommand(String command)
			throws InvalidNumberOfArgumentsException { // analyzes the playgame
		// command and initiates a
		// game of tictactoe between
		// 2 players
		String[] numberOfArgs = command.split(" ");
	
		if (numberOfArgs.length != 2) {
			throw new InvalidNumberOfArgumentsException();
		}
		String names = numberOfArgs[1];
		numberOfArgs = names.split(",");
		String userNamePlayerO = numberOfArgs[0];
		String userNamePlayerX = numberOfArgs[1];
		game.setPlayerO(null);
		game.setPlayerX(null);
		for (int i = 0; i < manager.players.size(); i++) {
			if (manager.players.get(i).getUsername()
					.equalsIgnoreCase(userNamePlayerO)) {
				game.setPlayerO(manager.players.get(i));
			}
			if (manager.players.get(i).getUsername()
					.equalsIgnoreCase(userNamePlayerX)) {
				game.setPlayerX(manager.players.get(i));
			}
		}
		if (game.getPlayerO() != null && game.getPlayerX() != null) {

			game.playGame();
		} else {
			System.out.println("Player does not exist.");
		}

	}

	private void commandLine(String command) throws InvalidCommandException,
			InvalidNumberOfArgumentsException {
		// commandLine for the game system

		if (command.equalsIgnoreCase("exit")) {
			System.out.println();
			manager.writeToFile();
			System.exit(0);
		} else if (command.equalsIgnoreCase("rankings")) {
			this.manager.displayRanking();
		} else if (command.contains("addplayer")) {
			addPlayerCommand(command);
		} else if (command.contains("addaiplayer")) {
			addAIPlayerCommand(command);
		} else if (command.equalsIgnoreCase("removeplayer")) {
			manager.removePlayer();
		} else if (command.contains("removeplayer")) {
			removePlayerCommand(command);
		} else if (command.equalsIgnoreCase("resetstats")) {
			manager.resetStats();
		} else if (command.contains("resetstats")) {
			resetPlayerCommand(command);
		} else if (command.equalsIgnoreCase("displayplayer")) {
			manager.displayPlayer();
		} else if (command.contains("displayplayer")) {
			displayPlayerCommand(command);
		} else if (command.contains("editplayer")) {
			editPlayerCommand(command);
		} else if (command.contains("playgame")) {
			playGameCommand(command);
		} else {
			throw new InvalidCommandException(command);
		}
	}

	TicTacToe() { // constructor to initialize a player manager and game manager

		manager = new PlayerManager();
		game = new GameManager();
	}

	public void run() { // Runs the game

		System.out.println("Welcome to Tic Tac Toe!");
		while (true) {
			manager.readFromFile();
			System.out.println();
			System.out.print(">");
			setCommand(sc.nextLine());
			try {
				commandLine(getCommand());
			} catch (InvalidCommandException e) {
				// TODO Auto-generated catch block
			} catch (InvalidNumberOfArgumentsException e) {
			}

		}
	}

	public static void main(String args[]) {
		TicTacToe gameSystem = new TicTacToe();

		gameSystem.run();

	}
}
