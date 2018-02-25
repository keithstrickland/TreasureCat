import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/*
 * Created on Feb 8, 2004
 */

/**
 * Load a board level definition from a file.
 * 
 * @author Keith Strickland
 */
public class LevelLoader {
	
	private static final int numLevels = 50;
	private static final String basePath = "src/resources/levels/";
	
	/**
	 * The constructor builds all of the specified
	 * levels and stores them in an StringBuilder array.
	 */
	public LevelLoader() {
	}

    /**
     * Load the level definition from a file and return
     * it as a StringBuilder object.
     *
     * @param levelNum The level to return.
     * @return A StringBuilder object containing the level initial state.
     */
	private StringBuilder loadLevelFromFile(int levelNum) {
		StringBuilder level = new StringBuilder();
		String fileName = "level_" + levelNum + ".txt";
		String levelFile = basePath + fileName;

        try (BufferedReader br = new BufferedReader(new FileReader(levelFile)))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                level.append(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return level;
	}
	
	/**
	 * Return the StringBuilder object containing
	 * the board layout for the specified level.
	 * 
	 * @param levelNum The level to return.
	 * @return A StringBuilder containing the board initial state.
	 */
	public StringBuilder getLevel(int levelNum)
	{			
		return loadLevelFromFile(levelNum);
	}

}
