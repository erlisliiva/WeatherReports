package com.erlis.weather.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileServiceTest {


    private FileService fileService;

    private String FILE_PATH = "C:\\Users\\Erlis\\IdeaProjects\\weatherApp\\weather\\src\\test\\resources\\";

    @BeforeEach
    public void setUp() {
        fileService = new FileService();
        //set fields
    }


    @Test
    void readFromFileIsReading() {
        try {
            List<String> cities = fileService.readFromFile("citynames.txt");
            assertNotNull(cities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .featuresToEnable(SerializationFeature.INDENT_OUTPUT)
                .build();
    }
}
