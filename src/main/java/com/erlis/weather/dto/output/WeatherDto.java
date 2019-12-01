package com.erlis.weather.dto.output;

import lombok.Data;

@Data
public class WeatherDto {
    public Double temperature;
    public int humidity;
    public int pressure;

//    @Override
//    public String toString() {
//        return "{"
//                + "\"temperature\":\"" + temperature + "\""
//                + ",\"humidity\":\"" + humidity + "\""
//                + ",\"pressure\":\"" + pressure + "\""
//                + "}";
//    }
}
