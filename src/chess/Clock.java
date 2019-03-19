package chess;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Clock extends JPanel {
	
	//In seconds
	private int current;
	
	private JButton time;
	
	//Argument is given in minutes
	public Clock(int startingTime) {
		
		current = startingTime*60;
		
        this.setPreferredSize(new Dimension(Board.dim/4,Board.dim));
        setBackground(Color.DARK_GRAY);
        
        time = new JButton(String.valueOf(current));
        time.setSize(Board.dim/4-2,Board.dim/2);
        time.setMaximumSize(getSize());

        add(time);
        
        //The clock itself
        int timerDelay = 1000;
		new Timer(timerDelay ,e -> {
		    time.setText(String.valueOf(current));
		    current--;
		}).start();
	}
}