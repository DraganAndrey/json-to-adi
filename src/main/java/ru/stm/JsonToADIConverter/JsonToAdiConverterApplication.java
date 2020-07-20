package ru.stm.JsonToADIConverter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class JsonToAdiConverterApplication implements CommandLineRunner {

	@Autowired
	ConvertService convertService;

	public static void main(String[] args) {
		SpringApplication.run(JsonToAdiConverterApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		File file = new File("/home/lezhachev/Загрузки/movies.txt");
		ObjectMapper objectMapper = new ObjectMapper();
		StringBuilder builder = new StringBuilder();
		try {
			InputJson json1 = objectMapper.readValue(file, InputJson.class);
			convertService.convert(json1);


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
