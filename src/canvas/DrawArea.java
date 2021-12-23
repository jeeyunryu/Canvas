package canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;

public class DrawArea extends JComponent{
	
	private Image image;
	private Graphics2D g2;
	private int currentX, currentY, oldX, oldY, latestX, latestY;
	boolean up = true, line = false;
	
	public DrawArea() {
		
		addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				
				oldX = e.getX();
				oldY = e.getY();
			}
			
			public void mouseReleased(MouseEvent e) {
				currentX = e.getX();
				currentY = e.getY();
				
				if (!up) {
					g2.drawLine(oldX, oldY, currentX, currentY);
					repaint();
				}
				
				
			}
			
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			
			public void mouseDragged(MouseEvent e) {
				currentX = e.getX();
				currentY = e.getY();
				
				if (g2 != null && up) {
					g2.drawLine(oldX, oldY, currentX, currentY);
					repaint();
					oldX = currentX;
					oldY = currentY;
				}
				
//				if(!up && line) {
//					//clear();
//					g2.drawLine(oldX, oldY, currentX, currentY);
//					repaint();
//					
//				}
			}
			
			
		}) ;
		
		
			
	}
	
	protected void paintComponent(Graphics g) {
		if (image == null) {
			image = createImage(getSize().width, getSize().height);
			g2 = (Graphics2D) image.getGraphics();
			
			// 그래픽 노이즈 방지
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);
			
			clear();
		}
		
		g.drawImage(image, 0, 0, null);
	}
	
	
	public void clear() {
		g2.setPaint(Color.white);
		g2.fillRect(0, 0, getSize().width, getSize().height);
		g2.setPaint(Color.black);
		repaint();
	}
	
	public void red() {
		g2.setPaint(Color.red);
		
	}
	
	public void black() {
		g2.setPaint(Color.black);
	
	}
	
	public void blue() {
		g2.setPaint(Color.blue);
		
	}
	
	public void erase() {
		g2.setPaint(Color.white);
	}
	
	public void line() {
		line = true;
		up = false;
		//g2.drawLine(oldX, oldY, latestX, latestY);
	}
	
	public void paint() {
		up = true;
		g2.setPaint(Color.black);
	}
	
//	public void bold() {
//		
//		g2.getLineWidth();
//		g2.setStroke()
//		g2.setStroke(new BasicStroke(10));
//	
//	}

}
