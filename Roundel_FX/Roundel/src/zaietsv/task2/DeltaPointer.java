package zaietsv.task2;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * DeltaPointer points a zero-point of the ring.
 * While mouse is dragged, rotates the ring.
 * */

public class DeltaPointer extends Polygon {
	
		public DeltaPointer(final Ring ring) {
		
		
		super((ring.getCenterX()- Sector.HEIGHT/2), ring.getCenterY()-ring.getRadius()- Sector.HEIGHT - Sector.WIDTH, 
				(ring.getCenterX()+ Sector.HEIGHT/2), ring.getCenterY()-ring.getRadius()- Sector.HEIGHT - Sector.WIDTH, 
				ring.getCenterX(), ring.getCenterY()-ring.getRadius()- Sector.HEIGHT - Sector.WIDTH + Sector.HEIGHT);
		setFill(Color.LIGHTGRAY);
		addEventHandler(MouseDragEvent.MOUSE_DRAGGED, new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				System.out.println("MouseDragEvent: rotate()");
				ring.rotate(true);
			}
		});
	}
}
