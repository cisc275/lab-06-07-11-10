/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

public class Model {

	String direction = "";
    int xloc = 0;
    int yloc = 0;
    int xChg = 1;
    int yChg = 1;
    int dir = 3;
    final int xIncr = 8;
    final int yIncr = 2;
    int imgWidth = 0;
    int imgHeight = 0;
    int frameWidth = 0;
    int frameHeight = 0;
	
	public Model(int x, int y, int imgW, int igH) {
		imgWidth = x;
		imgHeight = y;
		frameWidth = imgW;
		frameHeight = igH;
	}
	
	public void updateLocationAndDirection() {
		//System.out.println(frameWidth - imgWidth);
        if(xloc > (imgWidth - frameWidth) || xloc < 0) {
        	switch(dir) {
	        	case 1:
	        		dir = 7;
	        		break;
	        	case 3:
	        		dir = 5;
	        		break;
	        	case 5:
	        		dir = 3;
	        		break;
	        	case 7:
	        		dir = 1;
	        		break;
        	}
        	xChg = -1*xChg;
        	
        }
        if(yloc > (imgHeight - frameHeight) || yloc < 0) {

        	yChg = -1*yChg;
        	switch(dir) {
        		case 1:
        			dir = 3;
        			break;
        		case 3:
        			dir = 1;
        			break;
        		case 5:
        			dir = 7;
        			break;
        		case 7:
        			dir = 5;
        			break;
        	}
        }
        xloc+=xChg*xIncr;
        yloc+=yChg*yIncr;
	}

	public int getX() {
		return xloc;
	}

	public int getY() {
		return yloc;
	}
	
	public int getDirect() {
		return dir;
	}
	
}