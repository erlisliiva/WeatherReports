package com.erlis.weather.service;

import com.erlis.weather.dto.api.ApiResultDto;
import com.erlis.weather.dto.output.WeatherReportDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

@Service
public class FileService implements Serializable {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${resources.path}")
    private String resourcePath;

    public List<String> readFromFile(String fileName) throws IOException {

        File file = ResourceUtils.getFile(resourcePath + fileName);
        if (file.length() == 0 || fileName.isEmpty()){
            throw new IOException("File is empty of city names!");
        }
        return Files.readAllLines(file.toPath(), Charset.defaultCharset());
    }

    public void writeToFile(List<WeatherReportDto> weatherReportDtoList, String fileName) {

        try (FileWriter writer = new FileWriter(resourcePath + fileName);
             BufferedWriter bw = new BufferedWriter(writer)) {
            String string = objectMapper.writeValueAsString(weatherReportDtoList);
            bw.write(string);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }



}
