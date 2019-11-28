package com.erlis.weather.service;

import com.erlis.weather.client.RestClient;
import com.erlis.weather.controller.ApiController;
import com.erlis.weather.dto.api.*;
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
class WeatherApiServiceTest {

    @SpyBean
    private RestClient restClient;

    @Autowired
    private ApiController apiController;

    @Autowired
    private FileService fileService;

    private static final String fileName = "testfile.txt";
    private static final String TEST_FILE_PATH = "C:\\Users\\Erlis\\IdeaProjects\\weatherApp\\weather\\src\\main\\resources\\";

    @Test
    void countOfCitiesToWriteInFile() {
//        Mockito.doReturn(getMockObject()).when(restClient).getWeatherReportByCity(any());
        List<ApiResultDto> results = apiController.getResult();
        assertNotNull(results);
        assertEquals(3, results.size());
    }

    @Test
    void test(){
        ApiResultDto weatherReportByCity = Mockito.doReturn(getMockObject()).when(restClient).getWeatherReportByCity(any());
        assertNotNull(weatherReportByCity);
    }

    @Test
    void writeWeatherReportToFileCodIs200() {
        List<ApiResultDto> results = apiController.getResult();
        assertNotNull(results);
        assertEquals(3, results.size());
        assertTrue(results.stream().allMatch(apiResultDto -> "200".equals(apiResultDto.cod))); //null safe
    }

    @Test
    void checkForCorrectCityNameFromApi() {
        List<ApiResultDto> results = apiController.getResult();
        assertNotNull(results);
        assertEquals(3, results.size());
        assertTrue(results.stream().anyMatch(apiResultDto -> "Tallinn".equals(apiResultDto.city.name)));
        assertTrue(results.stream().anyMatch(apiResultDto -> "Tamsalu".equals(apiResultDto.city.name)));
        assertTrue(results.stream().anyMatch(apiResultDto -> "Rakvere".equals(apiResultDto.city.name)));
    }


    @Test
    void writeToFileIsWriting() {

        List<ApiResultDto> results = apiController.getResult();
        assertNotNull(results);
        File testFile = new File(TEST_FILE_PATH + fileName);
        assertTrue(testFile.exists());
        assertEquals(0, testFile.length());
        fileService.writeToFile(results, fileName);
        assertTrue(testFile.length() != 0);
        emptyOutATxtFile(testFile);
        assertEquals(0, testFile.length());

    }

    @Test
    void readFromFileIsReading(){
        try {
            List<String> cities = fileService.readFromFile("citynames.txt");
            assertNotNull(cities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ApiResultDto getMockObject() {

        ApiResultDto apiResultDto = new ApiResultDto();
        apiResultDto.setCnt(2);
        apiResultDto.setCod("200");
        apiResultDto.setMessage(1);
        TemperatureDto temperatureDto = new TemperatureDto();
        temperatureDto.setTemp(-5);
        temperatureDto.setHumidity(75);
        temperatureDto.setPressure(985);
        CityDto cityDto = new CityDto();
        cityDto.setName("Tallinn");
        CoordinatesDto coordinatesDto = new CoordinatesDto();
        coordinatesDto.setLat(10.0);
        coordinatesDto.setLon(20.0);
        cityDto.setCoord(coordinatesDto);
        apiResultDto.setCity(cityDto);
        ApiForecastDto forecastDto = new ApiForecastDto();
        forecastDto.setMain(temperatureDto);
        forecastDto.setDt_txt("2019-11-28 12:00:00");


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