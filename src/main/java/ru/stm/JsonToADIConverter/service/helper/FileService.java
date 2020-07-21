package ru.stm.JsonToADIConverter.service.helper;

import ru.stm.JsonToADIConverter.schema.ADIType;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Интерфейс для работы с фаилами
 */
public interface FileService {
    /**
     * Проверяет и создает директорию при ее отсутствии
     * @param path
     * @return
     */
    String prepareOutputDirectory(String path);

    /**
     * Записывает XMl в выходную директорию
     * @param adiType
     */
    void writeXmlFile(ADIType adiType);
}
