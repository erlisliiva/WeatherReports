package com.erlis.weather.dto.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class CityDto implements Serializable {
    public Integer id;
    public String name;
    public CoordinatesDto coord;
    public String country;
    public Long population;
    public Integer timezone;
    public Long sunrise;
    public Long sunset;
}
