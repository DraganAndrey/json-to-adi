package ru.stm.JsonToADIConverter.service.asset;

import org.apache.commons.lang3.StringUtils;
import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.schema.AMSType;
import ru.stm.JsonToADIConverter.service.asset.AssetService;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Класс который является общим предком для всех Asset-сервисов
 */
public abstract class AbstractAssetService  implements AssetService {

    /**
     * Генерация AMS тега для большинства сервисов сейчас одинакова
     * @param movieItem
     * @return AMSType
     */
    protected AMSType prepareAms(MovieItem movieItem){
        AMSType amsType = new AMSType();
        amsType.setAssetClass(getServiceName());
        amsType.setAssetName(prepareAmsTitle(movieItem.getTitleEn()));
        amsType.setCreationDate(OffsetDateTime.now().toString());
        amsType.setAssetID(UUID.randomUUID().toString());
        return amsType;
    }

    /**
     * Создает title для AMS тега
     * @param movieTitle
     * @return String
     */
    protected String prepareAmsTitle(String movieTitle){
        List<String> titleList = new ArrayList<>(Arrays.asList(movieTitle.split(StringUtils.SPACE)));
        titleList.add("title");
        return String.join("_", titleList);
    }

    /**
     * Метод, который возвращает тип ассета,
     * который может подготовить данный сервис
     * @return String
     */
    protected abstract String getServiceName();
}
