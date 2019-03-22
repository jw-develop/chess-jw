package chess;

public class Piece {
	
	public final Team color;
	public final Soldier soldier;
	
	public Piece(Team color,Soldier soldier) {
		this.color = color;
		this.soldier = soldier;
	}
	
	public int value() {
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
}