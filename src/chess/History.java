package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class History extends JPanel {

	private static History one_history;

	private JTextArea textArea = new JTextArea(15, 30);
	private HistoryOutput hOutputStream = new HistoryOutput(
	         textArea, "History of Moves:\n");
	
	private History() {
        this.setPreferredSize(new Dimension(Board.dim/4,Board.dim));
        setBackground(Color.DARK_GRAY);
  
        setLayout(new BorderLayout());
		add(new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		System.setOut(new PrintStream(hOutputStream));
		}
	
	public class HistoryOutput extends OutputStream {

		   private final JTextArea textArea;
		   private final StringBuilder sb = new StringBuilder();

		   public HistoryOutput(final JTextArea textArea, String title) {
		      this.textArea = textArea;
		      sb.append(title);
		   }

		   public void write(int b) {

		      if (b == '\r')
		         return;

		      if (b == '\n') {
		         final String text = sb.toString() + "\n";
		         SwingUtilities.invokeLater(() ->
		               textArea.append(text)
		         );
		         sb.setLength(0);
		         return;
		      }

		      sb.append((char) b);
		   }
		}
	
	public static History getInstance() {
		if (one_history == null)
			one_history = new History();
		return one_history;
	}
}
