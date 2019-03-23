package driver;

public class Move {
	
	public final int atX;
	public final int atY;
	public final int toX;
	public final int toY;
	public final int bounty;
	
	public Move(int atX, int atY, int toX, int toY, int bounty) {
		super();
		this.atX = atX;
		this.atY = atY;
		this.toX = toX;
		this.toY = toY;
		this.bounty = bounty;
	}
}
