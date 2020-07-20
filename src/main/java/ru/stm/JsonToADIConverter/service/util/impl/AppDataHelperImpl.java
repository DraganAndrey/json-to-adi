package ru.stm.JsonToADIConverter.service.util.impl;

import org.springframework.stereotype.Service;
import ru.stm.JsonToADIConverter.schema.AppDataType;
import ru.stm.JsonToADIConverter.service.util.AppDataHelper;

import java.util.List;
import java.util.Objects;

@Service
public class AppDataHelperImpl implements AppDataHelper {

    public void addAppData(String name, Object value, List<AppDataType> appData) {
        if(Objects.isNull(value)){
            return;
        }
        AppDataType appDataType = new AppDataType();
        appDataType.setName(name);
        appDataType.setAppDataValue(String.valueOf(value));
        appData.add(appDataType);
    }

    public void addAppData(String name, List<Object> values, List<AppDataType> appDataTypes){
        if(Objects.isNull(values)){
            return;
        }
        values.forEach(value -> this.addAppData(name,value,appDataTypes));
    }
}
