package ru.stm.JsonToADIConverter.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.schema.ADIType;
import ru.stm.JsonToADIConverter.service.convert.ConvertService;
import ru.stm.JsonToADIConverter.service.helper.FileService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class ConvertCommand implements CommandLineRunner {

    @Autowired
    private ConvertService convertService;

    @Autowired
    private FileService fileService;

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
            List<ADIType> adiTypeList = convertService.convert(inputJson);
            adiTypeList.forEach(adiType -> fileService.writeXmlFile(adiType));
        } catch (IOException e) {
            log.error("Ошибка чтения JSON");
        }
    }
}
