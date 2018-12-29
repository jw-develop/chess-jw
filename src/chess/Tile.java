package chess;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Tile extends JButton {

	private Piece piece;
	
	public Piece getPiece() {return piece;}
	
	public void setPiece(Piece p) {
		this.piece = p;
		updateText();
	}
	
	public void updateText() {
		if (piece != null)
			this.setText(getPiece().toString());
		else
			this.setText("");
	}
}