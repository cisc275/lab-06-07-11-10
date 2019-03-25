/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel {
	final int frameCount = 10;
    int picNum = 0;
    BufferedImage[][] pics;
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    
    public View() {
    	JFrame frame = new JFrame();
        frame.getContentPane().add(this);
        frame.setBackground(Color.gray);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
    	String[] arrOfStr = {"forward_north", "forward_northeast", "forward_east", "forward_southeast",
                "forward_south", "forward_southwest", "forward_west", "forward_northwest"};
        BufferedImage[] img = createImage(arrOfStr);
        pics = new BufferedImage[10][arrOfStr.length];
        //System.out.println(img.length);
        int count = 0;
        for (BufferedImage curImg : img) {
            for(int i = 0; i < frameCount; i++) {
                pics[i][count] = curImg.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
            }
            count ++;
        }
        
    }
    
    public void paint(Graphics g) { 
    	//g.drawImage(pics[picNum][3], 0, 0, Color.gray, this);
    }
    
    public void update(int x, int y, int dir) {
    	//System.out.println(x +" " + y + " " + dir + " " + this.getGraphics());
    	picNum = (picNum + 1 )% frameCount;
    	this.getGraphics().drawImage(pics[picNum][dir], x, y, Color.gray, this);
    }
    
    private BufferedImage[] createImage(String[] strArr){
        //for (String str : strArr) System.out.println(str);
        BufferedImage[] bufferedImage = new BufferedImage[strArr.length];
        String path = "images/orc/orc_";
        int count = 0;
        for (String str : strArr) {
            //System.out.println(path.concat(str).concat(".png"));
            //System.out.println(strArr.length);
            try {
                
                bufferedImage[count] = ImageIO.read(new File(path.concat(str).concat(".png")));
                count ++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bufferedImage;
        
        // TODO: Change this method so you can load other orc animation bitmaps
    }
    
    //---------------------------------G&S---------------------------------------
	public int getPicNum() {
		return picNum;
	}
	public void setPicNum(int picNum) {
		this.picNum = picNum;
	}
	public BufferedImage[][] getPics() {
		return pics;
	}
	public void setPics(BufferedImage[][] pics) {
		this.pics = pics;
	}
	public int getFrameCount() {
		return frameCount;
	}
	public static int getFrameWidth() {
		return frameWidth;
	}
	public static int getFrameHeight() {
		return frameHeight;
	}
	public static int getImageWidth() {
		return imgWidth;
	}
	public static int getImageHeight() {
		return imgHeight;
	}

}