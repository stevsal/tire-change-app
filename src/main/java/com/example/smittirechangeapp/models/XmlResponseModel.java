package com.example.smittirechangeapp.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

public class XmlResponseModel {

	@JacksonXmlProperty(localName = "availableTime")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<changeTime> availableTimes = new ArrayList<>();

	public List<changeTime> getAvailableTimes() {
		return availableTimes;
	}
	@JacksonXmlProperty(localName = "errorResponse")
	public ErrorModel errorStatus;

	public void setAvailableTimes(List<changeTime> availableTimes) {
		this.availableTimes = availableTimes;
	}

}
