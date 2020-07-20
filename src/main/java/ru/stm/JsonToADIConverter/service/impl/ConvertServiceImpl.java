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

}
