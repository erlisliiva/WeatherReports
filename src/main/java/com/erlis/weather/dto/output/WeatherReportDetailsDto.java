package com.erlis.weather.dto.output;

import lombok.Data;

@Data
public class WeatherReportDetailsDto {
    public String city;
    public String coordinates;
    public String temperatureUnit;

    @Override
    public String toString() {
        return "{"
                + "\"city\":\"" + city + "\""
                + ",\"coordinates\":\"" + coordinates + "\""
                + ",\"temperatureUnit\":\"" + temperatureUnit + "\""
                + "}";
    }
}
