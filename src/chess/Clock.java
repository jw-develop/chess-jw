package chess;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Clock extends JPanel {
	
	//MS from call.
	private Long start;
	
	//In seconds
	private Long total;
	private Long current;
	
	private JButton time;
	
	//Argument is given in minutes
	public Clock(Long startingTime) {
		
		current = startingTime*60;
		total = current;
		
		start = java.lang.System.currentTimeMillis();
		
        this.setPreferredSize(new Dimension(Board.dim/4,Board.dim));
        
        this.setBackground(Color.GRAY);
        
        time = new JButton(current.toString());
	}
	
	public void tick() {
		while (current > 0) {
			current = total - (start - java.lang.System.currentTimeMillis());
			time.setText(current.toString());
		}
	}
}