package zaietsv.task2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * represents a single instance of round scale
 * @author V.Zaiets
 *
 */
public class Sector extends Rectangle {
	
	final static double WIDTH = 4, HEIGHT = 50;
	
	//constructor of a coloured rectangular sector
	Sector(double x, double y, double angle, double hue) {
		
		super(x, y, WIDTH, HEIGHT);
		Color color = Color.hsb(hue, 1, 1);
		setFill(color);
		setRotate(angle);
	}
}
