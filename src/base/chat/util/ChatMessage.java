package base.chat.util;

import java.io.*;

public class ChatMessage implements Serializable {

	protected static final long serialVersionUID = 1112122200L;
	public static final int WHOISIN = 0;
	public static final int MESSAGE = 1;
	public static final int LOGOUT = 2;
	public static final int EMOJI = 3;
	/*public static int EMOJI1[]= {3,4,5,6,7,8,9,10,11,12,13,14};*/
	public static int EMOJI1[]= {};
	private int type;
	private String message;

	public ChatMessage(int type, String message) {
		this.type = type;
		this.message = message;
		EMOJI1 = new int[ChatUtil.hm.size()];
		int stv = LOGOUT+1;
		for(int i=0;i<ChatUtil.hm.size();i++) {
			EMOJI1[i]=stv;
			stv++;
		}
	}
	
	public static void setEmoji(int n) {
		EMOJI1 = new int[n];
		int stv = LOGOUT+1;
		for(int i=0;i<ChatUtil.hm.size();i++) {
			EMOJI1[i]=stv;
			stv++;
		}
	}
	
	public int getType() {
		return type;
	}
	public String getMessage() {
		return message;
	}

}
