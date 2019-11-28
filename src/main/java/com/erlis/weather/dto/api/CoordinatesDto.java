package com.erlis.weather.dto.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class CoordinatesDto implements Serializable {
    public Double lon;
    public Double lat;
}
