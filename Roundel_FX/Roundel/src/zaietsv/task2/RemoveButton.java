package zaietsv.task2;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * RemoveButton removes last added roundel from a specified ring
 * */

public class RemoveButton extends Button {
	
	RemoveButton(final Ring ring) {
		super("- / remove Roundel");
		relocate(ring.getCenterX()+ Sector.HEIGHT/2, ring.getCenterY()+ring.getRadius()+2 * Sector.HEIGHT);
		addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				System.out.println("MouseEvent: removeRoundel()");
				ring.removeRoundel();
			}
		});
	}

}
