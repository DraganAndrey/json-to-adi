package ru.stm.JsonToADIConverter.service.helper;

import ru.stm.JsonToADIConverter.schema.AppDataType;

import java.util.List;

/**
 * Интерфейс, который использется для облегчения работы с типом AppData
 */
public interface AppDataHelper {
    /**
     * Добавляет новый объект AppData в существующий список
     * @param name
     * @param value
     * @param appData
     */
    void addAppData(String name, Object value, List<AppDataType> appData);
}
