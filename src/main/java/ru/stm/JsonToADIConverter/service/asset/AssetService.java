package ru.stm.JsonToADIConverter.service.asset;

import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.schema.AssetType;

/**
 * Интерфейс, который предоставляет
 * возможность генерировать Asset объекты
 * Под каждый тип Asset-ов используется отдельный класс
 */
public interface AssetService {

    /**
     * Подготавливает Asset объект
     * @param movieItem
     * @return
     */
    public AssetType prepareAsset(MovieItem movieItem);
}
