package canvas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * 
 * @author ·ùÁö¿¬
 *
 */

public class Canvas {
	
	JButton clearBtn, blackBtn, blueBtn, redBtn, eraseBtn, lineBtn;
	
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
			}
			
		}
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
		
		controls.add(clearBtn);
		controls.add(blackBtn);
		controls.add(blueBtn);
		controls.add(redBtn);
		controls.add(eraseBtn);
		controls.add(lineBtn);
		
		content.add(controls, BorderLayout.NORTH);
		
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
