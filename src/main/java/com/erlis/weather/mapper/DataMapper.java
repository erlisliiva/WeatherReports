package com.erlis.weather.mapper;

import com.erlis.weather.dto.api.ApiForecastDto;
import com.erlis.weather.dto.api.ApiResultDto;
import com.erlis.weather.dto.output.ForecastDto;
import com.erlis.weather.dto.output.WeatherDto;
import com.erlis.weather.dto.output.WeatherReportDetailsDto;
import com.erlis.weather.dto.output.WeatherReportDto;
import org.springframework.stereotype.Service;

@Service
public class DataMapper {


    /** Manual Mapping for ApiDto to OutPutDto
     * @param weather APiDto
     * @return new OutPutDto
     */
    public WeatherReportDto map(ApiResultDto weather) {
        WeatherReportDto weatherReportDto = new WeatherReportDto();

        WeatherReportDetailsDto detailsDto = new WeatherReportDetailsDto();
        WeatherDto weatherDtoCurrent = new WeatherDto();

        weatherDtoCurrent.setHumidity(weather.getList().get(0).main.getHumidity());
        weatherDtoCurrent.setPressure(weather.getList().get(0).main.getPressure());
        weatherDtoCurrent.setTemperature(weather.getList().get(0).main.getTemp());

        weatherReportDto.setCurrentWeatherReport(weatherDtoCurrent);
        weatherReportDto.setWeatherReportDetails(detailsDto);

        weatherReportDto.weatherReportDetails.setCity(weather.city.name);
        weatherReportDto.weatherReportDetails.setCoordinates(weather.city.coord.lat.toString() + ", " + weather.city.coord.lat.toString());
        weatherReportDto.weatherReportDetails.setTemperatureUnit("Celsius");

        for (ApiForecastDto fc : weather.getList()) {

            WeatherDto weatherDto = new WeatherDto();
            ForecastDto forecastDto = new ForecastDto();
            weatherDto.setHumidity(fc.main.getHumidity());
            weatherDto.setPressure(fc.main.getPressure());
            weatherDto.setTemperature(fc.main.getTemp());
            forecastDto.setDate(fc.dt_txt);
            forecastDto.setWeather(weatherDto);
            weatherReportDto.getForecastReport().add(forecastDto);

        }
        return weatherReportDto;
    }
}
