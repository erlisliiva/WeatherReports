package com.erlis.weather.service;

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

import static com.erlis.weather.service.TestData.getMockObjectOutput;
import static org.junit.jupiter.api.Assertions.*;

public class FileServiceTest {


    private FileService fileService;

    private static final String fileNameToWrite = "weatherreports.txt";
    private static final String fileNameToRead = "citynames.txt";
    private static final String TEST_FILE_PATH = "C:\\Users\\Erlis\\IdeaProjects\\WeatherReports\\src\\test\\resources\\";

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        fileService = new FileService();
        setPrivateFields();
    }


    @Test
    void shouldThrowExceptionWhenFileIsEmpty() {
        assertThrows(IOException.class, () -> fileService.readFromFile(null));
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
    void testIfWriteToFileIsWriting() {

        List<WeatherReportDto> weatherReportDtos = new LinkedList<>();
        weatherReportDtos.add(getMockObjectOutput());
        fileService.writeToFile(weatherReportDtos, fileNameToWrite);

    }

    private void setPrivateFields() throws NoSuchFieldException, IllegalAccessException {

        Field objectMapper = FileService.class.getDeclaredField("objectMapper");
        objectMapper.setAccessible(true);
        objectMapper.set(fileService, objectMapper());

        Field resourcePath = FileService.class.getDeclaredField("resourcePath");
        resourcePath.setAccessible(true);
        resourcePath.set(fileService, TEST_FILE_PATH);
    }

    private ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .featuresToEnable(SerializationFeature.INDENT_OUTPUT)
                .build();
    }

}
