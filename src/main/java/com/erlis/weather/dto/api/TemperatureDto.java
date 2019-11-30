package com.erlis.weather.dto.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class TemperatureDto implements Serializable {
    public Integer temp;
    public Double temp_min;
    public Double temp_max;
    public Integer pressure;
    public Integer sea_level;
    public Integer grnd_level;
    public Integer humidity;
    public Double temp_kf;

    @Override
    public String toString() {
        return "{\"TemperatureDto\":{"
                + "                        \"temp\":\"" + temp + "\""
                + ",                         \"temp_min\":\"" + temp_min + "\""
                + ",                         \"temp_max\":\"" + temp_max + "\""
                + ",                         \"pressure\":\"" + pressure + "\""
                + ",                         \"sea_level\":\"" + sea_level + "\""
                + ",                         \"grnd_level\":\"" + grnd_level + "\""
                + ",                         \"humidity\":\"" + humidity + "\""
                + ",                         \"temp_kf\":\"" + temp_kf + "\""
                + "}}";
    }
}
