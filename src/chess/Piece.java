package chess;

import static chess.Soldier.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Piece {
	
	static HashMap<Soldier,Tactic> tacs;
	
	public final Team color;
	public final Soldier soldier;
	
	public Piece(Team color,Soldier soldier) {
		this.color = color;
		this.soldier = soldier;
	}
	
	public int bounty() {
		switch (this.soldier) {
			case PAWN: return 1;
			case HORSE: return 2;
			case BISHOP: return 3;
			case ROOK: return 5;
			case QUEEN: return 9;
			case KING: return Integer.MAX_VALUE;
			default: return 0;
		}
	}
	
	public String toString() {
		String toReturn = "";
		switch (this.color) {
			case WHITE: toReturn += "W";
			case BLACK: toReturn += "B";
		}
		switch (this.soldier) {
			case BISHOP: toReturn += "B";
			case HORSE: toReturn += "H";
			case KING: toReturn += "K";
			case PAWN: toReturn += "P";
			case QUEEN: toReturn += "Q";
			case ROOK: toReturn += "R";
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