package com.erlis.weather.service;

import com.erlis.weather.dto.api.*;
import com.erlis.weather.dto.output.ForecastDto;
import com.erlis.weather.dto.output.WeatherDto;
import com.erlis.weather.dto.output.WeatherReportDetailsDto;
import com.erlis.weather.dto.output.WeatherReportDto;
import com.erlis.weather.mapper.DataMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.erlis.weather.service.TestData.getMockObjectInput;
import static com.erlis.weather.service.TestData.getMockObjectOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataMapperTest {

    private DataMapper dataMapper;


    @BeforeEach
    public void setUp() {
        dataMapper = new DataMapper();
    }

    @Test
    void objectMapperReturnCorrectObject() {
        String expected = getMockObjectOutput().toString();
        String actual = dataMapper.map(getMockObjectInput()).toString();
        assertEquals(expected, actual);
    }

}
