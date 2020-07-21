package ru.stm.JsonToADIConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.custommonkey.xmlunit.XMLTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.service.ConvertService;
import ru.stm.JsonToADIConverter.service.util.FileService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@SpringBootTest
@Slf4j
@TestPropertySource(locations="classpath:test.properties")
class JsonToAdiConverterApplicationTests extends XMLTestCase {
	private final String TEST_DATA_FILE = "src/test/resources/data/jsonVodTestData.txt";
	private final String TEST_DATA_DIR = "src/test/resources/data/output";
	private final String ORIGIN_ADI_FILE = "src/test/resources/data/adiVodOriginData/Otverzhennye.xml";

	@Autowired
	private ConvertService convertService;
	@Autowired
	private FileService fileService;
	private ObjectMapper objectMapper = new ObjectMapper();


	@Test
	void contextLoads() {
	}


	@Test
	void convertJsonToAdiTest() throws IOException, SAXException {
		try {
			convertService.convert(objectMapper.readValue(new File(TEST_DATA_FILE), InputJson.class));
		} catch (IOException e) {
			log.error("Ошибка чтения JSON файла");
		}
		List<String> fileList = fileService.readFiles(TEST_DATA_DIR, Charset.defaultCharset());
		assertEquals(1, fileList.size());
		fileService.deleteAllFilesFolder(TEST_DATA_DIR);

	}

}
