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
}
