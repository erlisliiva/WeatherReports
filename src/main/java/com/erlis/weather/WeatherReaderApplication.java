package com.erlis.weather;

import com.erlis.weather.service.WeatherApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherReaderApplication.class, args);


	}

}
