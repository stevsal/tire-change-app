package com.example.smittirechangeapp.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "london.tireChangeBookingResponse")
public class ContactInfo {
	@XmlElement
	private String contactInformation;

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}
}
