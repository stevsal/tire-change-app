package com.example.smittirechangeapp.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Sites {
	List<Site> sites;

	public Sites(){}
	public List<Site> getSites() {
		return sites;
	}
}