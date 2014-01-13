package com.workshare.dumbster;

import java.io.IOException;

public class ClientThread extends Thread {
	
	public ClientThread(int port, MailData mailData) {
		this.port = port;
		this.mailData = mailData;
	}
	
	public int port;
	
	public MailData mailData;
	
	public Exception exception;
	
	public void run() {
		try {
			MailSocket.openSocketAndWrite("localhost", this.port, this.mailData);
		} catch (IOException e) {
			exception = e;
		}
	}
}