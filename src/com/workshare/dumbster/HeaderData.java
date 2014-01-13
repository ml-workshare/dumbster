package com.workshare.dumbster;

import java.io.Serializable;

public class HeaderData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String key;
	public String value;
	
	public HeaderData(String key, String value)
	{
		this.key = key;
		this.value = value;
	}
}