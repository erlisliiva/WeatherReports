package com.erlis.weather.service;

import com.erlis.weather.client.RestClient;
import com.erlis.weather.controller.ApiController;
import com.erlis.weather.dto.api.*;
import com.erlis.weather.dto.output.ForecastDto;
import com.erlis.weather.dto.output.WeatherDto;
import com.erlis.weather.dto.output.WeatherReportDetailsDto;
import com.erlis.weather.dto.output.WeatherReportDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ApiControllerIntegrationTest {

    @SpyBean
    private RestClient restClient;

    @Autowired
    private ApiController apiController;

    @Autowired
    private FileService fileService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String fileName = "weatherreports.txt";
    private static final String TEST_FILE_PATH = "C:\\Users\\Erlis\\IdeaProjects\\WeatherReports\\src\\test\\resources\\";


    //integration
    @Test
    void getCod200IfGETReturnsValue() {
        List<WeatherReportDto> results = apiController.getResult();
        assertNotNull(results);
        assertEquals("200", results.get(0).getCod());

    }

    //integration
    @Test
    void getCod200IfGETReturnsValueWithCityName() {
        List<WeatherReportDto> results = apiController.getResultForSpecifiedCity("Tallinn");
        assertNotNull(results);
        assertEquals("200", results.get(0).getCod());
    }

    //integration
    @Test
    void forecastHasCorrectTempValue() {
        List<WeatherReportDto> results = apiController.getResult();
        assertNotNull(results);
        assertNotNull(results.get(0).getForecastReport().get(0).getWeather().getTemperature());
    }

    //mock
    @Test
    void countOfCitiesToWriteInFile() {
        Mockito.doReturn(getMockObjectInput()).when(restClient).getWeatherReportByCity(any());
        List<WeatherReportDto> results = apiController.getResult();
        assertNotNull(results);
        assertEquals(1, results.size());
    }

    //mock
    @Test
    void checkForCorrectCityNameFromApi() {
        Mockito.doReturn(getMockObjectInput()).when(restClient).getWeatherReportByCity(any());
        List<WeatherReportDto> results = apiController.getResult();
        assertNotNull(results);
        assertEquals(1, results.size());
        assertTrue(results.stream().anyMatch(weatherReportDto -> "Tallinn".equals(weatherReportDto.getWeatherReportDetails().city)));
    }

    //mock
    @Test
    void writeToFileIsWritingCorrectValue() throws IOException {

        Mockito.doReturn(getMockObjectInput()).when(restClient).getWeatherReportByCity(any());
        List<WeatherReportDto> results = apiController.getResult();
        assertNotNull(results);
        File testFile = new File(TEST_FILE_PATH + fileName);
        assertTrue(testFile.exists());
        assertTrue(testFile.length() != 0);
        List<String> elements = fileService.readFromFile(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : elements) {
            stringBuilder.append(s);
        }
        List<WeatherReportDto> weatherReportDtos = objectMapper.readValue(stringBuilder.toString(), new TypeReference<>() {
        });
        assertEquals(results.get(0), weatherReportDtos.get(0));
        emptyOutATxtFile(testFile);
        assertEquals(0, testFile.length());

    }

    private WeatherReportDto getMockObjectOutput() {

        WeatherReportDto weatherReportDto = new WeatherReportDto();
        WeatherReportDetailsDto weatherReportDetailsDto = new WeatherReportDetailsDto();
        WeatherDto weatherDto = new WeatherDto();
        WeatherDto currentWeatherDto = new WeatherDto();
        ForecastDto forecastDto = new ForecastDto();

        weatherReportDto.setWeatherReportDetails(weatherReportDetailsDto);
        weatherReportDto.weatherReportDetails.setCity("Tallinn");
        weatherReportDto.weatherReportDetails.setTemperatureUnit("Celsius");
        weatherReportDto.weatherReportDetails.setCoordinates("59.222, 56.233");

        weatherReportDto.setCurrentWeatherReport(currentWeatherDto);

        weatherReportDto.currentWeatherReport.setTemperature(5.0);
        weatherReportDto.currentWeatherReport.setPressure(1000);
        weatherReportDto.currentWeatherReport.setHumidity(77);

        forecastDto.setDate("2019-12-03 03:00:00");
        weatherDto.setHumidity(78);
        weatherDto.setPressure(7118);
        weatherDto.setTemperature(8.0);
        forecastDto.setWeather(weatherDto);
        weatherReportDto.getForecastReport().add(forecastDto);

        return weatherReportDto;
    }

    private ApiResultDto getMockObjectInput() {

        ApiResultDto apiResultDto = new ApiResultDto();
        ApiForecastDto apiForecastDto = new ApiForecastDto();
        CityDto cityDto = new CityDto();
        TemperatureDto temperatureDto = new TemperatureDto();
        CoordinatesDto coordinatesDto = new CoordinatesDto();

        coordinatesDto.setLon(50.99);
        coordinatesDto.setLat(50.99);
        cityDto.setCoord(coordinatesDto);
        cityDto.setName("Tallinn");
        apiResultDto.setCity(cityDto);
        apiResultDto.setCod("200");

        apiForecastDto.setDt_txt("2019-12-03 00:00:00");

        temperatureDto.setTemp(5.0);
        temperatureDto.setPressure(77);
        temperatureDto.setHumidity(1000);
        apiForecastDto.setMain(temperatureDto);
        apiResultDto.getList().add(apiForecastDto);

        return apiResultDto;

    }

    private void emptyOutATxtFile(File file) {

        PrintWriter writer;
        try {
            writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}