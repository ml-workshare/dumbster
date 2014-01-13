package com.workshare.dumbster;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.dumbster.smtp.MailMessage;
import com.dumbster.smtp.MailMessageImpl;
import com.thoughtworks.xstream.XStream;

public class MailData implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayList<HeaderData> headers = new ArrayList<HeaderData>();
	
	public String body;
	
	public MailData(MailMessage message) {
		
		Iterator<String> iterator = message.getHeaderNames();
		while(iterator.hasNext()) {
			String header = iterator.next();
			String[] values = message.getHeaderValues(header);
			for(String value : values) {
				this.headers.add(new HeaderData(header, value));
			}
		}
		
		this.body = message.getBody();
	}
	
	public MailData() {
	}

	public String getHeader(String key) {
		
		for( HeaderData data : headers ) {
			if( key.equals(data.key)) {
				return data.value;
			}
		}
		return null;
	}
	
	public static String toXml(MailData mailData) {
		XStream xstream = new XStream();
		return xstream.toXML(mailData);		
	}
	
	public static MailData fromXml(String value) {
		XStream xstream = new XStream();
		return (MailData) xstream.fromXML(value);
	}
}