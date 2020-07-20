package ru.stm.JsonToADIConverter.service;

import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.schema.AssetType;

public interface AssetService {

    AssetType prepareAsset(MovieItem movieItem);
}
