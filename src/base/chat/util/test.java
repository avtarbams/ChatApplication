package base.chat.util;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;

import com.vdurmont.emoji.EmojiParser;

public class test extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField tf;
	
	test() {
		super("SayHello Messenger");
		JPanel northPanel = new JPanel();
		
		StyleContext sc = new StyleContext();
	    final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
	    JTextPane pane = new JTextPane(doc);
		
		String str = "An :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!";
		String result = EmojiParser.parseToUnicode(str);
		System.out.println(result);
		JTextArea ta = new JTextArea(result);
		northPanel.add(ta);
		add(northPanel, BorderLayout.NORTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 600);
		setVisible(true);
		System.out.println("hi...");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[]) {
		new test();
	}
}
