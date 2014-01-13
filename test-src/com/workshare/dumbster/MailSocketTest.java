package com.workshare.dumbster;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.workshare.dumbster.HeaderData;
import com.workshare.dumbster.MailData;

import junit.framework.TestCase;

public class MailSocketTest extends TestCase {
	
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
