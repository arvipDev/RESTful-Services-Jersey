package com.pluralsight.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User 
{
	private String identity;
	private String fullname;
	
	@XmlElement(name="id")
	public String getId() {
		return identity;
	}
	public void setId(String identity) {
		this.identity = identity;
	}
	
	@XmlElement(name="name")
	public String getName() {
		return fullname;
	}
	public void setName(String name) {
		this.fullname = name;
	}
	
	
}
