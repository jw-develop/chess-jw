package driver;

import chess.*;
import static chess.Soldier.*;

public class Driver {
	
	Tile[][] board;
	Team color;
	
	
	Soldier[] priority = {
			QUEEN,ROOK,BISHOP,HORSE,PAWN,KING
	};
	
	public Driver(Tile[][] board,Team color) {
		this.board = board;
		this.color = color;
		collectPieces();
	}
	
	private void collectPieces() {
		for (int i = 0; i < 8; i++)
        	for (int j = 0; j < 8; j++) {
        		
        	}
	}
}
