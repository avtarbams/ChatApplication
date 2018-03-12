package base.chat.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import base.chat.util.ChatUtil;

public class ChatCliente {
	public static void main(String[] args) throws IOException {
		Socket clientSocket = null;
		try {
			clientSocket = new Socket(ChatUtil.host, ChatUtil.port);

			// reading from keyboard (keyRead object)
			BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
			// sending to client (pwrite object)
			OutputStream ostream = clientSocket.getOutputStream();
			PrintWriter pwrite = new PrintWriter(ostream, true);

			// receiving from server ( receiveRead object)
			InputStream istream = clientSocket.getInputStream();
			BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
			System.out.println("Start the chat");
			String receiveMessage, sendMessage;
			while (true) {
				sendMessage = keyRead.readLine(); // writing message from keyborad
				pwrite.println(sendMessage); // sending messages to server
				pwrite.flush(); // flush the data
				if ((receiveMessage = receiveRead.readLine()) != null) // received from server
				{
					System.out.println(receiveMessage); // displaying at Console
				}
				Thread.sleep(1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(null!=clientSocket)
				clientSocket.close();
		}
	}
}
