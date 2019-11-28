package com.erlis.weather.service;

import com.erlis.weather.dto.api.ApiResultDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<String> readFromFile(String fileName) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + fileName);
        return Files.readAllLines(file.toPath(), Charset.defaultCharset());
    }

    public void writeToFile(List<ApiResultDto> weatherReportDtoList, String fileName) {

        try(FileOutputStream fos = new FileOutputStream("C:\\Users\\Erlis\\IdeaProjects\\weatherApp\\weather\\src\\main\\resources\\"+fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fos)) {
            StringBuffer stringBuffer = new StringBuffer();
            for (ApiResultDto weather : weatherReportDtoList) {
                String s = objectMapper.writeValueAsString(weather);
                stringBuffer.append(s);
            }
            objectOut.writeUTF(String.valueOf(stringBuffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
