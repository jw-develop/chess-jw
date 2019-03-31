package driver;

import chess.*;
import static chess.Soldier.*;

import java.util.ArrayList;

public class Driver {
	
	Tile[][] board;
	Team color;
	ArrayList<Mover> pieces;
	
	Soldier[] priority = {
			QUEEN,ROOK,BISHOP,HORSE,PAWN,KING
	};
	
	public Driver(Tile[][] board,Team color) {
		this.board = board;
		
		this.color = Team.BLACK;
		
		pieces = collectMovers();
	}
	
	public Move makeMove() {
		Move highestBounty = null;
		Mover toMove = null;
		
		for (Mover mover : pieces)
			for (Move m : mover.getMoves(board))
				if (highestBounty == null || m.bounty > highestBounty.bounty) {
					highestBounty = m;
					toMove = mover;
				}
		
		toMove.setX(highestBounty.toX);
		toMove.setY(highestBounty.toY);

		return highestBounty;
	}
	
	private ArrayList<Mover> collectMovers() {
		ArrayList<Mover> toReturn = new ArrayList<>(16);
		for (int i = 0; i < 8; i++)
        	for (int j = 0; j < 8; j++) {
        		Piece p = board[i][j].getPiece();
        		if (p != null && p.color == color)
        			toReturn.add(new Mover(i,j,
        					board[i][j].getPiece().soldier));
        	}
		return toReturn;
	}
}
