package com.workshare.dumbster;

import com.workshare.dumbster.HeaderData;
import com.workshare.dumbster.MailData;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class MailDataTest 
    extends TestCase
{
  
    public void testSerialization() {
    	// arrange
        MailData dataIn = new MailData();
        dataIn.body = "boday";
        dataIn.headers.add( new HeaderData("key", "value"));
        
        // act
        String value = MailData.toXml(dataIn);
        MailData dataOut = MailData.fromXml(value);
        
        // assert
        assertEquals(dataIn.body, dataOut.body);
        assertEquals(dataIn.headers.size(), dataOut.headers.size());
        assertEquals(dataIn.getHeader("key"), dataOut.getHeader("key"));
        
    }
}
