/* TicTacToe - submission Aman Lal
 * Student Id - 791650
 * Email Id - amanl@student.unimelb.edu.au
 */

import java.util.Comparator;

public class RatioComparator implements Comparator<Player> {

	public int compare(Player o1, Player o2) {
		float compareWinRatioPlayerNew = (float) ((float) ((Player) o1)
				.getGamesWon() / (float) ((Player) o1).getGamesPlayed());
		float compareWinRatioPlayerOld = (float) (((Player) o2).getGamesWon() / (float) ((Player) o2)
				.getGamesPlayed());
		float compareDrawRatioPlayerNew = (float) ((float) ((Player) o1)
				.getGamesDrawn() / (float) ((Player) o1).getGamesPlayed());
		float compareDrawRatioPlayerOld = (float) (((Player) o2)
				.getGamesDrawn() / (float) ((Player) o2).getGamesPlayed());

		if (Double.isNaN(compareWinRatioPlayerNew)) { // to prevent divide by
														// zero error
			compareWinRatioPlayerNew = 0;
		}
		if (Double.isNaN(compareWinRatioPlayerOld)) {
			compareWinRatioPlayerOld = 0;
		}
		if (Double.isNaN(compareDrawRatioPlayerNew)) {
			compareDrawRatioPlayerNew = 0;
		}
		if (Double.isNaN(compareDrawRatioPlayerOld)) {
			compareDrawRatioPlayerOld = 0;
		}
		float result = (float) compareWinRatioPlayerOld
				- compareWinRatioPlayerNew;
		if (result == 0) // if players have same win ratio compare on draw ratio
			result = (float) compareDrawRatioPlayerOld
					- compareDrawRatioPlayerNew;
		if (result == 0) // if players have same win and draw ratio compare
							// alphabetically
			result = o1.getUsername().compareToIgnoreCase(o2.getUsername());

		return Math.round((float) (result) * 100);
	}

}
