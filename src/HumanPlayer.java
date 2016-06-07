/* TicTacToe - submission Aman Lal
 * Student Id - 791650
 * Email Id - amanl@student.unimelb.edu.au
 */

public class HumanPlayer extends Player { // class represents a human player

	public HumanPlayer(String newUserName, String newFamilyName,
			String newGivenName, int newGamesPlayed, int newGamesWon,
			int newGamesDrawn) { // constructor to initialize HumanPlayer object
		// HumanPlayer constructor for HumanPlayer initialization
		setUsername(newUserName);
		setFamilyName(newFamilyName);
		setGivenName(newGivenName);
		setGamesPlayed(newGamesPlayed);
		setGamesWon(newGamesWon);
		setGamesDrawn(newGamesDrawn);

	}

	public Move makeMove(char[][] gameBoard) {
		// overridden abstract method for human player
		String line = TicTacToe.sc.nextLine();
		Move m = new Move();
		m.setRowNumber(line.charAt(0));
		m.setColumnNumber(line.charAt(2));
		return m;
	}
}
