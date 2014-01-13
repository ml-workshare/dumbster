package com.workshare.dumbster;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.workshare.dumbster.HeaderData;
import com.workshare.dumbster.MailData;
import com.workshare.dumbster.MailSocket;

import junit.framework.TestCase;

public class MailSocketTest extends TestCase {
	
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
	
	  public void testMailSocketRoundTrip() throws InterruptedException {
		  
    	// arrange
		ServerThread serverThread = new ServerThread(1234);
		
		MailData mailData = new MailData();
		mailData.body = "ABoday";
		mailData.headers.add(new HeaderData("asdf", "fdsa"));
		
		ClientThread clientThread = new ClientThread(1234, mailData);
		  
		// act
		serverThread.start();
		clientThread.start();
		
		Date timeout = DateUtils.addSeconds(new Date(), 5);
		while(new Date().before(timeout) &&  serverThread.isAlive() && clientThread.isAlive()) {
			Thread.sleep(100);
		}
		
        // assert
        assertEquals(clientThread.mailData.body, serverThread.mailData.body);
        assertEquals(clientThread.mailData.headers.size(), serverThread.mailData.headers.size());
        assertEquals(clientThread.mailData.getHeader("key"), serverThread.mailData.getHeader("key"));
        
    }
}
