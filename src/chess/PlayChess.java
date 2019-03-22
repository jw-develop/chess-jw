package chess;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import display.DisplayFrame;

public class PlayChess {
	
	public static void main(String[] args) {
		style();
		
		javax.swing.SwingUtilities.invokeLater(() -> DisplayFrame.build());
	}
	
	private static void style() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}
		catch (ClassNotFoundException | InstantiationException | 
				IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}