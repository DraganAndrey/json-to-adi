package ru.stm.JsonToADIConverter.service;

import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.pojo.MovieItem;

public interface ConvertService {

    void convert(InputJson inputJson);
}
