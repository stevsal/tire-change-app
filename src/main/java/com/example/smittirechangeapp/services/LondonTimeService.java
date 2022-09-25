package com.example.smittirechangeapp.services;

import com.example.smittirechangeapp.interfaces.ILondonTimeService;
import com.example.smittirechangeapp.models.ContactInfo;
import com.example.smittirechangeapp.models.changeTime;
import com.example.smittirechangeapp.models.XmlResponseModel;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LondonTimeService implements ILondonTimeService {

	private static final String BASE_URL = "http://localhost:9003/api/v1/tire-change-times";
	private final WebClient client = WebClient.builder()
			.filter(errorHandler())
			.baseUrl(BASE_URL)
			.build();

	public static ExchangeFilterFunction errorHandler() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			if (clientResponse.statusCode().is5xxServerError()) {
				return clientResponse.bodyToMono(String.class)
						.flatMap(errorBody -> Mono.error(new Exception(errorBody)));
			} else if (clientResponse.statusCode().is4xxClientError()) {
				return clientResponse.bodyToMono(String.class)
						.flatMap(errorBody -> Mono.error(new Exception(errorBody)));
			} else {
				return Mono.just(clientResponse);
			}
		});
	}

	public List<changeTime> getAll() throws IOException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateFrom = formatter.format(LocalDateTime.now());
		String dateUntil = formatter.format(LocalDateTime.now().plusMonths(3));
		/*var result = client.get()
				.uri(url)
				.accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML )
				.retrieve()
				.bodyToMono(TireChangeTimesResponse.class)
				.block();

		return result;*/
		var result = client.get()
				.uri(uriBuilder -> uriBuilder
						.path("/available")
						.queryParam("from", dateFrom)
						.queryParam("until", "2022-13-06")
						.build())
				.retrieve()
				.bodyToMono(String.class)
				.block();

		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.registerModule(new JavaTimeModule());
		XmlResponseModel value = xmlMapper.readValue(result, XmlResponseModel.class);
		List<changeTime> times = value.getAvailableTimes();
		value.getAvailableTimes().forEach(changeTime -> {
			changeTime.setName("London");
			changeTime.setAvailable(true);
		});

		return value.getAvailableTimes();
	}

	public String bookTimeWithPut(int id, ContactInfo info) {

		return ";";
	}

}
