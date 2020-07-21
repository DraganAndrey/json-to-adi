package ru.stm.JsonToADIConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.service.convert.ConvertService;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@Slf4j
public class JsonToAdiConverterApplication {

    @Autowired
    private ConvertService convertService;

    public static void main(String[] args) {
        SpringApplication.run(JsonToAdiConverterApplication.class, args);
    }

}
