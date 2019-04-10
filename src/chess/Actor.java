package chess;

import java.awt.Color;

import driver.Move;

public class Actor {
	
	private Tile[][] board;
	private Team turn;
	
	private Tile selectedTile;
	private Color selTileColor;
	
	public Actor(Tile[][] board) {
		this.board = board;
		selectedTile = null;
		turn = Team.WHITE;
	}
	
	private void select(int i, int j) {
		if (selectedTile.getPiece() != null) {
			selectedTile = board[i][j];
			selTileColor = selectedTile.getBackground();
			selectedTile.setBackground(Color.blue);
			selectedTile.getPiece().updateMoves(board);
		}
	}

	public void act(int i,int j) {
		
		Tile clicked = board[i][j];
		boolean moved = false;
		
		//If promotion
		if (Promote.getInstance().getPromoting())
			if ((i == 3 || i == 4) &&
				(j == 3 || j == 4))
				Promote.getInstance().resolve(i,j);
			else
				return;
		
		//Select a piece
		if (selectedTile == null && 
				clicked.getPiece() != null &&
				clicked.getPiece().color == turn)
			select(i,j);
			
			//Print coord.
			//System.out.printf("%d %d\n",x,y);
		
		//Attempt to move a piece
		else if (selectedTile != null) {

			//If compatible, then set the piece at the new place.
			if (selectedTile.getPiece().hasMove(i,j,board)) {
				
				clicked.setPiece(selectedTile.getPiece());
				selectedTile.setPiece(null);
				
				//Change current color to move.
				moved = true;
				
				//Promotion
				Piece p = clicked.getPiece();
					if (p.soldier == Soldier.PAWN)
						if (p.color == Team.WHITE && i == 7 ||
							p.color == Team.BLACK && i == 0)
							Promote.getInstance().promote(board,i,j);
			}
			
			//No matter what, reset the selected tile square.
			selectedTile.setBackground(selTileColor);
			selectedTile = null;
			if (moved)
				nextTeam();
		}
	}
	
	private void nextTeam() {
		if (turn == Team.WHITE) {
			turn = Team.BLACK;
			Move m = Chess.bDriver.makeMove();
			if (m != null) {
				System.out.println("MOVED!");
				select(m.atX,m.atY);
				System.out.println(m.atX+" "+m.atY);
				act(m.toX,m.toY);
				System.out.println(m.toX+" "+m.toY);
			}
			else 
				System.out.println("No move found");
		}
		else
			turn = Team.WHITE;
	}
	
	public Team getTurn() {return turn;}
}