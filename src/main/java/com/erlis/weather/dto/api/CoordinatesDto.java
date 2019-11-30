package com.erlis.weather.dto.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class CoordinatesDto implements Serializable {
    public Double lon;
    public Double lat;

    @Override
    public String toString() {
        return "{\"CoordinatesDto\":{"
                + "                        \"lon\":\"" + lon + "\""
                + ",                         \"lat\":\"" + lat + "\""
                + "}}";
    }
}
