package zaietsv.task2;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Roundel is a rounded cursor for ring scale.
 * 
 * */
public class Roundel extends Group {
	
	private Ring ring;	
	private int location;
	
	//constructor
	Roundel(Ring ring) {
	
		this.ring = ring;
		this.location = 0;
		
		getChildren().add(new Circle(0.6*Sector.HEIGHT));
		getChildren().add(new Circle(0.5*Sector.HEIGHT));
		getChildren().add(new Circle(0.4*Sector.HEIGHT));
		relocate(((Sector)ring.getSectors().getChildren().get(location)).getLayoutX() 
				-Sector.HEIGHT/2-Sector.WIDTH, 
				((Sector)ring.getSectors().getChildren().get(location)).getLayoutY() - Sector.WIDTH);
		setPassive();
		setColor();
		
		
		//roundel moves by mouse dragging
		addEventHandler(MouseDragEvent.MOUSE_DRAGGED, new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				System.out.println("MouseDragEvent: move();");
				move(true);
			}
		});
		
		//roundel turns on to active state by mouse pressing
		addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				System.out.println("MouseEvent: setActive();");
				setActive();
					
			}
		});
	}
	
	int getLocation() {
		return location;
	}
	
	void setLocation(int location) {
		this.location = location;
	}

	//moving a roundel over the circle
	void move(boolean direction) {
		if (direction) {
			location--;
			if (location < 0) {
				location = Ring.DEG;
			}
		} else {
			location++;
			if (location > Ring.DEG) {
				location = 0;
			}
		}
		
		relocate(((Sector)ring.getSectors().getChildren().get(location)).getLayoutX() 
				- ((Circle)this.getChildren().get(0)).getRadius(), 
				((Sector)ring.getSectors().getChildren().get(location)).getLayoutY());
		setColor();
	}
	
	//changing roundel`s color according to current position
	void setColor() {
		double hue = ((Color)((Sector)ring.getSectors().getChildren().get(location)).getFill()).getHue();
		((Circle)getChildren().get(2)).setFill(Color.hsb(hue, 1, 1));
		System.out.println("hue: " + hue);
	}
	
	//setting up a roundel in an active state
	private void setActive() {
		
		for (Node n : ring.getRoundels().getChildren()) {
			((Roundel)n).setPassive();
		}
		
		((Circle)getChildren().get(0)).setFill(Color.BLACK);
		((Circle)getChildren().get(1)).setFill(Color.WHITE);
	}
			
	//setting up a roundel in a passive state
	private void setPassive() {
			
		((Circle)getChildren().get(0)).setFill(Color.WHITE);
		((Circle)getChildren().get(1)).setFill(Color.BLACK);
	}	
}
