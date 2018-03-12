package base.chat.util;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.vdurmont.emoji.EmojiParser;

public class ChatUtil {
	public static String host = "localhost";
	public static int port = 1500;
	public static String userName = "Vivek";
	public static Properties prop = new Properties();
	
	public static HashMap<Integer,String> hm = new HashMap<Integer,String>();
	public static HashMap<Integer,String> clist = new HashMap<Integer,String>();
	public static HashMap<String,Color> ctoc = new HashMap<String,Color>();
	public static void setMap() {
		hm.put(3,"./emojis/smiley1.png");
		hm.put(4,"./emojis/smiley2.png");
		hm.put(5,"./emojis/smiley3.png");
		hm.put(6,"./emojis/smiley4.png");
		hm.put(7,"./emojis/smiley5.png");
		hm.put(8,"./emojis/smiley6.png");
		hm.put(9,"./emojis/smiley7.png");
		hm.put(10,"./emojis/smiley8.png");
		hm.put(11,"./emojis/smiley9.png");
		hm.put(12,"./emojis/smiley10.png");
		hm.put(13,"./emojis/smiley11.png");
		hm.put(14,"./emojis/smiley12.png");
		
		hm.put(15,"./emojis/smiley1.gif");
		hm.put(16,"./emojis/smiley2.gif");
		hm.put(17,"./emojis/smiley3.gif");
		hm.put(18,"./emojis/smiley4.gif");
		hm.put(19,"./emojis/smiley5.gif");
		hm.put(20,"./emojis/smiley6.gif");
		hm.put(21,"./emojis/smiley7.gif");
		hm.put(22,"./emojis/smiley8.gif");
		hm.put(23,"./emojis/smiley9.gif");
		hm.put(24,"./emojis/smiley10.gif");
		hm.put(25,"./emojis/smiley11.gif");
		hm.put(26,"./emojis/smiley12.gif");
	}
	
	public static void setColorList() {
		clist.put(1,"red");
		clist.put(2,"orange");
		clist.put(3,"magenta");
		clist.put(4,"darkGray");
		clist.put(5,"green");
		clist.put(6,"blue");
	}
	
	public static void setColorToColorList() {
		ctoc.put("red",Color.RED);
		ctoc.put("orange",Color.ORANGE);
		ctoc.put("magenta",Color.MAGENTA);
		ctoc.put("darkGray",Color.DARK_GRAY);
		ctoc.put("green",Color.GREEN);
		ctoc.put("blue",Color.BLUE);
	}
	
	public static void main(String args[]) {
		/*System.out.println("SSSSSSSSSS");
		loadProperties();
		setMap();
		System.out.println("hiiii "+prop.getProperty("default_user"));
		String str = "An :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!";
		String result = EmojiParser.parseToUnicode(str);
		System.out.println(result);*/
		
		JFrame.setDefaultLookAndFeelDecorated(true);
	    JFrame frame = new JFrame();
	    frame.setTitle("JLabel Test");
	    frame.setLayout(new FlowLayout());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JLabel label = new JLabel("First Name");
	    label.setFont(new Font("Courier New", Font.ITALIC, 12));
	    label.setForeground(Color.GREEN);

	    frame.add(label);
	    frame.pack();
	    frame.setVisible(true);
	}
	
	public static void loadProperties() {
		InputStream input = null;
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
