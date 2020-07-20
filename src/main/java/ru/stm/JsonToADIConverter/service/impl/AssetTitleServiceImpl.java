package ru.stm.JsonToADIConverter.service.impl;

import org.springframework.stereotype.Service;
import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.schema.AssetType;
import ru.stm.JsonToADIConverter.service.AssetService;

@Service
public class AssetTitleServiceImpl implements AssetService {

    @Override
    public AssetType prepareAsset(MovieItem movieItem) {
        return null;
    }
}
