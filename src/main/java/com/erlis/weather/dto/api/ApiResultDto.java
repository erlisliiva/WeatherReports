package com.erlis.weather.dto.api;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ApiResultDto implements Serializable {


    public String cod;
    public Integer message;
    public Integer cnt;
    public List<ApiForecastDto> list;
    public CityDto city;

    @Override
    public String toString() {
        return "{" + "\n"
                + "\"cod\":\"" + cod + "\"" + "\n"
                + ",\"message\":\"" + message + "\"" + "\n"
                + ",\"cnt\":\"" + cnt + "\"" + "\n"
                + ",\"list\":" + list + "\n"
                + ",\"city\":" + city + "\n"
                + "}";
    }




}
