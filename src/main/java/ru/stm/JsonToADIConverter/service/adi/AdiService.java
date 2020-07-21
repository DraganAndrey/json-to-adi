package ru.stm.JsonToADIConverter.service.adi;

import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.schema.ADIType;

public interface AdiService {

    public ADIType initAdi(MovieItem movieItem);
}
