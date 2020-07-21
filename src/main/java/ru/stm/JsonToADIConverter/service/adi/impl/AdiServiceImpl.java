package ru.stm.JsonToADIConverter.service.adi.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.schema.ADIType;
import ru.stm.JsonToADIConverter.schema.AMSType;
import ru.stm.JsonToADIConverter.schema.MetadataType;
import ru.stm.JsonToADIConverter.service.adi.AdiService;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AdiServiceImpl implements AdiService {
    @Override
    public ADIType initAdi(MovieItem movieItem) {
        ADIType adiType = new ADIType();
        adiType.setMetadata(this.prepareMetadata(movieItem));
        return adiType;
    }

    private MetadataType prepareMetadata(MovieItem movieItem) {

        MetadataType metadataType = new MetadataType();
        AMSType amsType = new AMSType();
        amsType.setAssetClass("package");
        amsType.setAssetName(prepareAmsTitle(movieItem.getTitleEn()));
        amsType.setCreationDate(OffsetDateTime.now().toString());
        amsType.setAssetID(UUID.randomUUID().toString());
        metadataType.setAMS(amsType);
        return metadataType;
    }

    private String prepareAmsTitle(String movieTitle){
        List<String> titleList = new ArrayList<>(Arrays.asList(movieTitle.split(StringUtils.SPACE)));
        titleList.add("title");
        return String.join("_", titleList);
    }
}
