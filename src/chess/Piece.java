package chess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import driver.Move;
import driver.Tactic;
import driver.Tactic.Couple;
import driver.Tactic.Couples;

public class Piece {
	
	public final Team color;
	public final Soldier soldier;
	
	private int x;
	private int y;
	private Tactic tactic;
	private List<Move> moves;
	private Set<Couple> set;
	
	public Piece(Team color,Soldier soldier,int a,int b) {
		this.color = color;
		this.soldier = soldier;
		x = a;
		y = b;
		tactic = Tactic.getTactic(soldier);
		moves = new ArrayList<Move>();
		set = new HashSet<Couple>();
	}
	
	public List<Move> getMoves() {
		return moves;
	}
	
	public boolean hasMove(int x,int y,Tile[][] board) {
		updateMoves(board);
		for (Couple c : set)
			if (c.a == x && c.b == y)
				return true;
		return false;
	}
	
	public void movePiece(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void updateMoves(Tile[][] board) {
		
		Couples cS = tactic.allM(x, y, board);
		moves = new ArrayList<Move>();
		set = new HashSet<Couple>();
		
		System.out.println("Searching for moves:");
		
		for (Couple c : cS) {
			
			if (!(c.a == x && c.b == y)) {
				Tile t = board[c.a][c.b];
				Tile m = board[x][y];
				int bounty = 0;
				
				if (t.getPiece() != null && m.getPiece() != null) {
					Piece oPiece = t.getPiece();

					//Different color, add move with that bounty.
					if (oPiece.color != m.getPiece().color)
						bounty = oPiece.bounty();
					
					// Don't add if same color.
					else
						break;
				}

				moves.add(new Move(x,y,c.a,c.b,bounty));
				set.add(new Couple(c.a,c.b));
				//System.out.printf("Move added: %s,%s,%s,%s,%s\n",x,y,c.a,c.b,bounty);
			}
		}
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
			case WHITE: toReturn += "W"; break;
			case BLACK: toReturn += "B"; break;
		}
		switch (this.soldier) {
			case BISHOP: toReturn += "B"; break;
			case HORSE: toReturn += "H"; break;
			case KING: toReturn += "K"; break;
			case PAWN: toReturn += "P"; break;
			case QUEEN: toReturn += "Q"; break;
			case ROOK: toReturn += "R"; break;
		}
		return toReturn;
	}
}