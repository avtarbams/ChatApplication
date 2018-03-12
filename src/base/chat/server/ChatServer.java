package base.chat.server;

import java.net.*;

import base.chat.util.ChatUtil;

import java.io.*;

public class ChatServer {
	public static void main(String[] args) throws IOException {
		ServerSocket sersock = null;
		try {
			sersock = new ServerSocket(ChatUtil.port);

			System.out.println("Server is getting ready for chatting");
			Socket sock = sersock.accept();
			// reading from keyboard (keyRead object)
			BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
			// sending to client (pwrite object)
			OutputStream ostream = sock.getOutputStream();
			PrintWriter pwrite = new PrintWriter(ostream, true);

			// receiving from server ( receiveRead object)
			InputStream istream = sock.getInputStream();
			BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

			String receiveMessage, sendMessage;
			while (true) {
				if ((receiveMessage = receiveRead.readLine()) != null) {
					System.out.println(receiveMessage);
				}
				sendMessage = keyRead.readLine();
				pwrite.println(sendMessage);
				pwrite.flush();
				Thread.sleep(1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			if(null!=sersock) {
				sersock.close();
			}
		}
	}
}