package com.erlis.weather.service;

import com.erlis.weather.client.RestClient;
import com.erlis.weather.dto.api.ApiResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherApiService {

    @Autowired
    private FileService fileService;

    @Autowired
    private RestClient restClient;

    public List<ApiResultDto> writeWeatherReportToFile() throws IOException {

        List<String> cities = fileService.readFromFile("citynames.txt");

        List<ApiResultDto> weatherReportDtos = cities.stream()
                .map(city -> restClient.getWeatherReportByCity(city))
//                .map(apiResultDto -> new DataMapper().map(apiResultDto))
                .collect(Collectors.toList());

        fileService.writeToFile(weatherReportDtos, "weatherreports.txt");

        return weatherReportDtos;
    }
}
