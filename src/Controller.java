import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

public class Controller implements ActionListener {

	private Model model;
	private View view;
	private boolean buttonTriggered;
	private Timer time;
	private Action drawAction; // Holds the loop actions.
	
	public Controller(){
		view = new View();
		view.addActionListener(this);
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
		System.out.println("Hit");
		if (!buttonTriggered) {
			time.start();
            buttonTriggered = true;
        } else if (buttonTriggered) {
            time.stop();
            buttonTriggered = false;
        }
		
	}
}
