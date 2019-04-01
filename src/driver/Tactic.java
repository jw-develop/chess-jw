package driver;

import static chess.Soldier.BISHOP;
import static chess.Soldier.HORSE;
import static chess.Soldier.KING;
import static chess.Soldier.PAWN;
import static chess.Soldier.QUEEN;
import static chess.Soldier.ROOK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import chess.Soldier;
import chess.Tile;

public abstract class Tactic {
	
	public abstract Couples allM(int x,int y,Tile[][] board);

	public static Tactic getTactic(Soldier s) {
		return tacs.get(s);
	}

	private static HashMap<Soldier,Tactic> tacs;
	
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
	
	public static class Couples implements Iterable<Couple> {
		private ArrayList<Couple> internal = new ArrayList<>();
		
		public void add(int a,int b) {
			if (a > 0 && a < 8 && b > 0 && b < 8)
				internal.add(new Couple(a,b));
		}
		
		public void add(Couple c) {
			int a = c.a, b = c.b;
			if (a > 0 && a < 8 && b > 0 && b < 8)
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
	
	public static class Couple {
		public final int a;
		public final int b;
		public Couple(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
}
