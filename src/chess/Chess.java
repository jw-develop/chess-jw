package chess;

public class Chess {

	private Tile[][] board = new Tile[8][8];
	
	private void fill() {
		
		//Pawns
		for (int i = 0; i < 8;i++)
			board[1][i].setPiece(new Piece(Team.WHITE,Soldier.PAWN));
		for (int i = 0; i < 8;i++)
			board[6][i].setPiece(new Piece(Team.BLACK,Soldier.PAWN));
		
		//Other pieces
		Soldier[] kingside = {Soldier.ROOK,Soldier.HORSE,Soldier.BISHOP,Soldier.KING};
		Soldier[] queenside = {Soldier.ROOK,Soldier.HORSE,Soldier.BISHOP,Soldier.QUEEN};
		for (int i = 0; i < 4; i++) {
			board[0][i].setPiece(new Piece(Team.WHITE,kingside[i]));
			board[0][7-i].setPiece(new Piece(Team.WHITE,queenside[i]));
			board[7][i].setPiece(new Piece(Team.BLACK,kingside[i]));
			board[7][7-i].setPiece(new Piece(Team.BLACK,queenside[i]));
		}
	}
}
