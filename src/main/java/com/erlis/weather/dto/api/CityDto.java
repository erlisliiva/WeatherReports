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

    @Override
    public String toString() {
        return "{\"CityDto\":{"
                + "                        \"id\":\"" + id + "\""
                + ",                         \"name\":\"" + name + "\""
                + ",                         \"coord\":" + coord
                + ",                         \"country\":\"" + country + "\""
                + ",                         \"population\":\"" + population + "\""
                + ",                         \"timezone\":\"" + timezone + "\""
                + ",                         \"sunrise\":\"" + sunrise + "\""
                + ",                         \"sunset\":\"" + sunset + "\""
                + "}}";
    }
}
