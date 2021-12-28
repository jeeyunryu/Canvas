package canvas;

import java.awt.*;
import java.awt.geom.*;


public class Rectangle {
	
	private double x;
	private double y;
	private double width;
	private double height;
	
	public Rectangle(double x, double y, double width, double height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	public void drawRectangle(Graphics2D g2d) {
		Rectangle2D.Double r = new Rectangle2D.Double(x, y, width, height);
		g2d.draw(r);
		
	}

}
