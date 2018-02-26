/**
 * @author strickke
 *  
 */

public class GameMaster {

	private Board mBoard;
	private Position goalPosArray[];
	private static final int MAX_GOALS = 32;
	private int numGoals = 0;
	private int goalsOccupied = 0;

	GameMaster(Board board) {
		mBoard = board;
		setGoalSpaces();
	}

	public void setGoalSpaces() {
		goalPosArray = new Position[MAX_GOALS];
		Position pos = new Position();

		numGoals = 0;
		goalsOccupied = 0;

		int index = 0;

		for (pos.row = 0; pos.row < mBoard.numRows(); pos.row++) {
			for (pos.col = 0; pos.col < mBoard.numCols(); pos.col++) {
				char val = mBoard.getCell(pos);
				if (val == '+') {
					goalPosArray[index] = new Position(pos.row, pos.col);
					index++;
				}
			}
		}
		numGoals = index;
	}

	public boolean isGoalSpace(Position pos) {
		boolean isGoal = false;

		for (int i = 0; i < numGoals; i++) {
			if (goalPosArray[i].equals(pos))
				isGoal = true;
		}
		return isGoal;
	}

	public boolean treasureMove(Position pos, int direction) {
		boolean moved = false;

		Position newPos = pos.newPosition(direction);

		if (newPos.row >= 0 && newPos.row < mBoard.numRows()) {
			if (newPos.col >= 0 && newPos.col < mBoard.numCols()) {
				char val = mBoard.getCell(newPos);
				char tval = mBoard.getCell(pos);
				if (val == '.') {
					if (tval == '$')
						mBoard.setCell(pos, '.');
					else
						mBoard.setCell(pos, '+');
					mBoard.setCell(newPos, '$');
					moved = true;
				}
				if (val == '+') {
					if (tval == '$')
						mBoard.setCell(pos, '.');
					else
						mBoard.setCell(pos, '+');
					mBoard.setCell(newPos, '*');
					moved = true;
				}
			}
		}

		if (moved) {
			if (isGoalSpace(pos))
				goalsOccupied--;
			if (isGoalSpace(newPos))
				goalsOccupied++;
		}
		return moved;
	}

	public boolean playerMove(Position pos, int direction) {
		boolean moved = false;
		Position newPos = pos.newPosition(direction);

		// Bounds check row
		if (newPos.row >= 0 && newPos.row < mBoard.numRows()) {
			// Bounds check column
			if (newPos.col >= 0 && newPos.col < mBoard.numCols()) {
				char val = mBoard.getCell(newPos);
				char pval = mBoard.getCell(pos);
				if (val == '.') {
					if (pval == 'p')
						mBoard.setCell(pos, '.');
					else
						mBoard.setCell(pos, '+');
					mBoard.setCell(newPos, 'p');
					moved = true;
				}
				if (val == '+') {
					if (pval == 'p')
						mBoard.setCell(pos, '.');
					else
						mBoard.setCell(pos, '+');
					mBoard.setCell(newPos, 'P');
					moved = true;
				}
				if (val == '$') {
					Position treasurePos = pos.newPosition(direction);
					if (treasureMove(treasurePos, direction)) {
						if (pval == 'p')
							mBoard.setCell(pos, '.');
						else
							mBoard.setCell(pos, '+');
						mBoard.setCell(newPos, 'p');
						moved = true;
					}
				}
				if (val == '*') {
					Position treasurePos = pos.newPosition(direction);
					if (treasureMove(treasurePos, direction)) {
						if (pval == 'p')
							mBoard.setCell(pos, '.');
						else
							mBoard.setCell(pos, '+');
						mBoard.setCell(newPos, 'P');
						moved = true;
					}
				}
			}
		}
		return moved;
	}

	public boolean allGoalsFilled() {
		return goalsOccupied == numGoals;
	}

	public void nextLevel() {
		setGoalSpaces();
	}

}