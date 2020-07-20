package ru.stm.JsonToADIConverter.service.util.impl;

import ru.stm.JsonToADIConverter.schema.AppDataType;
import ru.stm.JsonToADIConverter.service.util.AppDataHelper;

import java.util.List;

public class AppDataHelperImpl implements AppDataHelper {

    public void addAppData(String name, Object value, List<AppDataType> appData) {
        AppDataType appDataType = new AppDataType();
        appDataType.setName(name);
        appDataType.setAppDataValue(String.valueOf(value));
        appData.add(appDataType);
    }
}
