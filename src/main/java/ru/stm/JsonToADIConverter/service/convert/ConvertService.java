package ru.stm.JsonToADIConverter.service.convert;

import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.schema.ADIType;

import java.util.List;

/**
 * Интерфейс предоставляющий возможность конвертировать Json в Xml представления согласно ADI формата
 */
public interface ConvertService {
    //Метод конвертирует входящий json в список Adi объектов
    List<ADIType> convert(InputJson inputJson);
}
