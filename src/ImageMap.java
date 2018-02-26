
/**
 * @author Keith
 *
 */

import java.awt.*;

public class ImageMap {
	
	char symbols[];
	Image images[];
	
	ImageMap(int mapSize)
	{
		images = new Image[mapSize];
		symbols = new char[mapSize];		
	}
	
	public void associateImage(Image image, char symbol, int key)
	{
		images[key] = image;
		symbols[key] = symbol;
	}
	
	public int findIndex(char symbol)
	{
		int result = 0;
		for (int x = 0; x < symbols.length; x++) {
			if (symbols[x] == symbol)
				result = x;
		}
		return result;
	}

	public Image getImage(int index)
    {
        return images[index];
	}

	public Image getImage(char symbol)
	{
		Image foundImage = null; 
		int index = findIndex(symbol);
		if (index >= 0 ){
			foundImage = images[index];
		} 
		return foundImage;
	}
}
