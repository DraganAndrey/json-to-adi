package ru.stm.JsonToADIConverter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stm.JsonToADIConverter.pojo.*;
import ru.stm.JsonToADIConverter.schema.AMSType;
import ru.stm.JsonToADIConverter.schema.AppDataType;
import ru.stm.JsonToADIConverter.schema.AssetType;
import ru.stm.JsonToADIConverter.schema.MetadataType;
import ru.stm.JsonToADIConverter.service.AbstractAssetService;
import ru.stm.JsonToADIConverter.service.AssetService;
import ru.stm.JsonToADIConverter.service.util.AppDataHelper;

import java.time.OffsetDateTime;
import java.util.*;

@Service
public class AssetTitleServiceImpl extends AbstractAssetService {

    @Autowired
    private AppDataHelper helper;

    @Override
    public AssetType prepareAsset(MovieItem movieItem) {

        AssetType assetType = new AssetType();

        MetadataType titleMetaData = new MetadataType();
        titleMetaData.setAMS(this.prepareAms(movieItem));

        List<AppDataType> appDataTypeList = this.prepareAppDataType(movieItem);
        titleMetaData.getAppData().addAll(appDataTypeList);

        assetType.setMetadata(titleMetaData);
        return assetType;
    }

    private List<AppDataType> prepareAppDataType(MovieItem movieItem) {

        List<AppDataType> appDataTypes = new ArrayList<>();

        List<RightHolder> rightHolders = movieItem.getRightHolderName();
        if (Objects.nonNull(rightHolders) && !rightHolders.isEmpty()) {
            rightHolders.forEach(rightHolder -> helper.addAppData("Rightholder_Name", rightHolder.getText(), appDataTypes));
        }

        List<Genre> genres = movieItem.getGenres();
        if (Objects.nonNull(genres) && !genres.isEmpty()) {
            genres.forEach(genre -> helper.addAppData("Genre", genre.getTitle(), appDataTypes));
        }
        List<Country> countries = movieItem.getCountries();
        if (Objects.nonNull(countries) && !countries.isEmpty()) {
            countries.forEach(country -> helper.addAppData("Country_Of_Origin", country.getName(),appDataTypes));
        }

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

    @Override
    protected String getServiceName() {
        return "title";
    }
}
