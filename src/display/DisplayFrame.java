package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import chess.*;

@SuppressWarnings("serial")
public class DisplayFrame extends JFrame {
	
	public static final int dim = 640;
	private Chess chess;
	private Tile[][] board;
	
	public DisplayFrame(String name,Chess chess) {
		super(name);
		this.board = chess.getBoard();
		this.chess = chess;
	}

	public void build() {
		style();
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //Add the chessboard.
	    addGrid(getContentPane(),BorderLayout.WEST);
	    
	    //Add the clock
	    Clock clock = new Clock(5);
	    getContentPane().add(clock,BorderLayout.CENTER);
	    
	    //Add the history panel.
	    getContentPane().add(History.getInstance(),BorderLayout.EAST);
	    
	    //Display
	    pack();
	    setVisible(true);
	}

	public void addGrid(Container pane, String layout) {
        final JPanel grid = new JPanel();

        grid.setLayout(new GridLayout(8,8));
        
        //Set up component's preferred size
        grid.setPreferredSize(new Dimension(dim,dim));
        
        //Add buttons
        for (int i = 0; i < 8; i++)
        	for (int j = 0; j < 8; j++) {
        		Tile t = board[i][j];
        		final int x = i;
        		final int y = j;
        		
        		//Execution of the pieces.
        		t.addActionListener(e -> {
        			chess.getActor().act(x,y);
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
	
	private void style() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}
		catch (ClassNotFoundException | InstantiationException | 
				IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
