package com.erlis.weather.service;

import com.erlis.weather.dto.output.ForecastDto;
import com.erlis.weather.dto.output.WeatherDto;
import com.erlis.weather.dto.output.WeatherReportDetailsDto;
import com.erlis.weather.dto.output.WeatherReportDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileServiceTest {


    private FileService fileService;

    private static final String fileNameToWrite = "weatherreports.txt";
    private static final String fileNameToRead = "citynames.txt";
    private static final String TEST_FILE_PATH = "C:\\Users\\Erlis\\IdeaProjects\\WeatherReports\\src\\test\\resources\\";

    @BeforeEach
    public void setUp() {
        fileService = new FileService();
    }

    @Test
    void readFromFileIsReading() {
        try {
            List<String> cities = fileService.readFromFile(fileNameToRead);
            assertNotNull(cities);
            assertEquals(1, cities.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testIfWriteToFileIsWriting() throws IllegalAccessException, InstantiationException, NoSuchFieldException, ClassNotFoundException {

        List<WeatherReportDto> weatherReportDtos = new LinkedList<>();
        weatherReportDtos.add(getTestObject());
        getObjectMapper();

        fileService.writeToFile(weatherReportDtos, fileNameToWrite);


    }

    private void getObjectMapper() throws NoSuchFieldException, IllegalAccessException {

        Field declaredField = FileService.class.getDeclaredField("objectMapper");
        declaredField.setAccessible(true);
        declaredField.set(fileService, objectMapper());
    }

    private ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .featuresToEnable(SerializationFeature.INDENT_OUTPUT)
                .build();
    }

    private WeatherReportDto getTestObject() {

        WeatherReportDto weatherReportDto = new WeatherReportDto();
        WeatherDto weatherDto = new WeatherDto();
        WeatherReportDetailsDto weatherReportDetailsDto = new WeatherReportDetailsDto();
        ForecastDto forecastDto = new ForecastDto();
        weatherReportDto.setCurrentWeatherReport(weatherDto);
        weatherReportDto.currentWeatherReport.setTemperature(-5.0);
        weatherReportDto.currentWeatherReport.setHumidity(5000);
        weatherReportDto.setWeatherReportDetails(weatherReportDetailsDto);
        weatherReportDto.weatherReportDetails.setCity("Tallinn");

        return weatherReportDto;
    }
}
