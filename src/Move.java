/* TicTacToe - submission Aman Lal
 * Student Id - 791650
 * Email Id - amanl@student.unimelb.edu.au
 */

public class Move {	

	private char rowNumber;	//represents the rowNumber
	private char columnNumber;	// represents the columnNumber

	public char getRowNumber() {	// accessors and mutators
		return rowNumber;
	}

	public void setRowNumber(char rowNumber) {
		this.rowNumber = rowNumber;
	}

	public char getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(char columnNumber) {
		this.columnNumber = columnNumber;
	}

	public void copyMethod(Move m) {
		this.rowNumber = m.rowNumber;
		this.columnNumber = m.columnNumber;
	}
}
