package ru.stm.JsonToADIConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.custommonkey.xmlunit.XMLTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.schema.ADIType;
import ru.stm.JsonToADIConverter.service.ConvertService;
import ru.stm.JsonToADIConverter.service.util.FileService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
@Slf4j
@TestPropertySource(locations="classpath:test.properties")
class JsonToAdiConverterApplicationTests extends XMLTestCase {
	private final String TEST_DATA_FILE = "src/test/resources/data/jsonVodTestData.txt";
	private final String TEST_DATA_DIR = "src/test/resources/data/output";
	private final String ORIGIN_ADI_DIR = "src/test/resources/data/adiVodOriginData";

	@Autowired
	private ConvertService convertService;
	@Autowired
	private FileService fileService;
	private ObjectMapper objectMapper = new ObjectMapper();


	@Test
	void contextLoads() {
	}


	@Test
	void convertJsonToAdiTest() throws JAXBException {
		try {
			convertService.convert(objectMapper.readValue(new File(TEST_DATA_FILE), InputJson.class));
		} catch (IOException e) {
			log.error("Ошибка чтения JSON файла");
		}
		List<String> resultFileList = fileService.readFiles(TEST_DATA_DIR, Charset.defaultCharset());
		List<String> originFileList = fileService.readFiles(ORIGIN_ADI_DIR, Charset.defaultCharset());
		assertEquals(1, resultFileList.size());
        assertEquals(1, originFileList.size());
        try {
            ADIType resultAdi = xmlToObject(resultFileList.get(0));
            ADIType originAdi = xmlToObject(originFileList.get(0));
            //todo compare
        } catch (JAXBException | XMLStreamException e) {
            log.error("Ошибка конвертации ADI xml в Java объект");
        }
        fileService.deleteAllFilesFolder(TEST_DATA_DIR);
	}

    private ADIType xmlToObject(String xmlString) throws XMLStreamException, JAXBException{
        InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
        JAXBContext jaxbContext = JAXBContext.newInstance(ADIType.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = factory.createXMLEventReader(stream);
        JAXBElement<ADIType> adiElement = unmarshaller.unmarshal(xmlEventReader, ADIType.class);
        return adiElement.getValue();
    }
}
