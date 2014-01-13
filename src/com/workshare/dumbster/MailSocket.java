package com.workshare.dumbster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MailSocket
{
	public static MailData openSocketAndRead(int portNumber) throws IOException {
		
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		try { 
			
			serverSocket = new ServerSocket(portNumber);
			clientSocket = serverSocket.accept();
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
			
			String inputLine;
			StringBuilder sb = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
			}
			
			return MailData.fromXml(sb.toString());
			
		}
		finally {
			
			if( in != null) {
				in.close();
			}
			if( out != null) {
				out.close();
			}
			if( clientSocket != null) {
				clientSocket.close();
			}
			if( serverSocket != null) {
				serverSocket.close();
			}
		}
	}
	
	public static void openSocketAndWrite(String hostname, int port, MailData data) throws IOException {
		
		Socket socket = null;
		OutputStreamWriter writer = null;
		
		try {
			
			socket = new Socket(hostname, port);
			writer = new OutputStreamWriter(socket.getOutputStream());
			writer.write(MailData.toXml(data));
			
		}
		finally {
			
			if( writer != null) {
				writer.close();
			}
			if( socket != null) {
				socket.close();
			}
		}
	}
}
