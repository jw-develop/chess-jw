package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import chess.*;

@SuppressWarnings("serial")
public class DisplayFrame extends JFrame {
	
	public static final int dim = 640;
	
	private DisplayFrame(String name) {
		super(name);
        setResizable(false);
	}

	public static void build() {
		DisplayFrame board = new DisplayFrame("Chess");
	    board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //Add the chessboard.
	    board.addGrid(board.getContentPane(),BorderLayout.WEST);
	    
	    //Add the clock
	    Clock clock = new Clock(5);
	    board.getContentPane().add(clock,BorderLayout.CENTER);
	    
	    //Add the history panel.
	    board.getContentPane().add(History.getInstance(),BorderLayout.EAST);
	    
	    //Place starting pieces - Update names
	    board.fill();
	    
	    //Display
	    board.pack();
	    board.setVisible(true);
	}

	public void addGrid(Container pane, String layout) {
        final JPanel grid = new JPanel();

        grid.setLayout(new GridLayout(8,8));
        
        //Set up component's preferred size
        grid.setPreferredSize(new Dimension(dim,dim));

        //Board inside of Chess.
        Actor chess = new Actor(board);
        
        //Add buttons
        for (int i = 0; i < 8; i++)
        	for (int j = 0; j < 8; j++) {
        		Tile t = new Tile();
        		board[i][j] = t;
        		final int x = i;
        		final int y = j;
        		
        		//Execution of the pieces.
        		t.addActionListener(e -> {
        			chess.act(x,y);
        		});
        		
        		//Color the board.
        		Color dark = Color.GRAY;
        		Color light = Color.WHITE;
        		
        		if ((i+j)%2 == 0) {
        			t.setBackground(light);
        			t.setForeground(dark);
        		}
        		else {
        			t.setBackground(dark);
        			t.setForeground(light);
        		}
        		grid.add(t);
        	}
        pane.add(grid,layout);
	}
}
