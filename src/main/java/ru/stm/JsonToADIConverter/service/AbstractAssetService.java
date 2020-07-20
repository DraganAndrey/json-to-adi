package ru.stm.JsonToADIConverter.service;

import org.apache.commons.lang3.StringUtils;
import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.schema.AMSType;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class AbstractAssetService  implements AssetService {

    protected AMSType prepareAms(MovieItem movieItem){
        AMSType amsType = new AMSType();
        amsType.setAssetClass(getServiceName());
        amsType.setAssetName(prepareAmsTitle(movieItem.getTitleEn()));
        amsType.setCreationDate(OffsetDateTime.now().toString());
        amsType.setAssetID(UUID.randomUUID().toString());
        return amsType;
    }



    protected String prepareAmsTitle(String movieTitle){
        List<String> titleList = new ArrayList<>(Arrays.asList(movieTitle.split(StringUtils.SPACE)));
        titleList.add("title");
        return String.join("_", titleList);
    }


    protected abstract String getServiceName();
}
