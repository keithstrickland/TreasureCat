/*
 * Created on Feb 7, 2004
 */

/**
 * @author Keith Strickland
 * 
 * Class Main
 *  
 */

import java.awt.*;
import javax.swing.ImageIcon;

import graphics.ScreenManager;

// Main game class for
// "Evil Black Kitty and the Mutant Terror Squirrels vs. The World Game"
public class Main {

	public int gameLevel = 1;

	public int lastlevel = 5;

	public boolean stop = false;

	public KeyInput _keyInput;

	public void loadLevel(int level) {
		board.setAllCells(builder.getLevel(level));
		bImage.setBoard(board);
		gm.nextLevel();
	}

	public void updatePosition(int direction) {
		Position pos = board.findCell('p');
		if (pos.notValid()) {
			pos = board.findCell('P');
		}
		switch (direction) {
		case 1:
			gm.playerMove(pos, KeyInput.UP);
			break;
		case 2:
			gm.playerMove(pos, KeyInput.RIGHT);
			map.associateImage(player_right, 'p', 1);
			map.associateImage(player_right, 'P', 5);
			break;
		case 3:
			gm.playerMove(pos, KeyInput.DOWN);
			break;
		case 4:
			gm.playerMove(pos, KeyInput.LEFT);
			map.associateImage(player_left, 'p', 1);
			map.associateImage(player_left, 'P', 5);
			break;
		}

		// Check for level completion
		if (gm.allGoalsFilled()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
			}
			gameLevel++;
			loadLevel(gameLevel);
		}
	}

	private static final DisplayMode POSSIBLE_MODES[] = {
            new DisplayMode(1280, 1024, 32, 0),
			new DisplayMode(640, 480, 32, 0),
			new DisplayMode(640, 480, 24, 0),
			new DisplayMode(640, 480, 16, 0) };

	public void run() {
		screen = new ScreenManager();
		try {
			DisplayMode displayMode = screen
					.findFirstCompatibleMode(POSSIBLE_MODES);
			screen.setFullScreen(displayMode);
			loadImages();
			initBoard();
			Window window = screen.getFullScreenWindow();
			window.addKeyListener(_keyInput);
			gameLoop();
		} finally {
			screen.restoreScreen();
		}
	}

	public void gameLoop() {
		while (!stop) {

			// draw and update the screen
			Graphics2D g = screen.getGraphics();
			draw(g);
			g.dispose();
			screen.update();

			// take a nap
			try {
				Thread.sleep(20);
			} catch (InterruptedException ex) {
			}
		}
	}

	public void drawLines(Graphics g, int width, int height, int xmax, int ymax) {
		int xval, yval;

		g.setColor(Color.BLACK);

		for (int x = 0; x < 20; x++) {
			xval = (x * width);
			if (xval < 0)
				xval = 0;
			g.drawLine(xval, 0, xval, ymax);
		}

		for (int y = 0; y < 16; y++) {
			yval = (y * height);
			if (yval < 0)
				yval = 0;
			g.drawLine(0, yval, xmax, yval);
		}
	}

	public void draw(Graphics g) {
		// draw background
		g.drawImage(bgImage, 0, 0, null);

		// drawLines(g, pix_sz, pix_sz, 799, 599);

		bImage.draw(g);
	}

	public static void main(String[] args) {
		Main game = new Main();
		game.initialLevel = new Integer(1);
		game._keyInput = new KeyInput();
		game._keyInput._gameMain = game;
		game.run();
	}

  private int initialLevel;

	private static final int pix_sz = 32;

	private ScreenManager screen;

	private Board board;

	private BoardImage bImage;

	private GameMaster gm;

	private ImageMap map;

	private LevelLoader builder;

	private Image bgImage;

	private Image space_tile;

	private Image treasure_sprite;

	private Image wall_tile;

	private Image player_right;

	private Image player_left;

	private Image goal;

	private void loadImages() {
		// load images
		bgImage = loadImage("src/resources/images/background640_480.jpg");
		player_right = loadImage("src/resources/images/player_right.png");
		player_left = loadImage("src/resources/images/player_left.png");
		space_tile = loadImage("src/resources/images/space_tile.png");
		treasure_sprite = loadImage("src/resources/images/treasure_sprite.png");
		wall_tile = loadImage("src/resources/images/wall_tile.png");
		goal = loadImage("src/resources/images/goal_tile.png");
	}

	private void initBoard() {
		builder = new LevelLoader();
		board = new Board(16, 20, builder.getLevel(initialLevel));
		gm = new GameMaster(board);
		map = new ImageMap(7);
		map.associateImage(space_tile, '.', 0);      // Empty space tile
		map.associateImage(player_right, 'p', 1);    // Player sprite
		map.associateImage(treasure_sprite, '$', 2); // Treasure sprite
		map.associateImage(wall_tile, '#', 3);       // Wall tile
		map.associateImage(goal, '+', 4);            // Goal tile
		map.associateImage(player_right, 'P', 5);    // Player on Goal tile
		map.associateImage(treasure_sprite, '%', 6); // Treasure on Goal tile
		bImage = new BoardImage(board, map, pix_sz, pix_sz);
		_keyInput.moveKeysEnabled = true;
	}

	private Image loadImage(String fileName) {
		return new ImageIcon(fileName).getImage();
	}

}
