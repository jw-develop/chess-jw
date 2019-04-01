package driver;

import chess.*;
import static chess.Soldier.*;

import java.util.ArrayList;

public class Driver {
	
	Tile[][] board;
	Team color;
	ArrayList<Piece> pieces;
	
	Soldier[] priority = {
			QUEEN,ROOK,BISHOP,HORSE,PAWN,KING
	};
	
	public Driver(Tile[][] board,Team color) {
		this.board = board;
		this.color = color;
		pieces = collectPieces();
	}
	
	public Move makeMove() {
		Move highestBounty = null;
		Piece toMove = null;
		
		for (Piece piece : pieces)
			for (Move m : piece.getMoves())
				if (highestBounty == null || m.bounty > highestBounty.bounty) {
					highestBounty = m;
					toMove = piece;
				}
		
		if (toMove != null) {
			toMove.movePiece(highestBounty.toX, highestBounty.toY);
		}

		return highestBounty;
	}
	
	private ArrayList<Piece> collectPieces() {
		ArrayList<Piece> toReturn = new ArrayList<>(16);
		for (int i = 0; i < 8; i++)
        	for (int j = 0; j < 8; j++) {
        		Piece p = board[i][j].getPiece();
        		if (p != null && p.color == color)
        			toReturn.add(p);
        	}
		return toReturn;
	}
}
