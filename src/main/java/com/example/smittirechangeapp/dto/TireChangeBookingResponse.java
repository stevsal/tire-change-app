package com.example.smittirechangeapp.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "london.tireChangeBookingResponse")
public class TireChangeBookingResponse {
	@JacksonXmlElementWrapper(localName = "time")
	public String time;
	@JacksonXmlElementWrapper(localName = "uuid")
	public String id;
	public String fullInfo;
	public boolean available;
}
