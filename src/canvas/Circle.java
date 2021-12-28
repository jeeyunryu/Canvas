package canvas;

import java.awt.*;
import java.awt.geom.*;

public class Circle {
	
	public double x;
	public double y;
	public double width;
	public double height;
	
	public Circle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void drawCircle(Graphics2D g2d) {
		Ellipse2D.Double e1 = new Ellipse2D.Double(x, y, width, height);
		g2d.draw(e1);
	}
}
