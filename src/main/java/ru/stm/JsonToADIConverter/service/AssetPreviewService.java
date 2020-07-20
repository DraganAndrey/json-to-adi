package ru.stm.JsonToADIConverter.service;

import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.schema.AssetType;

public interface AssetPreviewService {
    AssetType getAssetPreview(InputJson inputJson);
}
