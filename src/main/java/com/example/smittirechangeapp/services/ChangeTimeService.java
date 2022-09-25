package com.example.smittirechangeapp.services;

import org.springframework.web.reactive.function.client.WebClient;

public class ChangeTimeService {
	private WebClient client = WebClient.create();

	/*public List<changeTime> getAll() throws IOException {
		String url = "";
		String resType = "xml";
		var result = client.get()
				.uri(url)
				.retrieve()
				.onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals, clientResponse ->
						clientResponse.bodyToMono(String.class).map(Exception::new))
				.onStatus(HttpStatus.BAD_REQUEST::equals, clientResponse ->
						clientResponse.bodyToMono(String.class).map(Exception::new));
		if(resType == "xml"){
			XmlMapper xmlMapper = new XmlMapper();
			xmlMapper.registerModule(new JavaTimeModule());
			xmlTme value = xmlMapper.readValue(result.bodyToMono(String.class).block(), xmlTme.class);
			var times = value.getAvailableTimes().forEach(changeTime -> {
				changeTime.setName("London");
				changeTime.setAvailable(true);
			});
			return
		}
		if(resType == "json") result.bodyToMono(new ParameterizedTypeReference<List<changeTime>>() {});

	}*/
}
