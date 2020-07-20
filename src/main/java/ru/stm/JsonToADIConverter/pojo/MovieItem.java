package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieItem {
    String contentType;
    String contentId;
    List<String> primaryGenres;
    List<String> studios;
    Boolean isRussian;
    Long publicationUnixtime;
    String mpaaRating;
    String ageRating;
    String tagline;
    String releaseDate;
    List<RightHolder> rightHolderName;
    Boolean isPerfect;
    @JsonProperty(value = "tvod_rights_start_date")
    String tvodRightsStartDate;
    @JsonProperty(value = "tvod_rights_expiration_date")
    String tvodRightsExpirationDate;
    @JsonProperty(value = "svod_rights_start_date")
    String svodRightsStartDate;
    @JsonProperty(value = "svod_rights_expiration_date")
    String svodRightsExpirationDate;
    @JsonProperty(value = "est_rights_start_date")
    String estRightsStartDate;
    @JsonProperty(value = "est_rights_expiration_date")
    String estRightsExpirationDate;
    @JsonProperty(value = "fvod_rights_start_date")
    String fvodRightsStartDate;
    @JsonProperty(value = "fvod_rights_expiration_date")
    String fvodRightsExpirationDate;
}
