package ru.stm.JsonToADIConverter.service.impl;

import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.schema.AMSType;
import ru.stm.JsonToADIConverter.schema.AppDataType;
import ru.stm.JsonToADIConverter.schema.AssetType;
import ru.stm.JsonToADIConverter.schema.MetadataType;
import ru.stm.JsonToADIConverter.service.AbstractAssetService;
import ru.stm.JsonToADIConverter.service.AssetService;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AssetPackageServiceImpl extends AbstractAssetService {
    @Override
    public AssetType prepareAsset(MovieItem movieItem) {
        AssetType assetType = new AssetType();

        MetadataType titleMetaData = new MetadataType();
        titleMetaData.setAMS(this.prepareAms(movieItem));
        assetType.setMetadata(titleMetaData);
        return assetType;
    }


    @Override
    protected String getServiceName() {
        return "package";
    }
}
