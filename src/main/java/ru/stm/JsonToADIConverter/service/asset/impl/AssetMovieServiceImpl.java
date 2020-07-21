package ru.stm.JsonToADIConverter.service.asset.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stm.JsonToADIConverter.pojo.*;
import ru.stm.JsonToADIConverter.schema.AppDataType;
import ru.stm.JsonToADIConverter.schema.AssetType;
import ru.stm.JsonToADIConverter.schema.MetadataType;
import ru.stm.JsonToADIConverter.service.asset.AbstractAssetService;
import ru.stm.JsonToADIConverter.service.helper.AppDataHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AssetMovieServiceImpl extends AbstractAssetService {

    @Autowired
    private AppDataHelper appDataHelper;

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
        List<AppDataType> movieData = new ArrayList<>(addMasterObjData(movieItem.getMasterObjects()));
        FileData fileData = movieItem.getFileData();
        if (Objects.nonNull(fileData)) {
            appDataHelper.addAppData("File_Size", fileData.getFileSize(), movieData);
            appDataHelper.addAppData("Encoding_Profile", fileData.getEncodingProfile(), movieData);
            appDataHelper.addAppData("DRM", fileData.getDrm(), movieData);
        }
        appDataHelper.addAppData("Playback_Id", movieItem.getPlaybackId(), movieData);
        appDataHelper.addAppData("Est_Rights_Expirtaion_Date", movieItem.getEstRightsExpirationDate(), movieData);
        appDataHelper.addAppData("Est_Rights_Start_Date", movieItem.getEstRightsExpirationDate(), movieData);
        appDataHelper.addAppData("Fvod_Rights_Expiration_Date", movieItem.getFvodRightsExpirationDate(), movieData);
        appDataHelper.addAppData("Fvod_Rights_Start_Date", movieItem.getFvodRightsStartDate(), movieData);
        appDataHelper.addAppData("Svod_Rights_Expiration_Date", movieItem.getSvodRightsExpirationDate(), movieData);
        appDataHelper.addAppData("Svod_Rights_Start_Date", movieItem.getSvodRightsStartDate(), movieData);
        appDataHelper.addAppData("Tvod_Rights_Expiration_Date", movieItem.getTvodRightsExpirationDate(), movieData);
        appDataHelper.addAppData("Tvod_Rights_Start_Date", movieItem.getTvodRightsStartDate(), movieData);
        appDataHelper.addAppData("Tvod_Window", movieItem.getTvodWindow(), movieData);
        appDataHelper.addAppData("Est_Storage_To", movieItem.getEstStorageTo(), movieData);
        Price price = movieItem.getPrice();
        if (Objects.nonNull(price)) {
            appDataHelper.addAppData("Price_Payment_Method", price.getPaymentMethod(), movieData);
            appDataHelper.addAppData("Price_Value", price.getValue(), movieData);
            appDataHelper.addAppData("Price_Currency", price.getCurrency(), movieData);
        }
        List<AreasPrice> areasPrices = movieItem.getAreasPrices();
        if (Objects.nonNull(areasPrices) && !areasPrices.isEmpty()) {
            areasPrices.forEach(areaPrice -> {
                appDataHelper.addAppData("Area_Id", areaPrice.getAreaId(), movieData);
                appDataHelper.addAppData("Area_Price", areaPrice.getPrice(), movieData);
            });
        }
        List<SegmentPrice> segmentPrices = movieItem.getSegmentPrices();
        if (Objects.nonNull(segmentPrices) && !segmentPrices.isEmpty()) {
            segmentPrices.forEach(segmentPrice -> {
                appDataHelper.addAppData("Segment", segmentPrice.getSegment(), movieData);
                appDataHelper.addAppData("Segment_Price", segmentPrice.getPrice(), movieData);
            });
        }
        List<String> avaibleCategory = movieItem.getAvaibleCategory();
        if (Objects.nonNull(avaibleCategory) && !avaibleCategory.isEmpty()) {
            avaibleCategory.forEach(category -> appDataHelper.addAppData("Avaible_Category", category, movieData));
        }
        return movieData;
    }

    private List<AppDataType> addMasterObjData(List<MasterObject> masterObjs) {
        List<AppDataType> movieData = new ArrayList<>();
        if (Objects.isNull(masterObjs) || masterObjs.isEmpty()) return movieData;
        masterObjs.forEach(masterObject -> {
            appDataHelper.addAppData("Resolution", masterObject.getContentResolution(), movieData);
            appDataHelper.addAppData("Audio_Type", masterObject.getAudioType(), movieData);
            appDataHelper.addAppData("Is_Hdr", masterObject.getIsHdr(), movieData);
            appDataHelper.addAppData("Frame_Rate", masterObject.getFps(), movieData);
            appDataHelper.addAppData("Run_time", masterObject.getDuration(), movieData);
            appDataHelper.addAppData("Display_Run_Time", masterObject.getDurationText(), movieData);
            List<String> subtitles = masterObject.getSubtitles();
            if (!subtitles.isEmpty()) {
                subtitles.forEach(subtitleUrl -> {
                    appDataHelper.addAppData("Subtitle_Language", subtitleUrl, movieData);
                });
            }
            List<SkippingFragment> skippingFragments = masterObject.getSkippingFragments();
            if (!skippingFragments.isEmpty()) {
                skippingFragments.forEach(skippingFragment -> appDataHelper.addAppData("Skipping_Fragments",
                        skippingFragment.getName(), movieData));
            }
        });
        return movieData;
    }

    @Override
    protected String getServiceName() {
        return "movie";
    }
}
