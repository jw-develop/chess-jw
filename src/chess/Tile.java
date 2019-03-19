package chess;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Tile extends JButton {

	private Piece piece;
	
	
	public Piece getPiece() {return piece;}
	
	public void setPiece(Piece p) {
		this.piece = p;
		updateVis();
	}
	
	public void updateVis() {
		if (piece != null)
			setImage();
		else
			setIcon(null);
	}
	
	public void setImage() {setIcon(new ImageIcon(getImageLoc()));}
	
	public String getImageLoc() {
		
		String containingFolder = "./resources/80/";
		
		String loc = containingFolder;
		
		switch (piece.color) {
		case WHITE: loc += "White"; break;
		case BLACK: loc += "Black"; break;
		}
		switch (piece.soldier) {
		case BISHOP: loc += "Bishop"; break;
		case HORSE: loc += "Knight"; break;
		case KING: loc += "King"; break;
		case PAWN: loc += "Pawn"; break;
		case QUEEN: loc += "Queen"; break;
		case ROOK: loc += "Rook"; break;
		}
		loc += ".png";
		
		//System.out.println(loc);
		return loc;
	}
}