package ru.stm.JsonToADIConverter.service.asset;

import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.schema.AssetType;

public interface AssetService {

    public AssetType prepareAsset(MovieItem movieItem);
}
