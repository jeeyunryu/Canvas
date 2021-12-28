package canvas;

import java.awt.*;
import java.awt.geom.*;

public class Line {
	
	private double startX;
	private double startY;
	private double endX;
	private double endY;
	
	public Line(double startX, double startY, double endX, double endY) {
		
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		
	}
	
	public void drawLine(Graphics2D g2d) {
		Line2D.Double l1 = new Line2D.Double(startX, startY, endX, endY);
		g2d.draw(l1);
	}

}
