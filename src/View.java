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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel {
	final int frameCount = 10;
    final int fireCount = 4;
    final int jumpCount = 8;
    boolean fire = false;
    boolean jump = false;
    int picNum = 0;
    int action = 0;
    JButton btn = new JButton("Start/Stop");
    BufferedImage[][] pics;
    BufferedImage[] pics2 = new BufferedImage[fireCount];
    BufferedImage[] pics3 = new BufferedImage[jumpCount];
    final static int frameWidth = 800;
    final static int frameHeight = 500;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    JFrame frame;
    String[] directions = {"north","northeast","east","southeast","south","southwest","west","northwest"};
    
    //instantiates frame and initializes the pics array
    public View() {
    	frame = new JFrame();
    	btn.setFocusable(false);
        this.add(btn);
        frame.getContentPane().add(this);
        frame.setBackground(Color.gray);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
        
    	String[] arrOfStr = {"forward_north", "forward_northeast", "forward_east", "forward_southeast",
                "forward_south", "forward_southwest", "forward_west", "forward_northwest"};
    	BufferedImage[] img = createImage(arrOfStr);
        pics = new BufferedImage[10][arrOfStr.length];
        int count = 0;
        for (BufferedImage curImg : img) {
            for(int i = 0; i < frameCount; i++) {
                pics[i][count] = curImg.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
            }
            count ++;
        }
        
    }
        
    //adds controller as action listener
	public void addActionListener(Controller controller) {
		btn.addActionListener(controller);
	}
	
	//adds controller as key listener
	public void addKeyListener(Controller controller) {
		frame.addKeyListener(controller);
	}
    
	//
    public void paint(Graphics g) {}
    
    //chooses the nest picture to display
    public void update(int x, int y, int dir) {
    	//System.out.println(x +" " + y + " " + dir + " " + this.getGraphics());
    	if (fire == true) {
    		if(action == fireCount) {
    			picNum =0;
    		}
	    	picNum = (picNum + 1) % fireCount;
    		this.getGraphics().drawImage(pics2[picNum], x, y, Color.gray, this);
    		action-=1;
    		if (action == 0) {
    			fire = false;
    		}
    	}
    	else if (jump == true) {
    		if(action == jumpCount) {
    			picNum =0;
    		}
	    	picNum = (picNum + 1) % jumpCount;
    		this.getGraphics().drawImage(pics3[picNum], x, y, Color.gray, this);
    		action-=1;
    		if (action == 0) {
    			jump = false;
    		}
    		
    	}
    	else if (action ==0) {
	    	picNum = (picNum + 1) % frameCount;
	    	this.getGraphics().drawImage(pics[picNum][dir], x, y, Color.gray, this);
    	}
    }
    
    //loads the images for the fire animation
    public void animateFire(int dir) {
    	fire = true;
		BufferedImage img2 = createImage("images.orc/orc_fire_"+directions[dir]+".png");
		for(int i = 0; i < fireCount; i++) {
			action+=1;
			pics2[i] = img2.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
		}
    }
    
    //loads the images for the jump animation
    public void animateJump(int dir) {
    	jump = true;
		BufferedImage img3 = createImage("images.orc/orc_jump_"+directions[dir]+".png");
		for(int i = 0; i < jumpCount; i++) {
			action+=1;
			pics3[i] = img3.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
		}
    }
    
    //returns a buffered image for a given file name if it exists
    private BufferedImage createImage(String s){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(s));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    //returns an array of buffered images for an array of file names if they exist
    private BufferedImage[] createImage(String[] strArr){
        //for (String str : strArr) System.out.println(str);
        BufferedImage[] bufferedImage = new BufferedImage[strArr.length];
        String path = "images.orc/orc_";
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
	//---------------------------Draw Panel--------------------------------------


}