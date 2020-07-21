package ru.stm.JsonToADIConverter.service.adi;

import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.schema.ADIType;

/**
 * Сервис для создания Adi объектов
 */
public interface AdiService {
    /**
     * Создает Adi объект из Pojo представления
     * @param movieItem
     * @return
     */
    public ADIType initAdi(MovieItem movieItem);
}
