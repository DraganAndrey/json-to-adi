package ru.stm.JsonToADIConverter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stm.JsonToADIConverter.pojo.MasterObject;
import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.schema.AppDataType;
import ru.stm.JsonToADIConverter.schema.AssetType;
import ru.stm.JsonToADIConverter.schema.MetadataType;
import ru.stm.JsonToADIConverter.service.AbstractAssetService;
import ru.stm.JsonToADIConverter.service.util.AppDataHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AssetPreviewServiceImpl extends AbstractAssetService {

    @Autowired
    private AppDataHelper appDataHelper;

    @Override
    public AssetType prepareAsset(MovieItem movieItem) {
        AssetType assetType = new AssetType();

        MetadataType titleMetaData = new MetadataType();
        titleMetaData.setAMS(this.prepareAms(movieItem));

        List<AppDataType> appDataTypeList = this.prepareAppDataType(movieItem);
        titleMetaData.getAppData().addAll(appDataTypeList);

        assetType.setMetadata(titleMetaData);
        return assetType;
    }

    private List<AppDataType> prepareAppDataType(MovieItem movieItem) {
        List<AppDataType> appData = new ArrayList<>();
        appDataHelper.addAppData("Rating", movieItem.getRaiting(), appData);
        appDataHelper.addAppData("Votes", movieItem.getVotes(), appData);
        appDataHelper.addAppData("Popularity", movieItem.getPopulatiry(), appData);
        List<MasterObject> masterObjectList = movieItem.getMasterObjects();
        if(Objects.nonNull(masterObjectList) && !masterObjectList.isEmpty()) {
            masterObjectList.forEach(masterObject -> {
                appDataHelper.addAppData("Is_Preview", masterObject.getIsPreview(), appData);
                appDataHelper.addAppData("Preview_Duration", masterObject.getPreviewDuration(), appData );
                appDataHelper.addAppData("Run_Time", masterObject.getPreviewStartTime(), appData );
            });
        }

        appDataHelper.addAppData("Is_Presale", movieItem.getIsPresale(), appData);
        appDataHelper.addAppData("Presale_Start_Date", movieItem.getPresaleStartDate(), appData);
        appDataHelper.addAppData("Presale_Target_Date", movieItem.getPresaleTargetDate(), appData);
        appDataHelper.addAppData("Is_Soon", movieItem.getIsSoon(), appData);
        appDataHelper.addAppData("Presale_Price", movieItem.getPresalePrice(), appData);
        return appData;
    }

    @Override
    protected String getServiceName() {
        return "preview";
    }
}
