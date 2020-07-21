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
            assertTrue(areEqualIgnoringOrder(getAppData(originAdi), getAppData(resultAdi)));
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

    private List<AppDataType> getAppData(ADIType adiType) {
        AssetType asset = adiType.getAsset();
        List<AppDataType> list = asset.getMetadata().getAppData();
        asset.getAsset().forEach(assetType -> list.addAll(assetType.getMetadata().getAppData()));
        return list;
    }

    private static boolean areEqualIgnoringOrder(List<AppDataType> list1, List<AppDataType> list2) {
        Comparator comparator = (Comparator<AppDataType>) (o1, o2) -> {
            if (!o1.getName().equals(o2.getName())) return 1;
            if (!o1.getAppDataValue().equals(o2.getAppDataValue())) return 1;
            return 0;
        };

        if (list1.size() != list2.size()) {
            return false;
        }

        List<AppDataType> copy1 = new ArrayList<>(list1);
        List<AppDataType> copy2 = new ArrayList<>(list2);

        Collections.sort(copy1, comparator);
        Collections.sort(copy2, comparator);

        Iterator<AppDataType> it1 = copy1.iterator();
        Iterator<AppDataType> it2 = copy2.iterator();
        while (it1.hasNext()) {
            AppDataType t1 = it1.next();
            AppDataType t2 = it2.next();
            if (comparator.compare(t1, t2) != 0) {
                return false;
            }
        }
        return true;
    }
}
