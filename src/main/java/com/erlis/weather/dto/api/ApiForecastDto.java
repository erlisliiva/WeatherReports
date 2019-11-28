package com.erlis.weather.dto.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiForecastDto implements Serializable {
    public TemperatureDto main;
    public String dt_txt;

    @Override
    public String toString() {
        return "{\"ApiForecastDto\":{" + "\n"
                + "\"main\":" + main+ "\n"
                + ",\"dt_txt\":\"" + dt_txt + "\""+ "\n"
                + "}" + "\n"
                + "}";
    }
}
