package com.workshare.dumbster;

import java.io.IOException;

public class ServerThread extends Thread {
	
	public ServerThread(int port) {
		this.port = port;
	}
	
	public int port;
	
	public MailData mailData;
	
	public Exception exception;
	
	public void run() {
		try {
			mailData = MailSocket.openSocketAndRead(port);
		} catch (IOException e) {
			exception = e;
		}
	}
}