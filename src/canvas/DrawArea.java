package canvas;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.RenderedImage;

import javax.swing.*;

import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class DrawArea extends JComponent {
	
//	ButtonGroup colorGroup = new ButtonGroup();
//	static JRadioButton redBtn = new JRadioButton();
//	static JRadioButton blackBtn = new JRadioButton();
//	static JRadioButton blueBtn = new JRadioButton();
//	
	
	ArrayList<Shape> shapeList = new ArrayList<>();
	
	
	
	
	private Image image;
	
	private Graphics2D g2;
	private Graphics2D transg2;
	
	private Ellipse2D ellipse;
	
	private Cursor cursor;
	
	static JLabel myLabel = new JLabel();

	private int currentX = 0;
	private int currentY = 0;
	private int oldX = 0;
	private int oldY = 0;
	private int initX = 0;
	private int initY = 0;
	private int width = 100;
	private int height = 100;
	private int radius = 50;

	private boolean pendown = false;
	private boolean isLine = false;
	private boolean isPoly = false; 
	private boolean isFirstClk = true;
	private boolean isRect = false;
	private boolean isCircle = false; 
	private boolean isHLighter = false;
	private boolean isDnd = false;
	
	public DrawArea() {
		
		addMouseListener(new MouseAdapter() {
			
			//1. press
			public void mousePressed(MouseEvent e) {
				
				currentX = e.getX();
				currentY = e.getY();
				
				if(isFirstClk) {
					
					if (isPoly) {
						
						oldX = e.getX();
						oldY = e.getY();
						isFirstClk = false;
						
					}
					
					else {
						
						initX = currentX;
						initY = currentY;
						isFirstClk = false;
						
					}
					
				}
				
				if (isPoly) {
					
					if (e.getClickCount() == 2) {
						
						e.getX();
						e.getY();
						g2.drawLine(oldX, oldY, currentX, currentY);
						pendown = false;
						isFirstClk = true;
						
					}
					
					else {
						
						g2.drawLine(oldX, oldY, currentX, currentY);
						repaint();
						oldX = currentX;
						oldY = currentY;
						
					}
					
				}

			}
			
			//2. release
			public void mouseReleased(MouseEvent e) {
				
				currentX = e.getX();
				currentY = e.getY();
				
				if (isDnd) {
				
					clear();
					g2.setPaint(Color.black);
					g2.setStroke(new BasicStroke());
					g2.draw(ellipse);
					repaint();
					
					isFirstClk = true;
				}
				
				if (isLine ) {
					
					//g2.drawLine(initX, initY, currentX, currentY);
					//repaint();
					
					isFirstClk = true;
					
				}
				
				if (isRect) {
					
					//g2.drawRect(initX, initY, currentX-initX, currentY-initY);
					//repaint();
//					shapeList.add((Shape) new Rectangle(initX, initY, currentX-initX, currentY-initY));
//					image.drawAll(shapeList);
					
					isFirstClk = true;
					
					
				} 
				
				if(isCircle) {
					
					//g2.drawOval(initX, initY, currentX-initX, currentY-initY);
					//repaint();
					isFirstClk = true;
					
				}
			
			}
			
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			
			//3. move
			public void mouseMoved(MouseEvent e) {
			
				myLabel.setText("(" + e.getX() + ", " + e.getY() + ")");
				
				
				if (isDnd) {
					
					if (ellipse.contains(e.getX(), e.getY())) {
						
						cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
						setCursor(cursor);
						
					}
					else {
						
						cursor = Cursor.getDefaultCursor();
						setCursor(cursor);
						
					}
				}
				
				
			}
			
			//4. move & press
			public void mouseDragged(MouseEvent e) {
				
				oldX = currentX;
				oldY = currentY;
				currentX = e.getX();
				currentY = e.getY();

				if (isDnd) {
					
					clear();
					g2.setPaint(Color.black);
					ellipse.setFrame(currentX - radius, currentY - radius, width, height);
					g2.draw(ellipse);
					repaint();
			
				}
				
				else if (pendown) {
					
					if (isHLighter) {
						
						transg2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
						transg2.drawLine(oldX, oldY, currentX, currentY);
						repaint();
						
					}
					
					else {
						
						g2.drawLine(oldX, oldY, currentX, currentY);
						repaint();
						
					}
					
				}
				
				else if (isLine) {
					clear();
					g2.setPaint(Color.black);
					g2.setStroke(new BasicStroke());
					g2.drawLine(initX, initY, currentX, currentY);

					repaint();
			
				}
				
				else if(isRect) {
					clear();
					g2.setPaint(Color.black);
					g2.drawRect(initX, initY, currentX-initX, currentY-initY);
					repaint();
				}
				
				else if (isCircle) {
					clear();
					g2.setPaint(Color.black);
					g2.drawOval(initX, initY, currentX-initX, currentY-initY);
					repaint();
				}
		
			}
		
		}) ;
	
	}
	
	protected void paintComponent(Graphics g) {
		
		if (image == null) {
			
			image = createImage(getSize().width, getSize().height);
			g2 = (Graphics2D) image.getGraphics();
			transg2 = (Graphics2D) image.getGraphics();
			
			// prevent graphical noise
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);
			
			clear();
			
		}
		
		g.drawImage(image, 0, 0, null);
		
	}

	public void clear() {
		
		g2.setPaint(Color.white);
		g2.fillRect(0, 0, getSize().width, getSize().height);
		
		repaint();
		
	}
	
	public void red() {
		
		g2.setPaint(Color.red);
		transg2.setPaint(Color.red);
	}
	
	public void black() {
		
		g2.setPaint(Color.black);
		transg2.setPaint(Color.black);
	
	}
	
	public void blue() {
		
		g2.setPaint(Color.blue);
		transg2.setPaint(Color.blue);
		
	}
	
	public void erase() {
	
		g2.setPaint(Color.white);
		g2.setPaint(Color.white);
	
		isDnd = false;
		isRect = false;
		pendown = true; 
		isLine = false;
		isPoly = false;
		isFirstClk = false;
		isHLighter = false;
		isCircle = false;
		
	}
	
	public void addLine() {
		
		g2.setStroke(new BasicStroke());
		g2.setPaint(Color.black);
		
		isDnd = false;
		isRect = false;
		pendown = false; 
		isLine = true;
		isPoly = false;
		isFirstClk = true;
		isHLighter = false;
		isCircle = false;
		
	}
	
	// initialize the property of the pen (color & width)
	public void paint() {
		
		g2.setPaint(Color.black);
		g2.setStroke(new BasicStroke());
	
		isDnd = false;
		isRect = false;
		pendown = true; 
		isLine = false;
		isPoly = false;
		isFirstClk = false;
		isHLighter = false;
		isCircle = false;
		
	}
	
	public void bold() {
		
		g2.setStroke(new BasicStroke(10));
		transg2.setStroke(new BasicStroke(10));
		
	}
	
	public void putText() {
		
		g2.setPaint(Color.black);
		
		isDnd = false;
		isRect = false;
		pendown = false; 
		isLine = false;
		isPoly = false;
		isFirstClk = false;
		isHLighter = false;
		isCircle = false;
		
	}
	
	public void drawPoly() {
		
		g2.setStroke(new BasicStroke());
		g2.setPaint(Color.black);
		
		isDnd = false;
		isRect = false;
		pendown = false; 
		isLine = false;
		isPoly = true;
		isFirstClk = true;
		isHLighter = false;
		isCircle = false;
		
	}
	
	// mouse press & move & release
	public void drawRect() {
		
		g2.setStroke(new BasicStroke());
		g2.setPaint(Color.black);

		isDnd = false;
		isRect = true;
		pendown = false; 
		isLine = false;
		isPoly = false;
		isFirstClk = true;
		isHLighter = false;
		isCircle = false;
		
	}
	
	//mouse press & move & release
	public void drawCircle() {
		
		g2.setStroke(new BasicStroke());
		g2.setPaint(Color.black);

		isDnd = false;
		isRect = false;
		pendown = false; 
		isLine = false;
		isPoly = false;
		isFirstClk = true;
		isHLighter = false;
		isCircle = true;
	}
	
	public void highlight() {
		
		transg2.setStroke(new BasicStroke());
		transg2.setPaint(Color.black);
		
		isDnd = false;
		isRect = false;
		pendown = true; 
		isLine = false;
		isPoly = false;
		isFirstClk = false;
		isHLighter = true;
		isCircle = false;
		
	}
	
	public void save() {
		
		try {
			
			ImageIO.write((RenderedImage) image, "png", new File("C:\\MyCanvas\\newImage.png"));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public void draganddrop() {
		
		initX = 200;
		initY = 200;
		radius = 50;
		
		ellipse = new Ellipse2D.Double(initX - radius, initY - radius, width, height);
		g2.setPaint(Color.black);
		g2.setStroke(new BasicStroke());
		g2.draw(ellipse);
		cursor = getCursor();
		repaint();
		
		isDnd = true;
		isRect = false;
		pendown = false; 
		isLine = false;
		isPoly = false;
		isFirstClk = true;
		isHLighter = false;
		isCircle = false;
		
	}
	
}
