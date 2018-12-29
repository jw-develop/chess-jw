package chess;

public class Promote {

	private static Promote one_promote;
	private Promote() {}
	
	private boolean promoting = false;
	private Tile[][] board;
	private Tile[] boardArray = new Tile[4];
	private Piece[] temp = new Piece[4];
	private Soldier[] options = {
		Soldier.QUEEN,Soldier.ROOK,Soldier.BISHOP,Soldier.HORSE
	};
	private int i;
	private int j;
	private Team team;
	
	public void promote(Tile[][] board, int i, int j) {
		
		//2d to 1d
		boardArray[0] = board[3][3];
		boardArray[1] = board[3][4];
		boardArray[2] = board[4][3];
		boardArray[3] = board[4][4];
		
		this.board = board;
		this.i = i;
		this.j = j;
		this.team = board[i][j].getPiece().color;
		promoting = true;
		
		//Store center pieces.
		for (int a = 0; a < 4; a++)
			temp[a] = boardArray[a].getPiece();
		
		//Place temp pieces.
		populate(team,options);
		
	}
	
	public void resolve(int a,int b) {
		
		//Converting 2x2 to 0 : 3
		int k = 0;
		if (a == 4)
			k += 2;
		if (b == 4)
			k += 1;
		
		board[i][j].setPiece(new Piece(team,options[k]));
		populate(temp);
		promoting = false;
	}
	
	private void populate(Piece[] arr) {
		for (int a = 0; a < 4; a++)
			boardArray[a].setPiece(arr[a]);
	}
	private void populate(Team team,Soldier[] arr) {
		for (int a = 0; a < 4; a++)
			boardArray[a].setPiece(new Piece(team,arr[a]));
	}
	
	public static Promote getInstance() {
		if (one_promote == null)
			one_promote = new Promote();
		return one_promote;
	}
	
	public boolean getPromoting() {return promoting;}
}
