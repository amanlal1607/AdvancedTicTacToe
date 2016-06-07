/* TicTacToe - submission Aman Lal
 * Student Id - 791650
 * Email Id - amanl@student.unimelb.edu.au
 */

public class AIPlayer extends Player {

	public final int GRID_SIZE = 3;

	public AIPlayer(String newUserName, String newFamilyName,
			String newGivenName, int newGamesPlayed, int newGamesWon,
			int newGamesDrawn) // constructor for the AI player class
	{
		setUsername(newUserName);
		setFamilyName(newFamilyName);
		setGivenName(newGivenName);
		setGamesPlayed(newGamesPlayed);
		setGamesWon(newGamesWon);
		setGamesDrawn(newGamesDrawn);
	}

	public Move makeMove(char[][] gameBoard) {
		// determining a free cell to makeMove for AIplayer
		Move m = new Move();
		for (int i = 0; i < GRID_SIZE; i++)
			for (int j = 0; j < GRID_SIZE; j++) {
				if (gameBoard[i][j] == ' ') {
					m.setRowNumber(String.valueOf(i).charAt(0));
					m.setColumnNumber(String.valueOf(j).charAt(0));
					return m;
				}
			}

		return m;
	}
}
