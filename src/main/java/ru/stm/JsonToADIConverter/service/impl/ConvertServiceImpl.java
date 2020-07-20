package ru.stm.JsonToADIConverter.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.schema.*;
import ru.stm.JsonToADIConverter.service.AssetService;
import ru.stm.JsonToADIConverter.service.ConvertService;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ConvertServiceImpl implements ConvertService {


    @Autowired
    private List<AssetService> assetServiceList;


    @Override
    public void convert(InputJson inputJson) {


        if (Objects.isNull(inputJson.getResult()) || Objects.isNull(inputJson.getResult().getItems())) {
            log.warn("Не найдены MovieItem во входящем Json");
            return;
        }
        List<ADIType> adiTypeList = inputJson.getResult().getItems().stream().map(item -> {
            List<AssetType> assetTypeList = assetServiceList
                    .stream()
                    .map(service -> service.prepareAsset(item))
                    .collect(Collectors.toList());
            ADIType adiType = new ADIType();
            adiType.setAsset(assetTypeList.stream().filter(asset -> asset.getMetadata().getAMS().getAssetClass().equals("package")).findFirst().get());
            adiType.getAsset()
                    .getAsset()
                    .addAll(assetTypeList.stream().filter(asset -> !asset.getMetadata().getAMS().getAssetClass().equals("package")).collect(Collectors.toList()));
            return adiType;
        }).collect(Collectors.toList());
        adiTypeList.forEach(adiType -> {
            try {
                javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(adiType.getClass().getPackage().getName());
                javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
                marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
                marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                OutputStream os = new FileOutputStream(String.format("%s_%s.xml", String.valueOf(System.currentTimeMillis()), adiType.getAsset().getMetadata().getAMS().getAssetName()));
                marshaller.marshal(new JAXBElement<>(new QName("ADIType", "ADI"), ADIType.class, adiType), os);

            } catch (javax.xml.bind.JAXBException ex) {
                log.error("Ошибка генерации XML {}", ex);
            } catch (FileNotFoundException e) {
                log.error("Файл не найден", e);
            }
        });

        System.out.println("");
    }

}
