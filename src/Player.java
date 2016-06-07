/* TicTacToe - submission Aman Lal
 * Student Id - 791650
 * Email Id - amanl@student.unimelb.edu.au
 */

public abstract class Player {

	private String userName;
	private String familyName;
	private String givenName;
	private int gamesPlayed;
	private int gamesWon;
	private int gamesDrawn; // Player details to be stored in the system
	private boolean isAI;

	public boolean isAI() {
		return isAI;
	}

	public void setAI(boolean isAI) {
		this.isAI = isAI;
	}

	private Move playerMove;

	public String getUsername() {
		return userName;
	};

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	public int getGamesDrawn() {
		return gamesDrawn;
	}

	public void setGamesDrawn(int gamesDrawn) {

		this.gamesDrawn = gamesDrawn;
	}

	public Move getPlayerMove() {
		return playerMove;
	}

	public void setPlayerMove(Move playerMove) {
		this.playerMove = playerMove;
	}

	public String toString() {
		return userName + "," + familyName + "," + givenName + ","
				+ gamesPlayed + "," + gamesWon + "," + gamesDrawn + "," + isAI;

	}

	public abstract Move makeMove(char[][] gameBoard);

}
