package ru.stm.JsonToADIConverter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.service.ConvertService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@SpringBootApplication
@Slf4j
public class JsonToAdiConverterApplication implements CommandLineRunner {

    @Autowired
    private ConvertService convertService;

    public static void main(String[] args) {
        SpringApplication.run(JsonToAdiConverterApplication.class, args);
    }


    @Override
    public void run(String... args) {
        if (args.length == 0) {
            log.info("Не указан входной JSON файл");
            return;
        }
        File file = new File(args[0]);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputJson inputJson = objectMapper.readValue(file, InputJson.class);
            convertService.convert(inputJson);
        } catch (IOException e) {
            log.error("Ошибка чтения JSON");
        }
    }
}
