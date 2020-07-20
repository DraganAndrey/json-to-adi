package ru.stm.JsonToADIConverter.service.impl;

import org.springframework.stereotype.Service;
import ru.stm.JsonToADIConverter.pojo.MasterObject;
import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.pojo.SkipingFragment;
import ru.stm.JsonToADIConverter.schema.AMSType;
import ru.stm.JsonToADIConverter.schema.AppDataType;
import ru.stm.JsonToADIConverter.schema.AssetType;
import ru.stm.JsonToADIConverter.schema.MetadataType;
import ru.stm.JsonToADIConverter.service.AssetService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssetMovieServiceImpl implements AssetService {


    public AssetType prepareAsset(MovieItem movieItem) {
        AssetType assetType = new AssetType();
        MetadataType metadataType = new MetadataType();
        AMSType amsType = new AMSType();
        amsType.setAssetClass(movieItem.getContentType());
        amsType.setAssetID(String.valueOf(movieItem.getContentId()));
        List<AppDataType> movieData = new ArrayList<>();
        addAppData("Playback_Id", movieItem.getPlaybackId(), movieData);
        return assetType;
    }

    private List<AppDataType> addMasterObjData(List<MasterObject> masterObjs) {
        List<AppDataType> movieData = new ArrayList<>();
        if (masterObjs.isEmpty()) return movieData;
        masterObjs.forEach(masterObject -> {
            addAppData("Resolution", masterObject.getContentResolution(), movieData);
            addAppData("Audio_Type", masterObject.getAudioType(), movieData);
            addAppData("Is_Hdr", masterObject.getIsHdr(), movieData);
            addAppData("Frame_Rate", masterObject.getFps(), movieData);
            addAppData("Audio_Type", masterObject.getDuration(), movieData);
            addAppData("Is_Hdr", masterObject.getIsHdr(), movieData);
            addAppData("Run_time", masterObject.getDuration(), movieData);
            addAppData("Display_Run_Time", masterObject.getDurationText(), movieData);
            List<String> subtitles =  masterObject.getSubtitles();
            if (!subtitles.isEmpty()) {
                subtitles.forEach(subtitleUrl->{
                    addAppData("Subtitle_Language", subtitleUrl , movieData);
                });
            }
            List<SkipingFragment> skipingFragments = masterObject.getSkippingFragmenst();
            if(!skipingFragments.isEmpty()){
                skipingFragments.forEach(skipingFragment -> {
                    addAppData("Skipping_Fragments", skipingFragment.getName(), movieData);
                });
            }
        });
        return movieData;
    }

    private void addAppData(String name, Object value, List<AppDataType> appData) {
        AppDataType appDataType = new AppDataType();
        appDataType.setName(name);
        appDataType.setAppDataValue(String.valueOf(value));
        appData.add(appDataType);
    }


}
