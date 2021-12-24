package canvas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * 
 * @author ·ùÁö¿¬
 *
 */

public class Canvas {
	
	JButton clearBtn, blackBtn, blueBtn, redBtn, eraseBtn, lineBtn, paintBtn,
		boldBtn, textBtn;
	
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
				drawArea.line();
			} else if (e.getSource() == paintBtn) {
				drawArea.paint();
		} 
				else if (e.getSource() == boldBtn) {
				drawArea.bold();
			} else if (e.getSource() == textBtn) {
				drawArea.putText();
			}
			
		}

//		private void putText() {
//			JTextField txt = new JTextField();
//			content.add(txt);
//			
//		}
	};
	
	public static void main(String[] args) {
		new Canvas().show();
	}
	
	public void show() {
		
		JFrame frame = new JFrame("New Canvas");
		Container content = frame.getContentPane();
		
		content.setLayout(new BorderLayout());
		
		drawArea = new DrawArea();
		
		content.add(drawArea, BorderLayout.CENTER);
		JPanel controls = new JPanel();
		
		clearBtn = new JButton("Clear");
		clearBtn.addActionListener(actionListener);
		blackBtn = new JButton("Black");
		blackBtn.addActionListener(actionListener);
		blueBtn = new JButton("Blue");
		blueBtn.addActionListener(actionListener);
		redBtn = new JButton("Red");
		redBtn.addActionListener(actionListener);
		eraseBtn = new JButton("Erase");
		eraseBtn.addActionListener(actionListener);
		lineBtn = new JButton("Line");
		lineBtn.addActionListener(actionListener);
		paintBtn = new JButton("Paint");
		paintBtn.addActionListener(actionListener);
		boldBtn = new JButton("Bold");
		boldBtn.addActionListener(actionListener);
		textBtn = new JButton("Text");
		textBtn.addActionListener(actionListener);
		
		controls.add(textBtn);
		controls.add(paintBtn);
		controls.add(lineBtn);
		controls.add(boldBtn);
		controls.add(blackBtn);
		controls.add(blueBtn);
		controls.add(redBtn);
		controls.add(eraseBtn);
		
		
		controls.add(clearBtn);
		
		
		content.add(controls, BorderLayout.NORTH);
		
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
