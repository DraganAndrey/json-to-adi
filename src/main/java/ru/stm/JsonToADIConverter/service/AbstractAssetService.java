package ru.stm.JsonToADIConverter.service;

import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.schema.AMSType;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class AbstractAssetService  implements AssetService {

    protected AMSType prepareAms(MovieItem movieItem){
        AMSType amsType = new AMSType();
        amsType.setAssetClass(this.getServiceName());
        amsType.setAssetName(prepareAmsTitle(movieItem.getTitleEn()));
        amsType.setCreationDate(OffsetDateTime.now().toString());
        amsType.setAssetID(UUID.randomUUID().toString());
        return amsType;
    }



    protected String prepareAmsTitle(String movieTitle){
        List<String> titleList = Arrays.asList(movieTitle.split(" "));
        titleList.add(this.getServiceName());
        return String.join("_", titleList);
    }


    protected abstract String getServiceName();
}
