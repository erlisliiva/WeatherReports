package com.erlis.weather.client;

import com.erlis.weather.dto.api.ApiResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class RestClient {

    private static final String REST_URI = "https://api.openweathermap.org/data/2.5/forecast";
    @Value("${appid}")
    private String appid;
    private static final String METRIC = "metric";

    @Autowired
    private Client client;

    public ApiResultDto getWeatherReportByCity(String cityName) {
        Response response = client.target(REST_URI)
                .queryParam("q", cityName)
                .queryParam("cnt", 24)
                .queryParam("appid", appid)
                .queryParam("units", METRIC)
                .request(MediaType.APPLICATION_JSON)
                .get();

            if (!Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily())) {
                throw new NotFoundException("No city found!");
            }
        return response.readEntity(ApiResultDto.class);

    }
}