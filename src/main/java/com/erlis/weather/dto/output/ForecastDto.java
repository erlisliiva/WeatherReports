package com.erlis.weather.dto.output;

import lombok.Data;

@Data
public class ForecastDto {
    public String date;
    public WeatherDto weather;

    @Override
    public String toString() {
        return "{{"
                + "\"date\":\"" + date + "\""
                + ",\"weather\":" + weather
                + "}";
    }
}
