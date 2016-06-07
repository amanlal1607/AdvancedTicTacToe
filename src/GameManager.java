/* TicTacToe - submission Aman Lal
 * Student Id - 791650
 * Email Id - amanl@student.unimelb.edu.au
 */
public class GameManager {

	private Player playerO;
	private Player playerX;
	private int moveCount; // keeps count of player moves
	private String playerOCurrentMove; // player O's current move
	private String playerXCurrentMove; // player X's current move

	public final int PLAYERO_WIN = 1; // constants representing game state
	public final int PLAYERX_WIN = 2;
	public final int GAME_DRAW = 3;
	public final int GAME_CONTINUE = 0;
	public final int GRID_SIZE = 3;
	public final int TOTAL_MOVES_ALLOWED = 9; // max. moves allowed
	public final char PLAYER_O_MOVE = 'O'; // player 1 symbol
	public final char PLAYER_X_MOVE = 'X'; // player 2 symbol
	public final int PERMISSIBLE_VALUE_LOWER_LIMIT = 48; // represents the ascii
															// code for 0
	public final int PERMISSIBLE_VALUE_UPPER_LIMIT = 50; // represents the ascii
															// code for 2
	public final int EXCEPTION1 = 6;
	public final int EXCEPTION2 = 8;
	
	public Player getPlayerO() {
		return playerO;
	}

	public void setPlayerO(Player playerO) {
		this.playerO = playerO;
	}

	public Player getPlayerX() {
		return playerX;
	}

	public void setPlayerX(Player playerX) {
		this.playerX = playerX;
	}

	public String getPlayerOCurrentMove() { /*
											 * Accessors and Mutators for
											 * Private variables
											 */
		return playerOCurrentMove;
	}

	public void setPlayerOCurrentMove(String playerOCurrentMove) {
		this.playerOCurrentMove = playerOCurrentMove;
	}

	public String getPlayerXCurrentMove() {
		return playerXCurrentMove;
	}

	public void setPlayerXCurrentMove(String playerXCurrentMove) {
		this.playerXCurrentMove = playerXCurrentMove;
	}

