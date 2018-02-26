/*
 * Created on Feb 7, 2004
 */

/**
 * @author Keith Strickland
 * 
 * Class BoardImage
 *
 */

import java.awt.*;

public class BoardImage {
	
	private Board mBoard;
	private Board goalBoard;
	private StringBuilder boardCopy;
	private ImageMap iMap;
	private int cellWidth = 0;
	private int cellHeight = 0;
	
	BoardImage(Board board, ImageMap map, int cell_height, int cell_width) {
		mBoard = board;
		boardCopy = board.getAllCells();
		goalBoard = new Board(mBoard.numRows(), mBoard.numCols(), boardCopy);
		iMap = map;
		cellHeight = cell_height;
		cellWidth = cell_width;
	}
	
	private Position calculateXY(Position pos)
	{
		Position pixelPos = new Position();
		pixelPos.row = pos.row * cellHeight;
		pixelPos.col = pos.col * cellWidth;
		
		return pixelPos;
	}


    private void drawImage(Graphics g, Position pos, char bgImageSymbol)
    {
        Image image = iMap.getImage(bgImageSymbol);
        Position pixelPos = calculateXY(pos);
        g.drawImage(image, pixelPos.col, pixelPos.row, null);
    }

	private void drawImages(Graphics g, Position pos, char bgImageSymbol, char imageSymbol )
    {
        drawImage(g, pos, bgImageSymbol);
        drawImage(g, pos, imageSymbol);
    }
	
	public void setBoard(Board board)
	{
		mBoard = board;
		boardCopy = board.getAllCells();
		goalBoard.setAllCells(boardCopy);			
	}
	
	public void draw(Graphics g)
	{
		Position pos = new Position();
		char symbol;
		
		// First draw the goal spaces...
		for (pos.row = 0; pos.row < goalBoard.numRows(); pos.row++) {
			for (pos.col = 0; pos.col < goalBoard.numCols(); pos.col++) {
				symbol = goalBoard.getCell(pos);				
				if (symbol == '+')
				{
                    drawImage(g, pos, symbol);
				}
			}
		}
		
		// Then draw the rest of the board.
		for (pos.row = 0; pos.row < mBoard.numRows(); pos.row++) {
			for (pos.col = 0; pos.col < mBoard.numCols(); pos.col++) {
				symbol = mBoard.getCell(pos);
				if (symbol == '.' || symbol == '#')
				{
                    drawImage(g, pos, symbol );
				}
				if (symbol == 'p' || symbol == '$')
				{
                    drawImages(g, pos,'.', symbol);
				}
				if (symbol == 'P')
				{
                    drawImages(g, pos,'+', 'p');
				}
                if (symbol == '*')
                {
                    drawImages(g, pos,'+', '$');
                }
			}
		}						
	}

}
