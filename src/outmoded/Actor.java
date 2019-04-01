package outmoded;

public class Actor {
	
	/**
	
private boolean canMove(Tile dest,int a,int b) {
		
		//Same color
		if (dest.getPiece() != null)
			if (selectedTile.getPiece().color == dest.getPiece().color)
				return false;
		
		switch (selectedTile.getPiece().soldier) {
		case PAWN:
			return canPawn(x,y,a,b);
		case HORSE:
			return (Math.abs(a - x) == 1 && Math.abs(b - y) == 2) ||
					(Math.abs(a - x) == 2 && Math.abs(b - y) == 1);
		case BISHOP:
			return canBishop(x,y,a,b);
		case ROOK:
			return canRook(x,y,a,b);
		case KING:
			return (Math.abs(a - x) < 2 && Math.abs(b - y) < 2);
		case QUEEN:
			return canBishop(x,y,a,b) || canRook(x,y,a,b);
		}
		return false;
	}
	
	//Parameters are reversed due to a x,y mixup during drafting.
	private boolean canPawn(int y,int x,int b,int a) {
		switch (selectedTile.getPiece().color) {
		case WHITE:
			if (x == a && board[b][a].getPiece() == null) {
				if (y == 1)
					return b == 2
						|| b == 3 && board[3][a].getPiece() == null;
				return b - y == 1;
			}
			if (b - y == 1)
				return Math.abs(a - x) == 1 &&
						board[b][a].getPiece() != null;
		case BLACK:
			if (x == a && board[b][a].getPiece() == null) {
				if (y == 6)
					return b == 5 
						|| b == 4 && board[4][a].getPiece() == null;
				return b - y == -1;
			}
			if (b - y == -1)
				return Math.abs(a - x) == 1 &&
						board[b][a].getPiece() != null;
		}
		return false;
	}
	
	private boolean canRook(int x,int y,int a,int b) {
		if (x == a) {
			if (y < b)
				for (int j = y+1;j < b; j++) {
					if (board[x][j].getPiece() != null)
						return false;
				}
			if (y > b)
				for (int j = y-1;j > b; j--)
					if (board[x][j].getPiece() != null)
						return false;
			return true;
		}
		else if (y == b) {
			if (x < a)
				for (int i = x+1;i < a; i++)
					if (board[i][y].getPiece() != null)
						return false;
			if (x > a)
				for (int i = x-1;i > a; i--)
					if (board[i][y].getPiece() != null)
						return false;
			return true;
		}
		return false;
	}
	
	private boolean canBishop(int x,int y,int a,int b) {
		if (Math.abs(a - x) == Math.abs(b - y)) {
			if (x < a && y < b)
				for (int i = x+1,j = y+1;i<a;i++,j++)
					if (board[i][j].getPiece() != null)
						return false;
			if (x > a && y < b)
				for (int i = x-1,j = y+1;i>a;i--,j++)
					if (board[i][j].getPiece() != null)
						return false;
			if (x < a && y > b)
				for (int i = x+1,j = y-1;i<a;i++,j--)
					if (board[i][j].getPiece() != null)
						return false;
			if (x > a && y > b)
				for (int i = x-1,j = y-1;i>a;i--,j--)
					if (board[i][j].getPiece() != null)
						return false;
			return true;
		}
		return false;
	}
	
	**/
}