	public int getMoveCount() {
		return moveCount;
	}

	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}

	private char[][] initGrid() { // initialize 2D matrix for player moves
		char[][] grid = new char[GRID_SIZE][GRID_SIZE];
		for (int i = 0; i < GRID_SIZE; i++)
			for (int j = 0; j < GRID_SIZE; j++) {
				grid[i][j] = ' ';
			}
		setMoveCount(0);
		return grid;
	}

	private void printGrid(char[][] grid) { // print the player's moves status

		System.out.println(grid[0][0] + "|" + grid[0][1] + "|" + grid[0][2]);
		System.out.println("-----");
		System.out.println(grid[1][0] + "|" + grid[1][1] + "|" + grid[1][2]);
		System.out.println("-----");
		System.out.println(grid[2][0] + "|" + grid[2][1] + "|" + grid[2][2]);
	}

	private int rowWiseCheck(char[][] grid) { // rowwise check for a winner

		if (grid[0][0] == grid[0][1] && grid[0][1] == grid[0][2]
				&& grid[0][0] == PLAYER_X_MOVE) {
			return PLAYERX_WIN;
		}
		if (grid[0][0] == grid[0][1] && grid[0][1] == grid[0][2]
				&& grid[0][0] == PLAYER_O_MOVE) {
			return PLAYERO_WIN;
		}
		if (grid[1][0] == grid[1][1] && grid[1][1] == grid[1][2]
				&& grid[1][0] == PLAYER_O_MOVE) {
			return PLAYERO_WIN;
		}
		if (grid[1][0] == grid[1][1] && grid[1][1] == grid[1][2]
				&& grid[1][0] == PLAYER_X_MOVE) {
			return PLAYERX_WIN;
		}
		if (grid[2][0] == grid[2][1] && grid[2][1] == grid[2][2]
				&& grid[2][0] == PLAYER_O_MOVE) {
			return PLAYERO_WIN;
		}
		if (grid[2][0] == grid[2][1] && grid[2][1] == grid[2][2]
				&& grid[2][0] == PLAYER_X_MOVE) // row wise decision conditions
		{
			return PLAYERX_WIN;
		}
		return 0;
	}

	private int columnWiseCheck(char[][] grid) { // columnwise check for a
													// winner
		if (grid[0][0] == grid[1][0] && grid[1][0] == grid[2][0]
				&& grid[2][0] == PLAYER_X_MOVE) {
			return PLAYERX_WIN;
		}
		if (grid[0][0] == grid[1][0] && grid[1][0] == grid[2][0]
				&& grid[2][0] == PLAYER_O_MOVE) {
			return PLAYERO_WIN;
		}
		if (grid[0][1] == grid[1][1] && grid[1][1] == grid[2][1]
				&& grid[2][1] == PLAYER_X_MOVE) {
			return PLAYERX_WIN;
		}
		if (grid[0][1] == grid[1][1] && grid[1][1] == grid[2][1]
				&& grid[2][1] == PLAYER_O_MOVE) {
			return PLAYERO_WIN;
		}
		if (grid[0][2] == grid[1][2] && grid[1][2] == grid[2][2]
				&& grid[2][2] == PLAYER_O_MOVE) {
			return PLAYERO_WIN;
		}
		if (grid[0][2] == grid[1][2] && grid[1][2] == grid[2][2]
				&& grid[2][2] == PLAYER_X_MOVE) // column wise decision
												// conditions
		{
			return PLAYERX_WIN;
		}
		return 0;
	}

	private int diagonalsCheck(char[][] grid) { // check diagonals and anti
												// diagonals for a winner

		if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]
				&& grid[2][2] == PLAYER_X_MOVE) {
			return PLAYERX_WIN;
		}
		if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]
				&& grid[2][2] == PLAYER_O_MOVE) // diagonal condition
		{
			return PLAYERO_WIN;
		}
		if (grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2]
				&& grid[2][0] == PLAYER_O_MOVE) {
			return PLAYERO_WIN;
		}
		if (grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2]
				&& grid[2][0] == PLAYER_X_MOVE) // anti - diagonal condition
		{
			return PLAYERX_WIN;
		}
		return 0;
	}

	private int getGameState(char[][] grid) { /*
											 * check for a winner/draw or if the
											 * game will continue
											 */
		int check = 0;
		check = rowWiseCheck(grid);
		if (check == 0) {
			check = columnWiseCheck(grid);
		}
		if (check == 0) {
			check = diagonalsCheck(grid);
		}
		if (check != 0) {
			return check;
		}
		if (getMoveCount() == TOTAL_MOVES_ALLOWED) {
			return GAME_DRAW;
		}
		return GAME_CONTINUE;

	}

	private void printGameState(int gameresult) { // Print out current game
													// state
		switch (gameresult) {
		case PLAYERO_WIN: {
			System.out.println("Game over. " + getPlayerO().getGivenName()
					+ " won!");
			getPlayerO().setGamesPlayed(getPlayerO().getGamesPlayed() + 1);
			getPlayerX().setGamesPlayed(getPlayerX().getGamesPlayed() + 1);
			getPlayerO().setGamesWon(getPlayerO().getGamesWon() + 1);
			break;
		}
		case PLAYERX_WIN: {
			System.out.println("Game over. " + getPlayerX().getGivenName()
					+ " won!");
			getPlayerO().setGamesPlayed(getPlayerO().getGamesPlayed() + 1);
			getPlayerX().setGamesPlayed(getPlayerX().getGamesPlayed() + 1);
			getPlayerX().setGamesWon(getPlayerX().getGamesWon() + 1);
			break;
		}
		case GAME_DRAW: {
			System.out.println("Game over. " + "It was a draw!");
			getPlayerO().setGamesPlayed(getPlayerO().getGamesPlayed() + 1);
			getPlayerX().setGamesPlayed(getPlayerX().getGamesPlayed() + 1);
			getPlayerO().setGamesDrawn(getPlayerO().getGamesDrawn() + 1);
			getPlayerX().setGamesDrawn(getPlayerX().getGamesDrawn() + 1);
			break;
		}
		case GAME_CONTINUE:
			System.out.println("Game will continue");
		default:
			;
		}
	}

	private boolean checkOccupiedCellPlayerO(char[][] grid) { /*
															 * check if the cell
															 * has already been
															 * occupied for
															 * playerO
															 */
		while (grid[(getPlayerOCurrentMove().charAt(0) - '0')][(getPlayerOCurrentMove()
				.charAt(2) - '0')] == PLAYER_O_MOVE
				|| grid[(getPlayerOCurrentMove().charAt(0) - '0')][(getPlayerOCurrentMove()
						.charAt(2) - '0')] == PLAYER_X_MOVE) {
			System.out.println("Invalid move. The cell has been occupied.");
			System.out.println(getPlayerO().getGivenName() + "'s" + " move:");
			setPlayerOCurrentMove(TicTacToe.sc.nextLine());
			checkInputValuesPlayerO(grid);

		}
		return true;
	}

	private boolean checkOccupiedCellPlayerX(char[][] grid) { /*
															 * check if cell has
															 * already been
															 * occupied for
															 * player X
															 */
		while (grid[(getPlayerXCurrentMove().charAt(0) - '0')][(getPlayerXCurrentMove()
				.charAt(2) - '0')] == PLAYER_O_MOVE
				|| grid[(getPlayerXCurrentMove().charAt(0) - '0')][(getPlayerXCurrentMove()
						.charAt(2) - '0')] == PLAYER_X_MOVE) {
			System.out.println("Invalid move. The cell has been occupied.");
			System.out.println(getPlayerX().getGivenName() + "'s" + " move:");
			setPlayerXCurrentMove(TicTacToe.sc.nextLine());
			checkInputValuesPlayerX(grid);
		}
		return true;
	}

	private boolean checkInputValuesPlayerO(char[][] grid) { /*
															 * check input value
															 * for playerO
															 */
		while (getPlayerOCurrentMove().charAt(0) < PERMISSIBLE_VALUE_LOWER_LIMIT
				|| getPlayerOCurrentMove().charAt(0) > PERMISSIBLE_VALUE_UPPER_LIMIT
				|| getPlayerOCurrentMove().charAt(2) < PERMISSIBLE_VALUE_LOWER_LIMIT
				|| getPlayerOCurrentMove().charAt(2) > PERMISSIBLE_VALUE_UPPER_LIMIT) {
			System.out
					.println("Invalid move. You must place at a cell within {0,1,2} {0,1,2}.");
			System.out.println(getPlayerO().getGivenName() + "'s" + " move:");
			setPlayerOCurrentMove(TicTacToe.sc.nextLine());
			checkOccupiedCellPlayerO(grid);
		}
		return true;
	}

	private boolean checkInputValuesPlayerX(char[][] grid) { /*
															 * check input value
															 * for playerX
															 */
		while (getPlayerXCurrentMove().charAt(0) < PERMISSIBLE_VALUE_LOWER_LIMIT
				|| getPlayerXCurrentMove().charAt(0) > PERMISSIBLE_VALUE_UPPER_LIMIT
				|| getPlayerXCurrentMove().charAt(2) < PERMISSIBLE_VALUE_LOWER_LIMIT
				|| getPlayerXCurrentMove().charAt(2) > PERMISSIBLE_VALUE_UPPER_LIMIT) {
			System.out
					.println("Invalid move. You must place at a cell within {0,1,2} {0,1,2}.");
			System.out.println(getPlayerX().getGivenName() + "'s" + " move:");
			setPlayerXCurrentMove(TicTacToe.sc.nextLine());
			checkOccupiedCellPlayerX(grid);
		}
		return true;
	}

	public void playGame() { // to play a game of TicTacToe between 2 players

		char[][] grid = new char[GRID_SIZE][GRID_SIZE];
		grid = initGrid();
		int moveCount = 0;
		int currentGameState = 0;
		while (true) {
			if (moveCount >= TOTAL_MOVES_ALLOWED) {
				return;
			}
			printGrid(grid);
			System.out.println(getPlayerO().getGivenName() + "'s" + " move:");
			Move tempMove = new Move();
			tempMove.copyMethod(getPlayerO().makeMove(grid));
			setPlayerOCurrentMove(tempMove.getRowNumber() + " " + tempMove.getColumnNumber());
			if (checkInputValuesPlayerO(grid) && checkOccupiedCellPlayerO(grid)) {
				// assigning the grid with player O's marker
				grid[(getPlayerOCurrentMove().charAt(0) - '0')][(getPlayerOCurrentMove()
						.charAt(2) - '0')] = PLAYER_O_MOVE; // - '0' to convert
															// char to
				// int

				printGrid(grid);
				moveCount++;
				setMoveCount(moveCount);

			}

			currentGameState = getGameState(grid); /*
													 * check game-state post
													 * playerO's move
													 */
			if (currentGameState > 0) {
				if (getMoveCount() == EXCEPTION1 || getMoveCount() == EXCEPTION2) {
					printGrid(grid);
				}
				printGameState(currentGameState);
				return;
			}

			if (moveCount >= TOTAL_MOVES_ALLOWED) {
				return;
			}
			System.out.println(getPlayerX().getGivenName() + "'s" + " move:");
			tempMove.copyMethod(getPlayerX().makeMove(grid));
			setPlayerXCurrentMove(tempMove.getRowNumber() + " " + tempMove.getColumnNumber());
			if (checkInputValuesPlayerX(grid) && checkOccupiedCellPlayerX(grid)) {
				// assigning the grid with player X's marker
				grid[(getPlayerXCurrentMove().charAt(0) - '0')][(getPlayerXCurrentMove()
						.charAt(2) - '0')] = PLAYER_X_MOVE; // - '0' to convert
															// char to
				// int

				moveCount++;
				setMoveCount(moveCount);

			}

			currentGameState = getGameState(grid); /*
													 * check game-state post
													 * playerX's move
													 */
			if (currentGameState > 0) {
				if (getMoveCount() == EXCEPTION1 || getMoveCount() == EXCEPTION2) {
					printGrid(grid);
				}
				printGameState(currentGameState);
				return;
			}

		}

	};

}
