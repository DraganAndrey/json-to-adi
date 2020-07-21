package ru.stm.JsonToADIConverter.service.convert.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.schema.ADIType;
import ru.stm.JsonToADIConverter.schema.AssetType;
import ru.stm.JsonToADIConverter.service.adi.AdiService;
import ru.stm.JsonToADIConverter.service.asset.AssetService;
import ru.stm.JsonToADIConverter.service.convert.ConvertService;
import ru.stm.JsonToADIConverter.service.helper.FileService;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ConvertServiceImpl implements ConvertService {

    @Autowired
    private List<AssetService> assetServiceList;

    @Autowired
    private AdiService adiService;

    @Autowired
    private FileService fileService;

    @Override
    public List<ADIType> convert(InputJson inputJson) {

        if (Objects.isNull(inputJson.getResult()) || Objects.isNull(inputJson.getResult().getItems())) {
            log.warn("Не найдены MovieItem во входящем Json");
            return Collections.emptyList();
        }
        return inputJson.getResult().getItems().stream().map(item -> {
            List<AssetType> assetTypeList = assetServiceList
                    .stream()
                    .map(service -> service.prepareAsset(item))
                    .collect(Collectors.toList());

            ADIType adiType = adiService.initAdi(item);
            adiType.setAsset(assetTypeList.stream().filter(asset -> asset.getMetadata().getAMS().getAssetClass().equals("title")).findFirst().get());
            adiType.getAsset()
                    .getAsset()
                    .addAll(assetTypeList.stream().filter(asset -> !asset.getMetadata().getAMS().getAssetClass().equals("title")).collect(Collectors.toList()));
            return adiType;
        }).collect(Collectors.toList());
    }

}
