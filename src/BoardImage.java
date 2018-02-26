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
	
	public void setBoard(Board board)
	{
		mBoard = board;
		boardCopy = board.getAllCells();
		goalBoard.setAllCells(boardCopy);			
	}
	
	public void draw(Graphics g)
	{
		Position pos = new Position();
		Position pixelPos = new Position();
		char symbol;
		Image image, bgimage;
		
		// First draw the goal spaces...
		for (pos.row = 0; pos.row < goalBoard.numRows(); pos.row++) {
			for (pos.col = 0; pos.col < goalBoard.numCols(); pos.col++) {
				symbol = goalBoard.getCell(pos);				
				if (symbol == '+')
				{
					image = iMap.getImage(symbol);
					pixelPos = calculateXY(pos);
					g.drawImage(image, pixelPos.col, pixelPos.row, null);
				}
			}
		}
		
		// Then draw the rest of the board.
		for (pos.row = 0; pos.row < mBoard.numRows(); pos.row++) {
			for (pos.col = 0; pos.col < mBoard.numCols(); pos.col++) {
				symbol = mBoard.getCell(pos);
				if (symbol == '.' || symbol == '#')
				{
					image = iMap.getImage(symbol);
					pixelPos = calculateXY(pos);
					g.drawImage(image, pixelPos.col, pixelPos.row, null);
				}
				if (symbol == 'p' || symbol == '$')
				{
					image = iMap.getImage(symbol);
					bgimage = iMap.getImage('.');
					pixelPos = calculateXY(pos);
					g.drawImage(bgimage, pixelPos.col, pixelPos.row, null);
					g.drawImage(image, pixelPos.col, pixelPos.row, null);
				}
				if (symbol == 'P')
				{
					image = iMap.getImage('p');
					bgimage = iMap.getImage('+');
					pixelPos = calculateXY(pos);
					g.drawImage(bgimage, pixelPos.col, pixelPos.row, null);
					g.drawImage(image, pixelPos.col, pixelPos.row, null);
				}
                if (symbol == '*')
                {
                    image = iMap.getImage('$');
                    bgimage = iMap.getImage('+');
                    pixelPos = calculateXY(pos);
                    g.drawImage(bgimage, pixelPos.col, pixelPos.row, null);
                    g.drawImage(image, pixelPos.col, pixelPos.row, null);
                }
			}
		}						
	}

}
