/* TicTacToe - submission Aman Lal
 * Student Id - 791650
 * Email Id - amanl@student.unimelb.edu.au
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class PlayerManager {

	public ArrayList<Player> players = new ArrayList<Player>();

	public PlayerManager() {

	}

	public boolean addPlayer(String newUserName, String newFamilyName,
			String newGivenName) { // Adds a new player

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUsername().equalsIgnoreCase(newUserName))
				return false;
		}

		HumanPlayer newPlayer = new HumanPlayer(newUserName, newFamilyName,
				newGivenName, 0, 0, 0);
		newPlayer.setAI(false);
		players.add(newPlayer);

		return true;
	}

	public boolean addAIPlayer(String newUserName, String newFamilyName,
			String newGivenName) { // Adds a new player

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUsername().equalsIgnoreCase(newUserName))
				return false;
		}

		AIPlayer newPlayer = new AIPlayer(newUserName, newFamilyName,
				newGivenName, 0, 0, 0);
		newPlayer.setAI(true);
		players.add(newPlayer);

		return true;
	}

	public boolean addOldHumanPlayer(String newUserName, String newFamilyName,
			String newGivenName, int newGamesPlayed, int newGamesWon,
			int newGamesDrawn) { // Adds an old Human player back to the system

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUsername().equalsIgnoreCase(newUserName))
				return false;
		}

		HumanPlayer newPlayer = new HumanPlayer(newUserName, newFamilyName,
				newGivenName, newGamesPlayed, newGamesWon, newGamesDrawn);
		newPlayer.setAI(false);
		players.add(newPlayer);
		

		return true;
	}

	public boolean addOldAIPlayer(String newUserName, String newFamilyName,
			String newGivenName, int newGamesPlayed, int newGamesWon,
			int newGamesDrawn) { // Adds an old AI player back to the system

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUsername().equalsIgnoreCase(newUserName))
				return false;
		}
		AIPlayer newPlayer = new AIPlayer(newUserName, newFamilyName,
				newGivenName, newGamesPlayed, newGamesWon, newGamesDrawn);
		newPlayer.setAI(true);
		players.add(newPlayer);

		return true;
	}

	public boolean removePlayer(String userName) {
		// Removes a single player
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUsername().equalsIgnoreCase(userName)) {
				players.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean removePlayer() {
		// Removes all players in the system
		System.out
				.println("Are you sure you want to remove all players? (y/n)");

		String check = TicTacToe.sc.nextLine();
		if (check.equalsIgnoreCase("y"))
			players.removeAll(players);
		return true;
	}

	public boolean editPlayer(String userName, String newFamilyName,
			String newGivenName) {
		// Edits a single player
		for (int i = 0; i < players.size(); i++)
			if (players.get(i).getUsername().equalsIgnoreCase(userName)) {
				players.get(i).setFamilyName(newFamilyName);
				players.get(i).setGivenName(newGivenName);
				return true;
			}
		return false;
	};

	public boolean resetStats(String userName) { /*
												 * reset game statistics for a
												 * player
												 */
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUsername().equalsIgnoreCase(userName)) {
				players.get(i).setGamesPlayed(0);
				players.get(i).setGamesWon(0);
				players.get(i).setGamesDrawn(0);
				return true;
			}
		}
		return false;
	}

	public boolean resetStats() { /*
								 * reset game statistics for all players in the
								 * system
								 */
		System.out
				.println("Are you sure you want to reset all player statistics? (y/n)");
		if (TicTacToe.sc.nextLine().equalsIgnoreCase("y")) {
			for (int i = 0; i < players.size(); i++) {
				players.get(i).setGamesPlayed(0);
				players.get(i).setGamesWon(0);
				players.get(i).setGamesDrawn(0);
			}
			return true;
		}
		return false;
	}

	public boolean dislayPlayer(String userName) { /*
													 * display all details for a
													 * player
													 */
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUsername().equalsIgnoreCase(userName)) {
				System.out.println(players.get(i).getUsername() + ","
						+ players.get(i).getFamilyName() + ","
						+ players.get(i).getGivenName() + ","
						+ players.get(i).getGamesPlayed() + " games" + ","
						+ players.get(i).getGamesWon() + " wins" + ","
						+ players.get(i).getGamesDrawn() + " draws");
				return true;
			}
		}
		return false;
	}

	public void displayPlayer() { /* display details for all players */
		Collections.sort(players, new AlphabeticalComparator()); /*
																 * uses a new
																 * instance of
																 * the
																 * AlphabeticalComparator
																 * class to sort
																 */
		for (int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i).getUsername() + ","
					+ players.get(i).getFamilyName() + ","
					+ players.get(i).getGivenName() + ","
					+ players.get(i).getGamesPlayed() + " games" + ","
					+ players.get(i).getGamesWon() + " wins" + ","
					+ players.get(i).getGamesDrawn() + " draws");
		}
	}

	public void displayRanking() { // displays player rankings

		System.out.println(" WIN  | DRAW | GAME | USERNAME");
		Collections.sort(players, new RatioComparator());
		int winRatio, drawRatio, games;
		String winRatioDisplay, drawRatioDisplay, gamesDisplay;
		int rankSize = 10;
		if (players.size() < rankSize)
			rankSize = players.size();
		for (int i = 0; i < rankSize; i++) {
			winRatio = Math.round((float) (players.get(i).getGamesWon()
					/ (float) (players.get(i).getGamesPlayed()) * 100));
			drawRatio = Math.round((float) (players.get(i).getGamesDrawn()
					/ (float) (players.get(i).getGamesPlayed()) * 100));
			games = players.get(i).getGamesPlayed();
			winRatioDisplay = String.format("%3s", winRatio); // adding padding
																// for
																// formatting
			drawRatioDisplay = String.format("%3s", drawRatio);
			gamesDisplay = String.format("%-3s", games);
			System.out.println(" " + winRatioDisplay + "% | "
					+ drawRatioDisplay + "% |  " + gamesDisplay + " | "
					+ players.get(i).getUsername());
		}
	}

	public boolean readFromFile() { // this method reads from
									// "players.dat"/updates information/creates
									// file if it doesn't exist

		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"players.dat"));
			String readCounter = "";
			while (readCounter != null) {
				readCounter = reader.readLine();
				if (readCounter == "") {
					readCounter = null;
				} else if (readCounter != null) {
					String[] valuePlaceHolder = readCounter.split(",");
					if (valuePlaceHolder[6].equalsIgnoreCase("true")) {
						addOldAIPlayer(valuePlaceHolder[0],
								valuePlaceHolder[1], valuePlaceHolder[2],
								Integer.parseInt(valuePlaceHolder[3]),
								Integer.parseInt(valuePlaceHolder[4]),
								Integer.parseInt(valuePlaceHolder[5]));

					} else {
						addOldHumanPlayer(valuePlaceHolder[0],
								valuePlaceHolder[1], valuePlaceHolder[2],
								Integer.parseInt(valuePlaceHolder[3]),
								Integer.parseInt(valuePlaceHolder[4]),
								Integer.parseInt(valuePlaceHolder[5]));
					}
				}
			}
			reader.close();

		} catch (FileNotFoundException e) {
			PrintWriter p;
			try {
				p = new PrintWriter(new FileOutputStream("players.dat", true));
				p.close();
			} catch (FileNotFoundException ex) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean writeToFile() { // this method writes player data to file on
									// exit command
		try {
			PrintWriter write = new PrintWriter(new FileOutputStream(
					"players.dat"));
			for (int i = 0; i < players.size(); i++) {
				write.println(players.get(i));
			}
			write.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return false;

		}
		return true;

	}
}
