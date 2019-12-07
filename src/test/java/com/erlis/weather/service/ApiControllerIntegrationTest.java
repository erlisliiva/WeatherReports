package com.erlis.weather.service;

import com.erlis.weather.client.RestClient;
import com.erlis.weather.controller.ApiController;
import com.erlis.weather.dto.output.WeatherReportDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import static com.erlis.weather.service.TestData.getMockObjectInput;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class ApiControllerIntegrationTest {

    @SpyBean
    private RestClient restClient;

    @Autowired
    private ApiController apiController;

    @SpyBean
    private FileService fileService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String fileName = "weatherreports.txt";
    private static final String TEST_FILE_PATH = "C:\\Users\\Erlis\\IdeaProjects\\WeatherReports\\src\\test\\resources\\";


    @BeforeEach
    void setUp() {
        emptyOutATxtFile();
    }

    @Test
    void getCod200IfGETReturnsValue() {
        List<WeatherReportDto> results = apiController.getResult();
        assertNotNull(results);
//        assertEquals("200", results.get(0).getCod());
        verify(restClient).getWeatherReportByCity(anyString());
    }

    @Test
    void getCod200IfGETReturnsValueWithCityName() {
        List<WeatherReportDto> results = apiController.getResultForSpecifiedCity("Tallinn");
        assertNotNull(results);
//        assertEquals("200", results.get(0).getCod());
    }

    @Test
    void countOfCitiesToWriteInFile() {
        Mockito.doReturn(getMockObjectInput()).when(restClient).getWeatherReportByCity(any());
        List<WeatherReportDto> results = apiController.getResult();
        assertNotNull(results);
        assertEquals(1, results.size());

        //assertEquals()...
    }

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
        emptyOutATxtFile();
        assertEquals(0, testFile.length());
    }

    @Test
    void mockReadingAndWritingFromFile() throws IOException {
        List<String> list = Collections.singletonList("Tamsalu");
        doReturn(list).when(fileService).readFromFile(any());
        doNothing().when(fileService).writeToFile(any(), any());
        File testFile = new File(TEST_FILE_PATH + fileName);
        assertTrue(testFile.exists());
        assertEquals(0, testFile.length());

    }

    @Test
    void callingApiWhenCityIsRead() {
        String city = "tallinn";
        List<WeatherReportDto> results = apiController.getResultForSpecifiedCity(city);
        verify(fileService, times(1)).writeToFile(eq(results), any());
        assertEquals(1, results.size());
    }

    private void emptyOutATxtFile() {
        File testFile = new File(TEST_FILE_PATH + fileName);
        PrintWriter writer;
        try {
            writer = new PrintWriter(testFile);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}