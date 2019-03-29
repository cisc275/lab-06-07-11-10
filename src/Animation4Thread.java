import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Animation4Thread extends JFrame{

    final int frameCount = 10;
    final int fireCount = 4;
    final int jumpCount = 8;
    boolean fire = false;
    boolean jump = false;
    BufferedImage[] pics;
    BufferedImage[] pics2 = new BufferedImage[fireCount];
    BufferedImage[] pics3 = new BufferedImage[jumpCount];
    int action = 0;
    int xloc = 100;
    int yloc = 100;
    final int xIncr = 1;
    final int yIncr = 1;
    final int picSize = 165;
    final int frameStartSize = 800;
    final int drawDelay = 30; //msec
    private static Timer timer;
    private boolean buttonTriggered;
 
    
    DrawPanel drawPanel = new DrawPanel();
    Action drawAction;
    JButton btn = new JButton();
    
    public Animation4Thread() {
    	btn.setText("Stop/Start");
    	btn.setFocusable(false);
    	btn.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!buttonTriggered) {
					timer.start();
                    buttonTriggered = true;
                } else if (buttonTriggered) {
                    timer.stop();
                    buttonTriggered = false;
                }
			}
    		
    	});
    	drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
    			drawPanel.repaint();
    		}
    	};
    	
    	drawPanel.add(btn);
    	this.addKeyListener(new KeyListener() {
    		Timer time;

    		
    		public void keyTyped(KeyEvent e) {
    			// TODO Auto-generated method stub
    			System.out.println("typed");
    			
    			

    		}

    		public void keyPressed(KeyEvent e) {
    			System.out.println("pressed");
    			if(e.getKeyChar() == 'f') {
    				fire = true;
    				System.out.println("f");
    				BufferedImage img2 = createImage("images.orc/orc_fire_southeast.png");
    				for(int i = 0; i < fireCount; i++) {
        				action+=1;
    					pics2[i] = img2.getSubimage(picSize*i, 0, picSize, picSize);
    				}
    			}
    			else if(e.getKeyChar() == 'j') {
    				jump = true;
    				System.out.println("j");
    				BufferedImage img3 = createImage("images.orc/orc_jump_southeast.png");
    				for(int i = 0; i < jumpCount; i++) {
    					action+=1;
    					pics3[i] = img3.getSubimage(picSize*i, 0, picSize, picSize);
    				}
    			}
    			
    			
    		}

    		public void keyReleased(KeyEvent e) {
    			System.out.println("released");

    		}
    	});
    	add(drawPanel);

    	BufferedImage img = createImage("images.orc/orc_forward_southeast.png");
    	pics = new BufferedImage[frameCount];//get this dynamically
    	for(int i = 0; i < frameCount; i++)
    		pics[i] = img.getSubimage(picSize*i, 0, picSize, picSize);
   
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(frameStartSize, frameStartSize);
    	setVisible(true);
    	pack();
    }
	
    @SuppressWarnings("serial")
	private class DrawPanel extends JPanel {
    	int picNum = 0;
    	
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.gray);
	    	if (fire == true) {
	    		if(action == fireCount) {
	    			picNum =0;
	    		}
		    	picNum = (picNum + 1) % fireCount;
	    		g.drawImage(pics2[picNum], xloc+=xIncr, yloc+=yIncr, Color.gray, this);
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
	    		g.drawImage(pics3[picNum], xloc+=xIncr, yloc+=yIncr, Color.gray, this);
	    		action-=1;
	    		if (action == 0) {
	    			jump = false;
	    		}
	    		
	    	}
	    	else if (action ==0) {
		    	picNum = (picNum + 1) % frameCount;
		    	g.drawImage(pics[picNum], xloc+=xIncr, yloc+=yIncr, Color.gray, this);
	    	}
		}


		public Dimension getPreferredSize() {
			return new Dimension(frameStartSize, frameStartSize);
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Animation4Thread a = new Animation4Thread();
				timer = new Timer(a.drawDelay, a.drawAction);
			}
		});
	}
    
    //Read image from file and return
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


}
