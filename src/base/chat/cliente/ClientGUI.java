package base.chat.cliente;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import base.chat.util.ChatMessage;
import base.chat.util.ChatUtil;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;

/*
 * The Client with its GUI
 */
public class ClientGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	// will first hold "Username:", later on "Enter message"
	private JLabel label;
	// to hold the Username and later on the messages
	private JTextField tf;
	// to hold the server address an the port number
	private JTextField tfServer, tfPort;
	// to Logout and get the list of the users
	private JButton login, logout, whoIsIn,emoji,emoj[];
	// for the chat room
	//private JTextArea ta;
	// if it is for connection
	private boolean connected;
	// the Client object
	private Client client;
	// the default port number
	private int defaultPort;
	private String defaultHost;
	private String username;
	private StyleContext context = new StyleContext();
	private StyledDocument document = new DefaultStyledDocument(context);
	private Style labelStyle = context.getStyle(StyleContext.DEFAULT_STYLE);

	private Icon[] icon;
	private JLabel[] label1;
	private JLabel label11;
	private Icon icon1;
    private JTextPane textPane;
	// Constructor connection receiving a socket number
	ClientGUI(String host, int port) {

		super("SayHello Messenger");
		defaultPort = port;
		defaultHost = host;
		String topIconFile = "./images/messengerlogo.png";
		ImageIcon img = new ImageIcon(topIconFile);
		setIconImage(img.getImage());
		
		String iconFile = "./images/messengerlogo_s.png";
		JLabel logoLabel = new JLabel();
		logoLabel.setIcon(new ImageIcon(new ImageIcon(iconFile).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		
		// The NorthPanel with:
		JPanel northPanel = new JPanel(new GridLayout(1,1));
		// the server name anmd the port number
		JPanel serverAndPort = new JPanel(new GridLayout(1,6, 1, 3));
		// the two JTextField with default value for server address and port number
		tfServer = new JTextField(host);
		tfPort = new JTextField("" + port);
		tfPort.setHorizontalAlignment(SwingConstants.RIGHT);
		serverAndPort.add(logoLabel);
		serverAndPort.add(new JLabel("Server Address:  "));
		serverAndPort.add(tfServer);
		serverAndPort.add(new JLabel("Port Number:  "));
		serverAndPort.add(tfPort);
		serverAndPort.add(new JLabel(""));
		// adds the Server an port field to the GUI
		northPanel.add(serverAndPort);
		ChatUtil.loadProperties();
		// the Label and the TextField
		label = new JLabel("Enter your username below", SwingConstants.CENTER);
		tf = new JTextField(ChatUtil.prop.getProperty("default_user"));
		tf.setBackground(Color.WHITE);
		/*northPanel.add(label);
		northPanel.add(tf);*/
		
		add(northPanel, BorderLayout.NORTH);

		// The CenterPanel which is the chat room
		String image = "./images/jtext_light.png";
		ImageIcon myimage = new ImageIcon(image);
	    Image image1 = myimage.getImage();
	    JLabel background = null;
		try {
			document.insertString(document.getLength(), "Welcome to SayHello Messenger\n",new SimpleAttributeSet());
			background=new JLabel(new ImageIcon(ImageIO.read(new File(image))));
		} catch (BadLocationException badLocationException) {
			System.err.println("Oops");
		} catch (IOException e) {
			e.printStackTrace();
		}
		textPane = new JTextPane() {
			{setOpaque(false);}
			protected void paintComponent(Graphics g) {
		        int w = this.getWidth();
		        int h = this.getHeight();
		        g.drawImage(image1,0,0,w,h,myimage.getImageObserver());
		        super.paintComponent(g);
		      }
		};
		textPane.setStyledDocument(document);
		textPane.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(textPane);
		add(scrollPane, BorderLayout.CENTER);

		// the 4 buttons
		login = new JButton("Login");
		login.addActionListener(this);
		
		logout = new JButton("Logout");
		logout.addActionListener(this);
		logout.setEnabled(false);		// you have to login before being able to logout
		
		whoIsIn = new JButton("Who is in");
		whoIsIn.addActionListener(this);
		whoIsIn.setEnabled(false);		// you have to login before being able to Who is in
		
		/*ChatUtil.setMap();*/
		icon = new Icon[ChatUtil.hm.size()];
		label1 = new JLabel[ChatUtil.hm.size()];
		emoj = new JButton[ChatUtil.hm.size()];
		JPanel emojiPanel = new JPanel();
		int i=0;
		for(Entry<Integer, String> entry : ChatUtil.hm.entrySet()) {
			int key = entry.getKey();
			String val = entry.getValue();
			icon[i] = new ImageIcon(val);
			label1[i] = new JLabel(icon[i]);
			StyleConstants.setComponent(labelStyle, label1[i]);
			emoj[i] = new JButton("");
			emoj[i].addActionListener(this);
			emoj[i].setEnabled(false);
			emoj[i].setIcon(new ImageIcon(val));
			emoj[i].setBounds(10, 438, 39, 31);
			emoj[i].setBorder(BorderFactory.createEmptyBorder());
			emoj[i].setContentAreaFilled(false);
			emoj[i].setFocusable(false);
			emojiPanel.add(emoj[i]);
			i++;
		}
		JScrollPane jScrollPane = new JScrollPane(emojiPanel);
		jScrollPane.setPreferredSize(new Dimension(330, 80));
		JPanel southPanel = new JPanel(new GridLayout(2, 1));
		southPanel.setPreferredSize(new Dimension(600, 105));
		/*southPanel.add(label);*/
		JPanel sp = new JPanel(new GridLayout(1,1));
		sp.setPreferredSize(new Dimension(600, 35));
		tf.setPreferredSize(new Dimension(600, 35));
		sp.add(tf);
		JPanel sp1 = new JPanel(new GridLayout(1,4));
		
		sp1.add(login);
		sp1.add(logout);
		sp1.add(whoIsIn);
		sp1.add(jScrollPane);
		
		southPanel.add(sp,BorderLayout.CENTER);
		southPanel.add(sp1,BorderLayout.SOUTH);
		
		add(southPanel, BorderLayout.SOUTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 600);
		setVisible(true);
		tf.requestFocus();

	}

	public ClientGUI() {
		
	}
	
	void append(String str,int no,Color c) {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(new Date());
			String messageDU = time + " " + username;
			context = new StyleContext();
		    labelStyle = context.getStyle(StyleContext.DEFAULT_STYLE);
		    /*ChatUtil.setMap();*/
		    icon1 = new ImageIcon(ChatUtil.hm.get(no));
		    label11 = new JLabel(icon1);
		    StyleConstants.setComponent(labelStyle, label11);
		    StyleContext sc = StyleContext.getDefaultStyleContext();
			AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
		    document.insertString(document.getLength(), str, aset);
			document.insertString(document.getLength(), "s\n", labelStyle);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
	}
	// called by the Client to append text in the TextArea 
	void append(String str) {
		try {
			
			String color=str;
			String newStr = str;
			Color c = Color.BLACK;
			if(str.startsWith("$")) {
				color = color.substring(color.indexOf("$") + 1);
				color = color.substring(0, color.indexOf("$"));
				c = ChatUtil.ctoc.get(color);
				newStr = newStr.substring(newStr.indexOf("$")+1);
				newStr = newStr.substring(newStr.indexOf("$")+1,newStr.length());
				str = newStr;
			}
			
			if(str.contains("#")) {
				String dtStr = str.substring(0,str.indexOf("#"));
				String nStr = str.substring(str.indexOf("#")+1, str.length());
				int i = new Integer(nStr.trim());
				append(dtStr,i,c);
			}
			else {
				StyleContext sc = StyleContext.getDefaultStyleContext();
				AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
				document.insertString(document.getLength(), "\n"+str,aset);
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	// called by the GUI is the connection failed
	// we reset our buttons, label, textfield
	void connectionFailed() {
		login.setEnabled(true);
		logout.setEnabled(false);
		whoIsIn.setEnabled(false);
		
		for(int i=0;i<ChatUtil.hm.size();i++) {
			emoj[i].setEnabled(false);
		}
	
		label.setText("Enter your login below");
		tf.setText(ChatUtil.prop.getProperty("default_user"));
		// reset port number and host name as a construction time
		tfPort.setText("" + defaultPort);
		tfServer.setText(defaultHost);
		// let the user change them
		tfServer.setEditable(false);
		tfPort.setEditable(false);
		tf.removeActionListener(this);
		connected = false;
	}
		
	/*
	* Button or JTextField clicked
	*/
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		// if it is the Logout button
		if(o == logout) {
			client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
			return;
		}
		// if it the who is in button
		if(o == whoIsIn) {
			client.sendMessage(new ChatMessage(ChatMessage.WHOISIN, ""));				
			return;
		}
		for(int i=0;i<ChatUtil.hm.size();i++) {
			if(o == emoj[i]) {
				client.sendMessage(new ChatMessage(ChatMessage.EMOJI1[i], ""));	
				return;
			}
		}
		if(o == emoji) {
			client.sendMessage(new ChatMessage(ChatMessage.EMOJI, ""));	
			return;
		}
		// ok it is coming from the JTextField
		if(connected) {
			// just have to send the message
			client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, tf.getText()));				
			tf.setText("");
			return;
		}

		if(o == login) {
			// ok it is a connection request
			username = tf.getText().trim();
			// empty username ignore it
			if(username.length() == 0)
				return;
			// empty serverAddress ignore it
			String server = tfServer.getText().trim();
			if(server.length() == 0)
				return;
			// empty or invalid port numer, ignore it
			String portNumber = tfPort.getText().trim();
			if(portNumber.length() == 0)
				return;
			int port = 0;
			try {
				port = Integer.parseInt(portNumber);
			}
			catch(Exception en) {
				return;   // nothing I can do if port number is not valid
			}

			// try creating a new Client with GUI
			client = new Client(server, port, username, this);
			// test if we can start the Client
			if(!client.start()) 
				return;
			tf.setText("");
			label.setText("What's on your Mind...!!!");
			connected = true;
			
			// disable login button
			login.setEnabled(false);
			// enable the 2 buttons
			logout.setEnabled(true);
			
			whoIsIn.setEnabled(true);
			for(int i=0;i<ChatUtil.hm.size();i++)
				emoj[i].setEnabled(true);
			// disable the Server and Port JTextField
			tfServer.setEditable(false);
			tfPort.setEditable(false);
			// Action listener for when the user enter a message
			tf.addActionListener(this);
		}

	}

	// to start the whole thing the server
	public static void main(String[] args) {
		ChatUtil.loadProperties();
		ChatUtil.setMap();
		ChatUtil.setColorList();
		ChatUtil.setColorToColorList();
		ChatMessage.setEmoji(ChatUtil.hm.size());
		new ClientGUI(ChatUtil.prop.getProperty("default_server"), Integer.parseInt(ChatUtil.prop.getProperty("default_port")));
	}

}
