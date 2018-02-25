/**
 * @author Keith Strickland
 *
 */
public class Position {
	
	public int row = -1;
	public int col = -1;
	
	/** Predefined values for DIRECTION
	 * 
	 */
	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int LEFT = 4;
	
	public Position()
	{
	}
	
	public boolean notValid(){
		return row == -1;
	}
	
	public Position(int x, int y)
	{
		this.row = x;
		this.col = y;
	}
	
	public boolean equals (Position other)
	{
		return (this.row == other.row) && (this.col == other.col);
	}
	
	public void copy(Position other)
	{
		this.row = other.row;
		this.col = other.col;
	}
	
	public void printPos()
	{
		System.out.print("Pos.row: " + this.row);
		System.out.println("  Pos.col: " + this.col);
	}
	
	public Position newPosition(int direction)
	{
		Position pos = new Position(row, col);
		
		switch (direction) {
			case (UP): pos.row--; break;
			case (RIGHT): pos.col++; break;
			case (DOWN): pos.row++; break;
			case (LEFT): pos.col--; break;
		}		
				
		return pos;
	}
	
}
