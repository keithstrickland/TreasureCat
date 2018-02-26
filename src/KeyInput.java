import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*
 * Created on Feb 9, 2005
 */

/**
 * @author Keith Strickland
 * 
 * Class KeyInput
 *
 */
public class KeyInput implements KeyListener {
	
	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int LEFT = 4;
	
	public Main _gameMain;
	public boolean moveKeysEnabled = false;

	private boolean firstLPressed  = false;
    private String  levelEntered   = "";

    private void changeLevel(int newLevel, boolean advanceLevel){
        moveKeysEnabled = false;
        if (newLevel != 0 ){
            _gameMain.gameLevel = newLevel;
        }
        else if (advanceLevel) {
            _gameMain.gameLevel++;
        } else {
            _gameMain.gameLevel--;
        }
        _gameMain.loadLevel(_gameMain.gameLevel);
        moveKeysEnabled = true;
    }

    private boolean isNumnericKeypress(int keyCode) {
        boolean isNumeric = false;
        if (keyCode == KeyEvent.VK_1) {
            isNumeric = true;
        }
        if (keyCode == KeyEvent.VK_2) {
            isNumeric = true;
        }
        if (keyCode == KeyEvent.VK_3) {
            isNumeric = true;
        }
        if (keyCode == KeyEvent.VK_4) {
            isNumeric = true;
        }
        if (keyCode == KeyEvent.VK_5) {
            isNumeric = true;
        }
        if (keyCode == KeyEvent.VK_6) {
            isNumeric = true;
        }
        if (keyCode == KeyEvent.VK_7) {
            isNumeric = true;
        }
        if (keyCode == KeyEvent.VK_8) {
            isNumeric = true;
        }
        if (keyCode == KeyEvent.VK_9) {
            isNumeric = true;
        }
        if (keyCode == KeyEvent.VK_0) {
            isNumeric = true;
        }
        return isNumeric;
    }

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		// exit the program
		if (keyCode == KeyEvent.VK_ESCAPE | keyCode == KeyEvent.VK_Q) {
			_gameMain.stop= true;
		}
		else if (keyCode == KeyEvent.VK_R) {
			moveKeysEnabled = false;
			_gameMain.loadLevel(_gameMain.gameLevel);
			moveKeysEnabled = true;
		}
		else if (keyCode == KeyEvent.VK_N) {
			if (_gameMain.gameLevel != _gameMain.lastlevel) {{
                    changeLevel(0, true);
				}
			}
		}
		else if (keyCode == KeyEvent.VK_P) {
			if (_gameMain.gameLevel != 1) {
                changeLevel(0, false);
			}
		}
        else if (keyCode == KeyEvent.VK_L) {
            if (firstLPressed) {
                try {
                    firstLPressed = false;
                    int newLevel = Integer.parseInt(levelEntered);
                    levelEntered = "";
                    changeLevel(newLevel, true);
                } catch (Exception exc) {
                    // do nothing
                }
            } else {
                firstLPressed = true;
            }
        }
        else if (isNumnericKeypress(keyCode)) {
		    if (firstLPressed){
                levelEntered += KeyEvent.getKeyText(e.getKeyCode());
            }
        }
		else {
			if (moveKeysEnabled){
				if (keyCode == KeyEvent.VK_UP) _gameMain.updatePosition(UP);
				if (keyCode == KeyEvent.VK_RIGHT) _gameMain.updatePosition(RIGHT);
				if (keyCode == KeyEvent.VK_DOWN) _gameMain.updatePosition(DOWN);
				if (keyCode == KeyEvent.VK_LEFT) _gameMain.updatePosition(LEFT);
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		// make sure the key isn't processed for anything else
		e.consume();

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent e) {		
		
		// this is called after the key is released - ignore it
		// make sure the key isn't processed for anything else
		e.consume();		// TODO Auto-generated method stub

	}

}
