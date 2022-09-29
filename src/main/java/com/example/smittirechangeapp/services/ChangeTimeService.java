package com.example.smittirechangeapp.services;

import com.example.smittirechangeapp.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ChangeTimeService {

	RestTemplate restTemplate = new RestTemplate();

	List<Site> configSites = getSitesFromConfig();

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public List<AvailableTime> getAll(Filters filter) {
		List<Site> sites = configSites;
		List<AvailableTime> timeList = new ArrayList<>();
		String url;
		for (Site site : sites) {
			url = urlBuilder(site.url, filter.getFrom(), filter.getUntil());
			ResponseEntity<AvailableTime[]> res = restTemplate.getForEntity(url, AvailableTime[].class);
			var resTimes = Arrays.stream(res.getBody()).toList();
			resTimes.forEach(availableTime -> {
				availableTime.setName(site.name);
				availableTime.setAddress(site.address);
			});
			timeList.addAll(resTimes);
		}
		return timeList;
	}

	public TireChangeBookingResponse bookTimeWithPost(int id, ContactInfo info) {
		HttpHeaders headers = new HttpHeaders();
		AvailableTime response = new AvailableTime();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ContactInfo> requestBody = new HttpEntity<ContactInfo>(info, headers);
		var res = restTemplate.postForObject("http://localhost:9004/api/v2/tire-change-times/"+id+"/booking", requestBody, TireChangeBookingResponse.class);
		return res;
	}

	public TireChangeBookingResponse bookTimeWithPut(String uuid, ContactInfo info) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<ContactInfo> requestBody = new HttpEntity<ContactInfo>(info, headers);
		var res = restTemplate.exchange("http://localhost:9003/api/v1/tire-change-times/{uuid}/booking", HttpMethod.PUT, requestBody, TireChangeBookingResponse.class, uuid).getBody();
		return res;
	}

	public String urlBuilder(String url, LocalDateTime from, LocalDateTime until) {
		if (from != null && until != null){
			return UriComponentsBuilder.fromUriString(url)
					.queryParam("from", from)
					.queryParam("until", until).build().toUriString();
		} else {
			return UriComponentsBuilder.fromUriString(url)
					.queryParam("from", formatter.format(LocalDateTime.now()))
					.queryParam("until", formatter.format(LocalDateTime.now().plusMonths(3))).build().toUriString();
		}
	}

	public List<Site> filterSites(List<Site> sites,Filters filter){
		ArrayList<String> filterTypes = new ArrayList<>();
		if(filter.isCar()) { filterTypes.add("car");}
		if(filter.isTruck()) { filterTypes.add("truck");}
		for (Site site: sites) {
			if(filter.getName() != site.name) sites.remove(site);
		}
		return sites;
	}

	public List<Site> getSitesFromConfig() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			File configFile = new ClassPathResource("config.json").getFile();
			var data = mapper.readValue(configFile, Sites.class);
			return data.getSites();
		} catch (IOException e) {
			return new ArrayList<Site>();
		}
	}
}
