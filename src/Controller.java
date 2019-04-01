import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

public class Controller implements ActionListener, KeyListener{

	private Model model;
	private View view;
	private boolean buttonTriggered;
	private Timer time;
	private Action drawAction; // Holds the loop actions.
	
	public Controller(){
		view = new View();
		view.addActionListener(this);
		view.addKeyListener(this);
		buttonTriggered = false;
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
	}
	
        //run the simulation
	public void start(){
		drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
    			 //increment the x and y coordinates, alter direction if necessary
    			model.updateLocationAndDirection();
    			
    			//update the view
    			view.update(model.getX(), model.getY(), model.getDirect());
    		}
    	};
		time = new Timer(50, drawAction);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!buttonTriggered) {
			time.start();
            buttonTriggered = true;
        } else if (buttonTriggered) {
            time.stop();
            buttonTriggered = false;
        }
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'f') {
			view.animateFire(model.getDirect());
		}
		else if(e.getKeyChar() == 'j') {
			view.animateJump(model.getDirect());
		}
		model.updateLocationAndDirection();
		view.update(model.getX(), model.getY(), model.getDirect());
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}
}
