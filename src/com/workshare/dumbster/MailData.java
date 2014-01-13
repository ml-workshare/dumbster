package com.workshare.dumbster;

import java.io.Serializable;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;

public class MailData implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayList<HeaderData> headers;
	
	public String body;
	
	public MailData() {
		this.headers = new ArrayList<HeaderData>();
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