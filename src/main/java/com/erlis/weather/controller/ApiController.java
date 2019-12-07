package com.erlis.weather.controller;

import com.erlis.weather.dto.output.WeatherReportDto;
import com.erlis.weather.service.WeatherApiService;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("forecast")
@Controller
public class ApiController {

    @Autowired
    private WeatherApiService weatherApiService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<WeatherReportDto> getResult() {
        try {
            return weatherApiService.writeWeatherReportToFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Path("/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WeatherReportDto> getResultForSpecifiedCity(@PathParam("city") String city) {
        return weatherApiService.writeWeatherReportToFileSpecificCity(city);
    }


}
