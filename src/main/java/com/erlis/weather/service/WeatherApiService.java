package com.erlis.weather.service;

import com.erlis.weather.client.RestClient;
import com.erlis.weather.dto.output.WeatherReportDto;
import com.erlis.weather.mapper.DataMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherApiService {

    @Autowired
    private FileService fileService;

    @Autowired
    private RestClient restClient;

    @Autowired
    private DataMapper dataMapper;

    public List<WeatherReportDto> writeWeatherReportToFile() throws IOException {

        List<String> cities = fileService.readFromFile("citynames.txt");
        List<WeatherReportDto> weatherReportDtos = cities.stream()
                .map(city -> restClient.getWeatherReportByCity(city))
                .map(apiResultDto -> new DataMapper().map(apiResultDto))
                .collect(Collectors.toList());
        fileService.writeToFile(weatherReportDtos, "weatherreports.txt");

        return weatherReportDtos;
    }

    public List<WeatherReportDto> writeWeatherReportToFileSpecificCity(String cityName) {
        List<String> list = new LinkedList<>();
        list.add(cityName);

        List<WeatherReportDto> weatherReportDtos = list.stream()
                .map(item -> restClient.getWeatherReportByCity(item))
                .map(weather -> new DataMapper().map(weather)).collect(Collectors.toList());

        fileService.writeToFile(weatherReportDtos, "weatherreports.txt");

        return weatherReportDtos;
    }

}
