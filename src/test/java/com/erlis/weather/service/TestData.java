package com.erlis.weather.service;

import com.erlis.weather.dto.api.*;
import com.erlis.weather.dto.output.ForecastDto;
import com.erlis.weather.dto.output.WeatherDto;
import com.erlis.weather.dto.output.WeatherReportDetailsDto;
import com.erlis.weather.dto.output.WeatherReportDto;

public class TestData {

    public static WeatherReportDto getMockObjectOutput() {

        WeatherReportDto weatherReportDto = new WeatherReportDto();
        WeatherReportDetailsDto weatherReportDetailsDto = new WeatherReportDetailsDto();
        WeatherDto weatherDto = new WeatherDto();
        WeatherDto currentWeatherDto = new WeatherDto();
        ForecastDto forecastDto = new ForecastDto();

        weatherReportDto.setWeatherReportDetails(weatherReportDetailsDto);
        weatherReportDto.weatherReportDetails.setCity("Tallinn");
        weatherReportDto.weatherReportDetails.setTemperatureUnit("Celsius");
        weatherReportDto.weatherReportDetails.setCoordinates("50.99, 50.99");

        weatherReportDto.setCurrentWeatherReport(currentWeatherDto);

        weatherReportDto.currentWeatherReport.setTemperature(5.0);
        weatherReportDto.currentWeatherReport.setPressure(77);
        weatherReportDto.currentWeatherReport.setHumidity(1000);

        forecastDto.setDate("2019-12-03 00:00:00");
        weatherDto.setHumidity(1000);
        weatherDto.setPressure(77);
        weatherDto.setTemperature(5.0);
        forecastDto.setWeather(weatherDto);
        weatherReportDto.getForecastReport().add(forecastDto);

        return weatherReportDto;
    }

    public static ApiResultDto getMockObjectInput() {

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
}
