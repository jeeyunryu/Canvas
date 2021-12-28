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

	ArrayList<Rectangle> rectList = new ArrayList<>();
	ArrayList<Circle> circList = new ArrayList<>();
	ArrayList<Line> lineList = new ArrayList<>();
	
	private Rectangle r1;
	private Line l1;
	private Circle c1;
	
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
	private boolean isDnd = false;
	
//	JTextField fileName;
//	private String fName;
//	JButton okBtn;
//	
//	ActionListener aListener = new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
//			if (e.getSource() == okBtn) {
//				fName = fileName.getText();
//				try {
//					
//					ImageIO.write((RenderedImage) image, "png", new File("C:\\MyCanvas\\" + fName + ".png"));
//					
//				} catch (IOException evnt) {
//					
//					evnt.printStackTrace();
//					
//				}
//			}
//		}
//	};
	
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
			
					lineList.add(l1);
					isFirstClk = true;
					
				}
				
				if (isRect) {
		
					rectList.add(r1);
					isFirstClk = true;
					
				} 
				
				if(isCircle) {
					
					circList.add(c1);
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
					
					g2.drawLine(oldX, oldY, currentX, currentY);
					repaint();

				}
				
				else if (isLine) {
					
					clear();
					
					g2.setPaint(Color.black);
					g2.setStroke(new BasicStroke());
					
					l1 = new Line(initX, initY, currentX, currentY);
					l1.drawLine(g2);
					
					for (Line l : lineList) {
						
						l.drawLine(g2);
						
					}
					
					for (Rectangle r : rectList) {
						
						r.drawRectangle(g2);
						
					}
					
					for (Circle c : circList) {
						
						c.drawCircle(g2);
						
					}
					
					repaint();
					
				}
				
				else if(isRect) {
					
					clear();
				
					g2.setPaint(Color.black);
					g2.setStroke(new BasicStroke());
					
					r1 = new Rectangle(initX, initY, currentX-initX, currentY-initY);
					r1.drawRectangle(g2);
					
					for (Line l : lineList) {
						l.drawLine(g2);
					}
					for (Rectangle r : rectList) {
						r.drawRectangle(g2);
					}
					for (Circle c : circList) {
						c.drawCircle(g2);
					}
					
					repaint();
					
					
				}
				
				else if (isCircle) {
					
					clear();
					
					g2.setPaint(Color.black);
					g2.setStroke(new BasicStroke());
					
					c1 = new Circle(initX, initY, currentX-initX, currentY-initY);
					c1.drawCircle(g2);
					
					for (Line l : lineList) {
						l.drawLine(g2);
					}
					for (Rectangle r : rectList) {
						r.drawRectangle(g2);
					}
					for (Circle c : circList) {
						c.drawCircle(g2);
					}
					
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
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			clear();
			
			repaint();
			
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
		isCircle = false;
		
	}
	
	public void save() {
		
//		JFrame saveFrm = new JFrame("Save to file");
//		fileName = new JTextField(10);
//		Container contentPane = saveFrm.getContentPane();
//		contentPane.setLayout(new FlowLayout());
//		
//		okBtn = new JButton("OK");
//		
//		contentPane.add(fileName);
//		contentPane.add(okBtn);
//		saveFrm.setSize(200, 200);
//		saveFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		saveFrm.setVisible(true);
		
		try {
			
			ImageIO.write((RenderedImage) image, "png", new File("C:\\MyCanvas\\newImage.png"));
			
		} catch (IOException evnt) {
			
			evnt.printStackTrace();
			
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
		isCircle = false;
		
	}
	
	public void clearAllShapes() {
		
		rectList.removeAll(rectList);
		circList.removeAll(circList);
		lineList.removeAll(lineList);
		clear();
		
	}
	
}
