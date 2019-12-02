package com.erlis.weather.dto.output;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
public class WeatherReportDto {

    private String cod;
    public WeatherReportDetailsDto weatherReportDetails;
    public WeatherDto currentWeatherReport;
    public List<ForecastDto> forecastReport;

    public List<ForecastDto> getForecastReport() {
        if (forecastReport == null){
            forecastReport = new LinkedList<>();
        }
        return forecastReport;
    }

    @Override
    public String toString() {
        return "{"
                + "\"weatherReportDetails\":" + weatherReportDetails
                + ",\"currentWeatherReport\":" + currentWeatherReport
                + ",\"forecastReport\":" + forecastReport
                + "}";
    }
}
