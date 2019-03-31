package chess;

import driver.Driver;

public class Chess {

	private Tile[][] board;
	private Actor actor;
	public static Driver bDriver;
	@SuppressWarnings("unused")
	private boolean over;
	
	public Chess() {
		genNormalBoard();
		actor = new Actor(board);
		bDriver = new Driver(board,Team.BLACK);
		over = false;
	}
	
	public void play() {

	}
	
	private void genNormalBoard() {
		board = new Tile[8][8];
		
		for (int i = 0; i < 8; i++)
        	for (int j = 0; j < 8; j++)
        		 board[i][j] = new Tile();
		
		//Pawns
		//for (int i = 0; i < 8;i++)
		//	board[1][i].setPiece(new Piece(Team.WHITE,Soldier.PAWN));
		//for (int i = 0; i < 8;i++)
		//	board[6][i].setPiece(new Piece(Team.BLACK,Soldier.PAWN));
		
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

	public Tile[][] getBoard() {
		return board;
	}
	
	public Actor getActor() {
		return actor;
	}
}