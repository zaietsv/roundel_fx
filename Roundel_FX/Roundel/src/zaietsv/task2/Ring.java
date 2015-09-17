package zaietsv.task2;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Ring is a round scale painted by hue method. 
 * Consists of 360 rectangular sectors and may contain optional quantity of roundel cursors.
 * */

public class Ring extends Group {
	
	//ring parameters
	private double centerX = 250, centerY = 250;
	private double radius = 150;
	final static int DEG = 360;
	
	//group of coloured segments
	private Group sectors = new Group();
	
	//group of roundels (optional)
	private Group roundels = new Group();
		
	//ring constructor	
	Ring(double centerX, double centerY, double radius) {
		
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
		
		//fill in rounded scale by sectors
		for (int i = 0; i<DEG; i++) {
			Sector sector = new Sector(0, 0, i, i);
			sector.relocate(centerX + radius * Math.cos(Math.PI/180 * (i-90)), 
					centerY + radius * Math.sin(Math.PI/180 * (i-90)));
			sectors.getChildren().add(sector);
		}
		getChildren().add(sectors);
		getChildren().add(roundels);
	}
	
	//getters and setters
	public Group getSectors() {
		return sectors;
	}

	public Group getRoundels() {
		return roundels;
	}
	
	public double getCenterX() {
		return centerX;
	}
	
	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}


	public double getRadius() {
		return radius;
	}


	public void setRadius(double radius) {
		this.radius = radius;
	}

	//adds roundel
	void addRoundel() {
			Roundel rnd = new Roundel(this);
			roundels.getChildren().add(rnd);
	}
	
	//removes roundel
	void removeRoundel() {
		if (!roundels.getChildren().isEmpty()) {
			roundels.getChildren().remove(roundels.getChildren().size()-1);
		}
	}

	//visually rotates coloured ring by changing of color "hue" parameter
	public void rotate(boolean direction) {
		double hue;
		for (Node s : sectors.getChildren()) {
			hue = ((Color)((Rectangle)s).getFill()).getHue();
			if (direction) {
				hue -= 1;		
			} else {
				hue += 1;
			}
			((Rectangle)s).setFill(Color.hsb( hue, 1, 1));
		}
		
		for (Node n : roundels.getChildren()) {
			((Roundel)n).setColor();
		}
	}
}
