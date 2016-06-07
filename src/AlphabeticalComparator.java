/* TicTacToe - submission Aman Lal
 * Student Id - 791650
 * Email Id - amanl@student.unimelb.edu.au
 */

import java.util.Comparator;

public class AlphabeticalComparator implements Comparator<Player> { // compares
																	// players
																	// alphabetically

	@Override
	public int compare(Player player1, Player player2) {
		return player1.getUsername().compareToIgnoreCase(player2.getUsername());
	}

}
