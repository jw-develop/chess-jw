package driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import chess.Piece;
import chess.Soldier;
import static chess.Soldier.*;
import chess.Tile;

public class Mover {
	
	static HashMap<Soldier,Tactic> tacs;
	
	private int x;
	private int y;
	private Tactic tactic;
	
	public Mover(int a,int b,Soldier s) {
		x = a;
		y = b;
		tactic = tacs.get(s);
	}

	public void setX(int x) {this.x = x;}

	public void setY(int y) {this.y = y;}

	public ArrayList<Move> getMoves(Tile[][] board) {
		
		Couples cS = tactic.allM(x, y, board);
		ArrayList<Move> toReturn = new ArrayList<>();
		
		System.out.println("Searching for moves:");
		
		for (Couple c : cS) {
			if (c.a > 0 && c.b > 0 &&
					c.a < board.length && c.b < board.length &&
					!(c.a == x && c.b == y)) {
				Tile t = board[c.a][c.b];
				Tile m = board[x][y];
				int bounty = 0;
				
				if (t.getPiece() != null && m.getPiece() != null) {
					Piece oPiece = t.getPiece();

					//Different color, add move with that bounty.
					if (oPiece.color != m.getPiece().color)
						bounty = oPiece.bounty();
				}

				toReturn.add(new Move(x,y,c.a,c.b,bounty));
				System.out.printf("\nMove added: %s,%s,%s,%s,%s",x,y,c.a,c.b,bounty);
			}
		}
		return toReturn;
	}
	
	private static interface Tactic {Couples allM(int x,int y,Tile[][] board);}
	
	static {
		tacs = new HashMap<>();
		
		tacs.put(PAWN,new Tactic() {
			public Couples allM(int x, int y, Tile[][] board) {
				Couples toReturn = new Couples();
				return toReturn;
			}
		});
		
		tacs.put(KING,new Tactic() {
			public Couples allM(int x, int y, Tile[][] board) {
				Couples toReturn = new Couples();
				for (int i=x-1;i<=x+1;i++)
					for (int j=y-1;j<=y+1;j++)
						toReturn.add(i,j);
				return toReturn;
			}
		});
		
		Tactic rook = new Tactic() {
			public Couples allM(int x, int y, Tile[][] board) {
				Couples toReturn = new Couples();
				for (int i=x+1;i<8 && board[i][y] != null;i++)
					toReturn.add(i,y);
				for (int j=y+1;j<8 && board[x][j] != null;j++)
					toReturn.add(x,j);
				for (int i=x-1;i>=0 && board[i][y] != null;i--)
					toReturn.add(i,y);
				for (int j=y-1;j>=0 && board[x][j] != null;j--)
					toReturn.add(x,j);
				return toReturn;
			}
		};
		
		Tactic bishop = new Tactic() {
			public Couples allM(int x, int y, Tile[][] board) {
				Couples toReturn = new Couples();
				for (int i=x+1,j=y+1;
						i<8 && j<8 && board[i][j] != null;
						i++,j++)
					toReturn.add(i,j);
				for (int i=x+1,j=y-1;
						i<8 && j>0 && board[i][j] != null;
						i++,j--)
					toReturn.add(i,j);
				for (int i=x-1,j=y+1;
						i>0 && j<8 && board[i][j] != null;
						i--,j++)					
					toReturn.add(i,j);
				for (int i=x-1,j=y-1;
						i>0 && j>0 && board[i][j] != null;
						i--,j--)
					toReturn.add(i,j);
				return toReturn;
			}
		};
		
		tacs.put(ROOK,rook);
		tacs.put(BISHOP,bishop);
		
		tacs.put(QUEEN,new Tactic() {
			public Couples allM(int x, int y, Tile[][] board) {
				Couples toReturn = bishop.allM(x, y, board);
				for (Couple c : rook.allM(x, y, board))
					toReturn.add(c);
				return toReturn;
			}
		});
		
		tacs.put(HORSE,new Tactic() {
			public Couples allM(int x, int y, Tile[][] board) {
				Couples toReturn = new Couples();
				int[][] arr = {
						{-2,-1},
						{2,-1},
						{-2,1},
						{2,1}
				};
				
				for (int[] a : arr)
					toReturn.add(a[0],a[1]);
				for (int[] a : arr)
					toReturn.add(a[1],a[0]);
				
				return toReturn;
			}
		});
	}
	
	private static class Couples implements Iterable<Couple> {
		private ArrayList<Couple> internal = new ArrayList<>();
		
		public void add(int a,int b) {
			internal.add(new Couple(a,b));
		}
		
		public void add(Couple c) {
			internal.add(c);
		}

		public Iterator<Couple> iterator() {
			return new Iterator<Couple>() {
				int i = 0;
				public boolean hasNext() {
					return i < internal.size();
				}
				public Couple next() {
					return internal.get(i++);
				}
				
			};
		}
	}
	
	private static class Couple {
		public final int a;
		public final int b;
		public Couple(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
}
