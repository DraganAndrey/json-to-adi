package ru.stm.JsonToADIConverter.service.impl;

import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.pojo.MasterObject;
import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.pojo.SkipingFragment;
import ru.stm.JsonToADIConverter.schema.*;
import ru.stm.JsonToADIConverter.service.ConvertService;

import java.util.ArrayList;
import java.util.List;


public class ConvertServiceImpl implements ConvertService {

    @Override
    public void convert(InputJson inputJson) {
        //todo
        ADIType adiType = new ADIType();
        AssetType assetType = new AssetType();
        MetadataType metadataType = new MetadataType();


    }

    private void getMovieAsset(MovieItem movieItem) {
        AssetType assetType = new AssetType();
        MetadataType metadataType = new MetadataType();
        AMSType amsType = new AMSType();
        amsType.setAssetClass(movieItem.getContentType());
        amsType.setAssetID(String.valueOf(movieItem.getContentId()));
        List<AppDataType> movieData = new ArrayList<>();
        addAppData("Playback_Id", movieItem.getPlaybackId(), movieData);


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
                    addAppData("Skiping_Fragments", skipingFragment.getName(), movieData);
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
