package canvas;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class DrawArea extends JComponent{
	
	private Image image;
	private Graphics2D g2;
	private int currentX, currentY, oldX, oldY, latestX, latestY;
	boolean pendown = true, line = false, isText = false;
	
	JButton etrBtn;
	JTextField txtfield;
	
	public  ActionListener actionListener2 = new ActionListener() {
			
		public void actionPerformed(ActionEvent e) {
				if (e.getSource() == etrBtn) {
					g2.drawString(txtfield.getText(), oldX, oldY);
					repaint();
				}
		}

	};
	
	public DrawArea() {
		
		addMouseListener(new MouseAdapter() {
			
			

			public void mousePressed(MouseEvent e) {
				
				oldX = e.getX();
				oldY = e.getY();
				
				if (isText) {
					
					
					JFrame txtFrame = new JFrame();
					Container content = txtFrame.getContentPane();
					content.setLayout(new FlowLayout());
					txtfield = new JTextField("hi~");
					
					etrBtn = new JButton("Enter");
					etrBtn.addActionListener(actionListener2);
									
					
					content.add(txtfield);
					content.add(etrBtn);
					
					
					txtFrame.setSize(100, 100);
					txtFrame.setVisible(true);
					
					
					
				}
			}
			
			public void mouseReleased(MouseEvent e) {
				currentX = e.getX();
				currentY = e.getY();
				
				if (line) {
					g2.drawLine(oldX, oldY, currentX, currentY);
					repaint();
				}
				
				
			}
			
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			
			public void mouseDragged(MouseEvent e) {
				currentX = e.getX();
				currentY = e.getY();
				
				if (g2 != null && pendown) {
					g2.drawLine(oldX, oldY, currentX, currentY);
					repaint();
					oldX = currentX;
					oldY = currentY;
				}
				
//				if(!pendown && line) {
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
		g2.setStroke(new BasicStroke());
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
		pendown = false;
		isText = false;
		
	}
	
	public void paint() {
		pendown = true;
		g2.setPaint(Color.black);
		isText = false;
	}
	
	public void bold() {
		
		Stroke stroke = new BasicStroke(10);
		
		
		g2.setStroke(stroke);
		
	
	}
	
	public void putText() {
 
		pendown = false;
		isText = true;
		line = false;

		
		
	}

}
