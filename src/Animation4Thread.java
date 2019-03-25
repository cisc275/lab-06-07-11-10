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
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Animation4Thread extends JFrame{

    final int frameCount = 10;
    BufferedImage[] pics;
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
    //KeyStroke key = new KeyStroke(); 
    
    public Animation4Thread() {
    	btn.setText("Stop/Start");
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
    	//drawPanel.add(new JButton("test"));
    	this.addKeyListener(new KeyListener() {
    		public void keyTyped(KeyEvent e) {
    			// TODO Auto-generated method stub
    			System.out.println("typed");

    		}

    		public void keyPressed(KeyEvent e) {
    			// TODO Auto-generated method stub
    			System.out.println("pressed");
    			
    		}

    		public void keyReleased(KeyEvent e) {
    			// TODO Auto-generated method stub
    			System.out.println("released");

    		}
    	});
    	add(drawPanel);
    	BufferedImage img = createImage();
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
	    	picNum = (picNum + 1) % frameCount;
	    	g.drawImage(pics[picNum], xloc+=xIncr, yloc+=yIncr, Color.gray, this);
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
    private BufferedImage createImage(){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File("images.orc/orc_forward_southeast.png"));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }


}
/*
class KeyStroke implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
*/