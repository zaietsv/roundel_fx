package zaietsv.task2;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Test application for Ring.class
 */

public class RingApplication extends Application {

		
	@Override
	public void start(Stage stage) throws Exception {
    	
		Group group = new Group(new Ring(300, 250, 200));
		group.getChildren().add(new Ring(300, 250, 100));
		group.getChildren().add(new Ring(300, 250, 300));
    	Scene scene = new Scene(group);
    	scene.setFill(Color.GREY);
    	
        stage.setTitle("Hello, RingApplication!"); 
        stage.setScene(scene);
        stage.setWidth(600); 
        stage.setHeight(620);
        stage.show();
	}
	
	 public static void main(String[] args) {
	        Application.launch(args);
	    }
}