package com.erlis.weather.client;

import com.erlis.weather.dto.api.ApiResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Component
public class RestClient {

    private static final String REST_URI = "https://api.openweathermap.org/data/2.5/forecast";
    @Value("${appid}")
    private String appid;
    private static final String METRIC = "metric";

    @Autowired
    private Client client;

    public ApiResultDto getWeatherReportByCity(String cityName) {
        return client
                .target(REST_URI)
                .queryParam("q", cityName)
                .queryParam("cnt", 24)
                .queryParam("appid", appid)
                .queryParam("units", METRIC)
                .request(MediaType.APPLICATION_JSON)
                .get(ApiResultDto.class);
    }
}