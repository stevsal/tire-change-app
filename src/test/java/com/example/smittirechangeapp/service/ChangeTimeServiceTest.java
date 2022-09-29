package com.example.smittirechangeapp.service;

import com.example.smittirechangeapp.dto.Filters;
import com.example.smittirechangeapp.services.ChangeTimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ChangeTimeServiceTest {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ChangeTimeService changeTimeService;

    private MockRestServiceServer mockServer;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void getAllTest() {
        Filters filter = new Filters();
        mockServer.expect(ExpectedCount.once(),
                requestTo("http://localhost:9004/api/v2/tire-change-times?from=2022-09-25"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK));
        mockServer.verify();
    }

}
