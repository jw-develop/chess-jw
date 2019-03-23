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
	
	public ArrayList<Move> getMoves(Tile[][] board) {
		
		Couples cS = tactic.allM(x, y, board);
		ArrayList<Move> toReturn = new ArrayList<>();
		
		for (Couple c : cS) {
			if (c.a > 0 && c.b > 0 &&
					c.a < board.length && c.b < board.length) {
				Tile t = board[c.a][c.b];
				Tile m = board[x][y];
				
				if (t.getPiece() != null) {
					Piece oPiece = t.getPiece();
					
					//Different color, add move with that bounty.
					//if (oPiece.color != m.getPiece().color)
						toReturn.add(new Move(x,y,c.a,c.b,oPiece.bounty()));
				}
				else
					toReturn.add(new Move(x,y,c.a,c.b,0));
			}
		}
		return toReturn;
	}
	
	private static interface Tactic {Couples allM(int x,int y,Tile[][] board);}
	
	static {
		tacs = new HashMap<>();
		
		tacs.put(PAWN,new Tactic() {
			Couples toReturn = new Couples();
			public Couples allM(int x, int y, Tile[][] board) {
				return toReturn;
			}
		});
		
		tacs.put(KING,new Tactic() {
			Couples toReturn = new Couples();
			public Couples allM(int x, int y, Tile[][] board) {
				for (int i=x-1;i<x+1;i++)
					for (int j=x-1;j<x+1;j++)
						toReturn.add(i, j);
				return toReturn;
			}
		});
		
		tacs.put(QUEEN,new Tactic() {
			Couples toReturn = new Couples();
			public Couples allM(int x, int y, Tile[][] board) {
				return toReturn;
			}
		});
		
		tacs.put(ROOK,new Tactic() {
			Couples toReturn = new Couples();
			public Couples allM(int x, int y, Tile[][] board) {
				return toReturn;
			}
		});
		
		tacs.put(BISHOP,new Tactic() {
			Couples toReturn = new Couples();
			public Couples allM(int x, int y, Tile[][] board) {
				return toReturn;
			}
		});
		
		tacs.put(HORSE,new Tactic() {
			Couples toReturn = new Couples();
			public Couples allM(int x, int y, Tile[][] board) {
				return toReturn;
			}
		});
	}
	
	private static class Couples implements Iterable<Couple> {
		private ArrayList<Couple> internal = new ArrayList<>();
		
		public void add(int a,int b) {
			internal.add(new Couple(a,b));
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
