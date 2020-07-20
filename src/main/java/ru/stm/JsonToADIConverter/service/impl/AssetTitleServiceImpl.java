package ru.stm.JsonToADIConverter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stm.JsonToADIConverter.pojo.InputJson;
import ru.stm.JsonToADIConverter.pojo.MovieItem;
import ru.stm.JsonToADIConverter.schema.AMSType;
import ru.stm.JsonToADIConverter.schema.AppDataType;
import ru.stm.JsonToADIConverter.schema.AssetType;
import ru.stm.JsonToADIConverter.schema.MetadataType;
import ru.stm.JsonToADIConverter.service.AssetService;
import ru.stm.JsonToADIConverter.service.util.AppDataHelper;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AssetTitleServiceImpl implements AssetService {

    @Autowired
    private AppDataHelper helper;

    @Override
    public AssetType prepareAsset(MovieItem movieItem) {

        AssetType assetType = new AssetType();

        MetadataType titleMetaData = new MetadataType();
        titleMetaData.setAMS(this.prepareAms(movieItem));
        List<AppDataType> appDataTypeList = this.prepareAppDataType(movieItem);
        return null;
    }

    private List<AppDataType> prepareAppDataType(MovieItem movieItem) {

        List<AppDataType> appDataTypes = new ArrayList<>();

        helper.addAppData("Rightholder_Name",movieItem.getRightHolderName(),appDataTypes);
        helper.addAppData("Genre", movieItem.getGenres(),appDataTypes);
        helper.addAppData("Country_Of_Origin", movieItem.getCountries(),appDataTypes);

        helper.addAppData("Title",movieItem.getTitleEn(),appDataTypes);
        helper.addAppData("Title_Ru",movieItem.getTitleRu(),appDataTypes);
        helper.addAppData("Summary_Long",movieItem.getDescriptionEn(),appDataTypes);
        helper.addAppData("Summary_Long_Ru",movieItem.getDescriptionEn(),appDataTypes);

        helper.addAppData("Summary_Short",movieItem.getTagline(),appDataTypes);
        helper.addAppData("Keyword",movieItem.getKeywordsEn(),appDataTypes);
        helper.addAppData("Keyword_Ru",movieItem.getKeywordsRu(),appDataTypes);
        helper.addAppData("Year",movieItem.getYear(),appDataTypes);

        helper.addAppData("Is_Russian",movieItem.getIsRussian(),appDataTypes);
        helper.addAppData("Release_Date",movieItem.getReleaseDate(),appDataTypes);
        helper.addAppData("Publication_Date",movieItem.getDatetimePublication(),appDataTypes);


        helper.addAppData("Backdrop_Available",movieItem.getBackdropAvailable(),appDataTypes);
        helper.addAppData("Is_Promotion",movieItem.getIsPromotion(),appDataTypes);
        helper.addAppData("Display_As_New",movieItem.getIsNovelty(),appDataTypes);
        helper.addAppData("Budget",movieItem.getBudget(),appDataTypes);

        helper.addAppData("Age_Rating",movieItem.getAgeRating(),appDataTypes);
        helper.addAppData("Support_dowload",movieItem.getSupportDownload(),appDataTypes);

        helper.addAppData("Distribution",movieItem.getDistributionList(),appDataTypes);
        helper.addAppData("Distribution_Area",movieItem.getDistributionAreas(),appDataTypes);
        helper.addAppData("Sales_Method",movieItem.getSalesMethods(),appDataTypes);

        helper.addAppData("Session_Limits",movieItem.getSessions_limits(),appDataTypes);
        helper.addAppData("One_Terminal_Limit", movieItem.getOneTerminalLimit(),appDataTypes);
        helper.addAppData("Max_Devices_For_Simultaneous_Play_Est",movieItem.getMaxDevisesOneTimeEst(),appDataTypes);
        helper.addAppData("Max_Devices_For_Simultaneous_Play_Non_Est", movieItem.getMaxDevisesOneTimeNonEst(),appDataTypes);
        helper.addAppData("Max_Ott_Devices_Per_Account",movieItem.getMaxOttDevicesPerAccount(),appDataTypes);
        helper.addAppData("Max_Stb_Devices_Per_Account",movieItem.getMaxStbDevicesPerAccount(),appDataTypes);


        return appDataTypes;
    }

    private AMSType prepareAms(MovieItem movieItem){
        AMSType amsType = new AMSType();
        amsType.setAssetClass("title");
        amsType.setAssetName(prepareAmsTitle(movieItem.getTitleEn()));
        amsType.setCreationDate(OffsetDateTime.now().toString());
        amsType.setAssetID(UUID.randomUUID().toString());
        return amsType;
    }



    private String prepareAmsTitle(String movieTitle){
        List<String> titleList = Arrays.asList(movieTitle.split(" "));
        titleList.add("title");
        return String.join("_", titleList);
    }
}
