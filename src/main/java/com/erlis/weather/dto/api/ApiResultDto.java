package com.erlis.weather.dto.api;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data
public class ApiResultDto implements Serializable {


    public String cod;
    public Integer message;
    public Integer cnt;
    public List<ApiForecastDto> list;
    public CityDto city;

    public List<ApiForecastDto> getList() {
        if (list == null){
            list = new LinkedList<>();
        }
        return list;
    }

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
