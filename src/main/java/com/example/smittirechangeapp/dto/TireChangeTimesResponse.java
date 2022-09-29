package com.example.smittirechangeapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

//@JacksonXmlRootElement(localName = 	"TireChangeTimesResponse")
public class TireChangeTimesResponse {

	@JacksonXmlElementWrapper(useWrapping = false)
	@JsonProperty
	List<AvailableTime> availableTime;

	public List<AvailableTime> getAvailableTimes() {
		return availableTime;
	}

	public TireChangeTimesResponse() {
	}
}
