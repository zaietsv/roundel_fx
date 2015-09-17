package zaietsv.task2;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Test application for Sector.class
 */
public class SectorApplication extends Application {

		
	@Override
	public void start(Stage stage) throws Exception {
    	
		Group group = new Group();
    	Scene scene = new Scene(group);
    	scene.setFill(Color.GREY);
    	for (int i = 0; i <= 27; i++) {
    		Sector sector = new Sector(20*i, 20*i, 10*i, 20*i);
    		group.getChildren().add(sector);
    	}
       
        stage.setTitle("Hello, SectorApplication!"); 
        stage.setScene(scene);
        stage.setWidth(600); 
        stage.setHeight(620);
        stage.show();
      
	}
	
	 public static void main(String[] args) {
	        Application.launch(args);
	    }
}