package ru.stm.JsonToADIConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.custommonkey.xmlunit.XMLTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.schema.ADIType;
import ru.stm.JsonToADIConverter.schema.AppDataType;
import ru.stm.JsonToADIConverter.schema.AssetType;
import ru.stm.JsonToADIConverter.service.convert.ConvertService;
import ru.stm.JsonToADIConverter.service.helper.FileService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

@SpringBootTest
@Slf4j
@TestPropertySource(locations = "classpath:test.properties")
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
            InputJson inputJson = objectMapper.readValue(new File(TEST_DATA_FILE), InputJson.class);
            List<ADIType> adiTypeList = convertService.convert(inputJson);
            adiTypeList.forEach(adiType -> fileService.writeXmlFile(adiType));
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
            assertTrue(areEqual(getAppData(originAdi), getAppData(resultAdi)));
        } catch (JAXBException | XMLStreamException e) {
            log.error("Ошибка конвертации ADI xml в Java объект");
        }
        fileService.deleteAllFilesFolder(TEST_DATA_DIR);
    }

    private ADIType xmlToObject(String xmlString) throws XMLStreamException, JAXBException {
        InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
        JAXBContext jaxbContext = JAXBContext.newInstance(ADIType.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = factory.createXMLEventReader(stream);
        JAXBElement<ADIType> adiElement = unmarshaller.unmarshal(xmlEventReader, ADIType.class);
        return adiElement.getValue();
    }

    private Map<String, String> getAppData(ADIType adiType) {
        AssetType asset = adiType.getAsset();
        Map<String, String> appDataMap = new HashMap<>();
        List<AppDataType> list = asset.getMetadata().getAppData();
        list.forEach(item -> appDataMap.put(item.getName(), item.getAppDataValue()));
        asset.getAsset().forEach(assetType -> assetType.getMetadata().getAppData().forEach(item -> {
            appDataMap.put(item.getName(), item.getAppDataValue());
        }));
        return appDataMap;
    }

    private boolean areEqual(Map<String, String> first, Map<String, String> second) {
        if (first.size() != second.size()) {
            return false;
        }
        return first.entrySet().stream()
                .allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }
}
