package zaietsv.task2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;


import javafx.stage.Stage;

/**
 * Main class for testing Task2.
 * @author V.Zaiets
 */
public class MainRingApplication extends Application {
	
	final double CENTER_X = 250, CENTER_Y = 250;
	final double RADIUS = 150;
	
	@Override
	public void start(Stage stage) throws Exception {
		Group group = new Group();
		
		//coloured ring with optional roundels
		Ring ring = new Ring(CENTER_X, CENTER_Y, RADIUS);
		group.getChildren().add(ring);
		
		//Delta pointer to point zero and rotate ring
		DeltaPointer deltaPointer = new DeltaPointer(ring);
		group.getChildren().add(deltaPointer);
		
		//Button "+" to add Roundels
		Button addButton = new AddButton(ring);
		group.getChildren().add(addButton);
			
		//Button "-" to remove Roundels
		Button removeButton = new RemoveButton(ring);
		group.getChildren().add(removeButton);
		
		//Scene for the current stage
		Scene scene = new Scene(group);
    	scene.setFill(Color.GREY);
       
    	//name and dimensions
        stage.setTitle("Hello, RingHSBApplication!"); 
        stage.setScene(scene);
        stage.setWidth(CENTER_X * 2); 
        stage.setHeight(CENTER_Y * 3);
        stage.show();
		
	}
	
	 public static void main(String[] args) {
	        Application.launch(args);
	    }
}