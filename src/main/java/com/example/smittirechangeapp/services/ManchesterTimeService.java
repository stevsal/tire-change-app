package com.example.smittirechangeapp.services;

import com.example.smittirechangeapp.interfaces.IManchesterTimeService;
import com.example.smittirechangeapp.models.ContactInfo;
import com.example.smittirechangeapp.models.changeTime;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ManchesterTimeService implements IManchesterTimeService {
	private static final String BASE_URL = "http://localhost:9004/api/v2/tire-change-times";
	private final WebClient client = WebClient.builder()
			.baseUrl(BASE_URL)
			.build();

	@Override
	public List<changeTime> getAll() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = "from="+formatter.format(LocalDateTime.now());
		String url = "http://localhost:9004/api/v2/tire-change-times?" + date;
		var result = client.get()
				.uri(url)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse ->
						clientResponse.bodyToMono(String.class).map(Exception::new))
				.onStatus(HttpStatus::is4xxClientError, clientResponse ->
						clientResponse.bodyToMono(String.class).map(Exception::new))
				.bodyToMono(new ParameterizedTypeReference<List<changeTime>>() {})
				.block();

		List<changeTime> times = Objects.requireNonNull(result)
				.stream()
				.filter(changeTime -> changeTime.getAvailable())
				.collect(Collectors.toList());
		times.forEach(changeTime -> changeTime.setName("Manchester"));
		return times;
	}
	@Override
	public String bookTimeWithPost(int id, ContactInfo info) {
		var res = client.post()
				.uri(uriBuilder -> uriBuilder
						.path("/{id}/booking")
						.build(id))
				.body(Mono.just(info), ContactInfo.class)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse ->
						clientResponse.bodyToMono(String.class).map(Exception::new))
				.onStatus(HttpStatus::is4xxClientError, clientResponse ->
						clientResponse.bodyToMono(String.class).map(Exception::new))
				.bodyToMono(String.class)
				.block();
		int count = 2;
		count++;
		return res;
	}
}
