package com.erlis.weather.dto.output;

import java.util.List;

public class WeatherReportDto {
    public WeatherReportDetailsDto weatherReportDetails;
    public WeatherDto currentWeatherReport;
    public List<ForecastDto> forecastReport;
}
