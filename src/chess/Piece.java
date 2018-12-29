package chess;

import java.util.HashMap;

public class Piece {
	
	public final Team color;
	public final Soldier soldier;
	public static HashMap<Piece,String> m = new HashMap<>();
	
	public String toString() {
		switch (this.color) {
		case WHITE:
			switch (this.soldier) {
			case BISHOP: return "WB";
			case HORSE: return "WH";
			case KING: return "WK";
			case PAWN: return "WP";
			case QUEEN: return "WQ";
			case ROOK: return "WR";
			}
		case BLACK:
			switch (this.soldier) {
			case BISHOP: return "BB";
			case HORSE: return "BH";
			case KING: return "BK";
			case PAWN: return "BP";
			case QUEEN: return "BQ";
			case ROOK: return "BR";
			}
		default:
			return "-";
		}
	}
	
	public Piece(Team color,Soldier soldier) {
		this.color = color;
		this.soldier = soldier;
	}
}