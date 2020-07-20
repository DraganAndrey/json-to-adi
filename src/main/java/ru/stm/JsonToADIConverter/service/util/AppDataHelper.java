package ru.stm.JsonToADIConverter.service.util;

import ru.stm.JsonToADIConverter.schema.AppDataType;

import java.util.List;

public interface AppDataHelper {

    void addAppData(String name, Object value, List<AppDataType> appData);
    void addAppData(String name, List<Object> values, List<AppDataType> appDataTypes);
}
