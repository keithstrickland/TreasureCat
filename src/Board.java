/**
 * 
 * Holds the state of each cell
 * on a board, stored as character symbols.
 * 
 * @author Keith Strickland
 *
 */

/**
 * @author Keith Strickland
 * 
 * Class Board
 *  
 */
/**
 * @author Keith Strickland
 * 
 * Class Board
 *
 */
public class Board {

	private char board[][];

	private int rows, cols;

	private int size;

	// private StringBuilder sb;

	/**
	 * Initialiaze an new board and its components.
	 * 
	 * @param numRows The number of rows the board is to have.
	 * @param numCols The number of columns the board is to have.
	 */
	private void init(int numRows, int numCols) {
		board = new char[numRows][numCols];
		rows = numRows;
		cols = numCols;
		size = rows * cols;
	}

	/**
	 * Create a Board with # rows and # columns.
	 * 
	 * @param numRows
	 * @param numCols
	 */
	public Board(int numRows, int numCols) {
		init(numRows, numCols);
	}

	/**
	 * Create a board with # rows and # columns, and initialize its contents to
	 * contain the characters in a StringBuilder.
	 * 
	 * @param numRows
	 *            The number of rows on the board
	 * @param numCols
	 *            The number of columns on the board
	 * @param buffer
	 *            The initial baord state contained in a StringBuilder.
	 */
	public Board(int numRows, int numCols, StringBuilder buffer) {
		init(numRows, numCols);
		setAllCells(buffer);
	}

	/**
	 * The size of the board (rows*columns).
	 * 
	 * @return Board size.
	 */
	public int size() {
		return size;
	}

	/**
	 * Number of rows on the board.
	 * 
	 * @return the number of rows. 
	 */
	public int numRows() {
		return rows;
	}

	/**
	 * The number of columns on the board.
	 * 
	 * @return The number of columns.
	 */
	public int numCols() {
		return cols;
	}

	/**
	 * Returns the character stored at [row][column].
	 * 
	 * @param pos X,Y cell pos to get
	 * @return Character at X,Y position
	 */
	public char getCell(Position pos) {
		return board[pos.row][pos.col];
	}

	/**
	 * Return the content of a cell up, down, left or right of the given X,Y
	 * position.
	 * 
	 * @param pos
	 *            Board X,Y position
	 * @param direction
	 *            UP, DOWN, LEFT, or RIGHT
	 * @return Character at calculated X,Y position
	 */
	public char getCell(Position pos, int direction) {
		Position offset = pos.newPosition(direction);
		return board[offset.row][offset.col];
	}

	/**
	 * Get the content of every cell on the board.
	 * 
	 * @return Board content as a StringBuilder
	 */
	public StringBuilder getAllCells() {
		StringBuilder sb = new StringBuilder(this.size);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				sb.append(board[i][j]);
			}
		}
		return sb;
	}

	/**
	 * Set the content of a cell space.
	 * 
	 * @param pos
	 *            The board X,Y position.
	 * @param val
	 *            Value to store at specified cell.
	 */
	public void setCell(Position pos, char val) {
		board[pos.row][pos.col] = val;
	}

	/**
	 * Set the content of a cell space calculated from a direction offset.
	 * 
	 * @param pos
	 *            The origin cell X,Y position
	 * @param direction
	 *            the offset direction (UP, DOWN, LEFT, or RIGHT).
	 * @param val
	 *            The value to store at the specified cell.
	 */
	public void setCell(Position pos, int direction, char val) {
		Position offset = pos.newPosition(direction);
		board[offset.row][offset.col] = val;
	}

	/**
	 * Set every cell on the board using the contents of the passed in
	 * StringBuilder object.
	 * 
	 * @param buffer
	 *            The buffer containing the board contents.
	 */
	public void setAllCells(StringBuilder buffer) {
		int current_cell = 0;
		// Arrays.fill(board, null);
		if (buffer.length() >= size) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					board[i][j] = buffer.charAt(current_cell);
					current_cell++;
				}
			}
		}
	}

	/**
	 * Returns first position of first occurence of the supplied character
	 * symbol.
	 * 
	 * @param symbol
	 *            The symbol to look for.
	 * 
	 * @return The cell position containing the specified character
	 * symbol. -1,-1 is returned if not match is found. 
	 *
	 */
	public Position findCell(char symbol) {
		Position pos = new Position();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j] == symbol) {
					pos.row = i;
					pos.col = j;
					break;
				}
			}
		}
		return pos;
	}

	/**
	 * Compares this board with another board and
	 * determines if their contents are an exact match.
	 * 
	 * @param otherBoard The board to compare.
	 * @return If the boards match.
	 */
	public boolean compare(Board otherBoard) {
		boolean result = true;
		Position pos = new Position();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				pos.row = i;
				pos.col = j;
				if (board[i][j] != otherBoard.getCell(pos)) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Main (test) function for Board class.
	 * @param args Parameter not used
	 */
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("#ABCD#");
		Board test = new Board(2, 3, sb);

		Position pos = new Position();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				pos.row = i;
				pos.col = j;
				System.out.println(test.getCell(pos));
			}

		}
		pos.row = 1;
		pos.col = 2;
		System.out.println(test.getCell(pos, Position.UP));

	}
}