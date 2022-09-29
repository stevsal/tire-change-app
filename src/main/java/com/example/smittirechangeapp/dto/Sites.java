package com.example.smittirechangeapp.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = "classpath:config.json")
@ConfigurationProperties(prefix = "config")
public class Sites {
	List<Site> sites;

	public Sites(){}
	public List<Site> getSites() {
		return sites;
	}
}