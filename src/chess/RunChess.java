package chess;

import display.DisplayFrame;

public class RunChess {
	
	public static void main(String[] args) {
		Chess chess = new Chess();
		
		DisplayFrame frame = new DisplayFrame("Chess",chess);
		frame.build();
		
		chess.play();
	}
}