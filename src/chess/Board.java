package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JFrame {
	
	public static final int dim = 500;
	private Tile[][] board = new Tile[8][8];
	
	private Board(String name) {
		super(name);
        setResizable(false);
	}
	
	public static void build() {
		Board board = new Board("Chess");
	    board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //Set up the content pane.
	    board.addGrid(board.getContentPane(),BorderLayout.WEST);
	    
	    Clock clock = new Clock((long) 5);
	    board.getContentPane().add(clock,BorderLayout.EAST);
	    
	    //Place starting pieces - Update names
	    board.fill();
	    
	    //Display
	    board.pack();
	    board.setVisible(true);
	}
	
	private void fill() {
		
		//Pawns
		for (int i = 0; i < 8;i++)
			board[1][i].setPiece(new Piece(Team.WHITE,Soldier.PAWN));
		for (int i = 0; i < 8;i++)
			board[6][i].setPiece(new Piece(Team.BLACK,Soldier.PAWN));
		
		//Pieces
		Soldier[] kingside = {Soldier.ROOK,Soldier.HORSE,Soldier.BISHOP,Soldier.KING};
		Soldier[] queenside = {Soldier.ROOK,Soldier.HORSE,Soldier.BISHOP,Soldier.QUEEN};
		for (int i = 0; i < 4; i++) {
			board[0][i].setPiece(new Piece(Team.WHITE,kingside[i]));
			board[0][7-i].setPiece(new Piece(Team.WHITE,queenside[i]));
			board[7][i].setPiece(new Piece(Team.BLACK,queenside[i]));
			board[7][7-i].setPiece(new Piece(Team.BLACK,kingside[i]));
		}
	}

	private void addGrid(Container pane, String layout) {
        final JPanel grid = new JPanel();

        grid.setLayout(new GridLayout(8,8));
        
        //Set up component's preferred size
        grid.setPreferredSize(new Dimension(dim,dim));

        //Board inside of Chess.
        Actor chess = new Actor(board);
        
        //Add buttons to experiment with Grid Layout
        for (int i = 0; i < 8; i++)
        	for (int j = 0; j < 8; j++) {
        		Tile t = new Tile();
        		board[i][j] = t;
        		final int x = i;
        		final int y = j;
        		t.addActionListener(e -> {
        			chess.act(x,y);
        		});
        		if ((i+j)%2 == 0) {
        			t.setBackground(Color.BLACK);
        			t.setForeground(Color.WHITE);
        		}
        		else {
        			t.setBackground(Color.WHITE);
        			t.setForeground(Color.BLACK);
        		}
        		grid.add(t);
        	}
        pane.add(grid,layout);
	}
}
