package canvas;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Canvas {
	
	Font defaultFont;
	
	final int SIZE = 20;
	
	static Container content;
	
	JButton clearBtn = new JButton("Clear");
	JButton textBtn = new JButton("Text");
	JButton saveBtn = new JButton("Save");
	JButton dndBtn = new JButton("Drag & Drop");
	JButton eraseBtn = new JButton("Eraser");
	JButton paintBtn = new JButton("Paint Brush");
	JButton boldBtn = new JButton("Bold");
	JButton lineBtn = new JButton("Line");
	JButton polyBtn = new JButton("Polygonal Chain");
	JButton rectBtn = new JButton("Rectangle");
	JButton circBtn = new JButton("Circle");
	JButton redBtn = new JButton("Red");
	JButton blueBtn = new JButton("Blue");
	JButton blackBtn = new JButton("Black");
	JButton clrShapesBtn = new JButton("Clear all Shapes");
	
	
	

	DrawArea drawArea;
	
	ActionListener actionListener = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == clearBtn) {
				
				drawArea.clear();
				
			} else if (e.getSource() == redBtn) {
				
				drawArea.red();
				
			} else if (e.getSource() == blackBtn) {
				
				drawArea.black();
				
			} else if (e.getSource() == blueBtn) {
				
				drawArea.blue();
				
			} else if (e.getSource() == eraseBtn) {
				
				drawArea.erase();
				
			} else if (e.getSource() == lineBtn) {
				
				drawArea.addLine();
				
			} else if (e.getSource() == paintBtn) {
				
				drawArea.paint();
				
			} else if (e.getSource() == boldBtn) {
					
				drawArea.bold();
				
			} else if (e.getSource() == textBtn) {
				
				drawArea.putText();
				
			} else if (e.getSource() == polyBtn) {
				
				drawArea.drawPoly(); 
				
			} else if (e.getSource() == rectBtn) {
				
				drawArea.drawRect();
				
			} else if (e.getSource() == circBtn) {
				
				drawArea.drawCircle();
				
			} else if (e.getSource() == saveBtn) {
				
				drawArea.save();
				
			} else if (e.getSource() == dndBtn) {
				
				drawArea.draganddrop();
				
			} else if (e.getSource() == clrShapesBtn) {
				drawArea.clearAllShapes();
			}
			
		}

	};
	
	public static void main(String[] args) {
		
		new Canvas().show();
		
	}
	
	public void show() {
		
		JFrame frame = new JFrame("New Canvas");
		content = frame.getContentPane();
		
		content.setLayout(new BorderLayout());
		
		drawArea = new DrawArea();

		content.add(drawArea, BorderLayout.CENTER);
		
		JPanel controls = new JPanel();
		controls.setLayout(new FlowLayout());
		
		content.add(controls, BorderLayout.NORTH);
		
		defaultFont = new Font("Arial", Font.PLAIN, 20);
		
		clearBtn.setFont(defaultFont);
		boldBtn.setFont(defaultFont);
		saveBtn.setFont(defaultFont);
		dndBtn.setFont(defaultFont);
		blackBtn.setFont(defaultFont);
		blueBtn.setFont(defaultFont);
		redBtn.setFont(defaultFont);
		lineBtn.setFont(defaultFont);
		polyBtn.setFont(defaultFont);
		rectBtn.setFont(defaultFont);
		circBtn.setFont(defaultFont);
		eraseBtn.setFont(defaultFont);
		paintBtn.setFont(defaultFont);
		
		
		boldBtn.addActionListener(actionListener);
		saveBtn.addActionListener(actionListener);
		dndBtn.addActionListener(actionListener);
		
		
		JPanel colorPanel = new JPanel(new GridLayout(3, 0));
		blackBtn.addActionListener(actionListener);
		blueBtn.addActionListener(actionListener);
		redBtn.addActionListener(actionListener);
		colorPanel.add(redBtn);
		colorPanel.add(blackBtn);
		colorPanel.add(blueBtn);
	
		JPanel shapePanel = new JPanel(new GridLayout(4, 0));
		lineBtn.addActionListener(actionListener);
		polyBtn.addActionListener(actionListener);
		rectBtn.addActionListener(actionListener);
		circBtn.addActionListener(actionListener);
		shapePanel.add(rectBtn);
		shapePanel.add(circBtn);
		shapePanel.add(lineBtn);
		shapePanel.add(polyBtn);
		
		JPanel toolPanel = new JPanel(new GridLayout(3, 0));
		eraseBtn.addActionListener(actionListener);
		paintBtn.addActionListener(actionListener);
		toolPanel.add(paintBtn);
		toolPanel.add(eraseBtn);
		
		JPanel clrPanel = new JPanel(new GridLayout(2, 0));
		clearBtn.addActionListener(actionListener);
		clrShapesBtn.addActionListener(actionListener);
		clrPanel.add(clearBtn);
		clrPanel.add(clrShapesBtn);
		
		
		controls.add(toolPanel);
		controls.add(shapePanel);
	
		
		controls.add(boldBtn);
		controls.add(colorPanel);
		controls.add(dndBtn);
		controls.add(saveBtn);
		controls.add(clrPanel);
		
		
		DrawArea.myLabel = new JLabel();
		content.add(DrawArea.myLabel, BorderLayout.SOUTH);

		frame.setSize(900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}
