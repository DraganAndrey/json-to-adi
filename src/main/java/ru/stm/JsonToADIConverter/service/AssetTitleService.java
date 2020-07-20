package ru.stm.JsonToADIConverter.service;

import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.schema.AssetType;

public interface AssetTitleService {

    AssetType getTitleAsset(InputJson inputJson);
}
