package zaietsv.task2;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * AddButton adds a new roundel to a specified ring
 * */

public class AddButton extends Button {
	
	AddButton(final Ring ring) {
	super("+ / add Roundel");
	relocate(ring.getCenterX() - 2.5 * Sector.HEIGHT, ring.getCenterY() + ring.getRadius() + 2 * Sector.HEIGHT);
	addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<Event>() {
		@Override
		public void handle(Event arg0) {
			System.out.println("MouseEvent: addRoundel()");
			
			ring.addRoundel();
		}
	});
	}

}
