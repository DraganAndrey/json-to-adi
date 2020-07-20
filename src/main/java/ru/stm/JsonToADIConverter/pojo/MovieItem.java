package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieItem {
    @JsonProperty(value = "content_type")
    private String contentType;
    @JsonProperty(value = "content_id")
    Long contentId;
    @JsonProperty(value = "primary_genres")
    private List<String> primaryGenres;
    private List<String> studios;
    @JsonProperty(value = "is_russian")
    private Boolean isRussian;
    @JsonProperty(value = "publication_unixtime")
    private Long publicationUnixtime;
    @JsonProperty(value = "mpaa_rating")
    private String mpaaRating;
    @JsonProperty(value = "age_rating")
    private String ageRating;
    private String tagline;
    @JsonProperty(value = "release_date")
    private String releaseDate;
    @JsonProperty(value = "rightholder_name")
    private List<RightHolder> rightHolderName;
    @JsonProperty(value = "is_perfect")
    private Boolean isPerfect;
    @JsonProperty(value = "tvod_rights_start_date")
    private String tvodRightsStartDate;
    @JsonProperty(value = "tvod_rights_expiration_date")
    private String tvodRightsExpirationDate;
    @JsonProperty(value = "svod_rights_start_date")
    private String svodRightsStartDate;
    @JsonProperty(value = "svod_rights_expiration_date")
    private String svodRightsExpirationDate;
    @JsonProperty(value = "est_rights_start_date")
    private String estRightsStartDate;
    @JsonProperty(value = "est_rights_expiration_date")
    private String estRightsExpirationDate;
    @JsonProperty(value = "est_storage_to")
    private String estStorageTo;
    @JsonProperty(value = "fvod_rights_start_date")
    private String fvodRightsStartDate;
    @JsonProperty(value = "fvod_rights_expiration_date")
    private String fvodRightsExpirationDate;
    @JsonProperty(value = "avod_rights_start_date")
    private String avodRightsStartDate;
    @JsonProperty(value = "avod_rights_expiration_date")
    private String avodRightsExpirationDate;
    private String raiting;
    private String description;
    @JsonProperty(value = "image_objs")
    private List<ImageJson> imageObjs;
    private Integer priority;
    private PostMessage postMessage;
    @JsonProperty(value = "is_promotion")
    private Boolean isPromotion;
    @JsonProperty(value = "is_novelty")
    private Boolean isNovelty;
    @JsonProperty(value = "is_soon")
    private Boolean isSoon;
    @JsonProperty(value = "has_nfc")
    private Boolean hasNfc;
    @JsonProperty(value = "source_id")
    private Long sourceId;
    @JsonProperty(value = "cdn_type")
    private String cdnType;
    @JsonProperty(value = "in_cdn")
    private Boolean inCdn;
    @JsonProperty(value = "presale_target_date")
    private String presaleTargetDate;
    @JsonProperty(value = "presale_start_date")
    private String presaleStartDate;
    @JsonProperty(value = "title_en")
    private String titleEn;
    @JsonProperty(value = "title_ru")
    private String titleRu;
    private Long duration;
    @JsonProperty(value = "daration_text")
    private String darationText;
    @JsonProperty(value = "tvod_window")
    private Long tvodWindow;
    @JsonProperty(value = "content_category")
    private String contentCategory;
    @JsonProperty(value = "description_en")
    private String descriptionEn;
    @JsonProperty(value = "keywords_ru")
    private String keywordsRu;
    @JsonProperty(value = "keywords_en")
    private String keywordsEn;
    @JsonProperty(value = "datetime_create")
    private String datetimeCreate;
    @JsonProperty(value = "datetime_modify")
    private String datetimeModify;
    private Long votes;
    private Integer populatiry;
    @JsonProperty(value = "backdrop_available")
    private Boolean backdropAvailable;
    @JsonProperty(value = "movie_url")
    private String movieUrl;
    @JsonProperty(value = "trailer_url")
    private String trailerUrl;
    @JsonProperty("budget")
    String budget;
    @JsonProperty("support_download")
    String supportDownload;
    @JsonProperty("distribution_list")
    List<String> distributionList;
    @JsonProperty("distribution_areas")
    List<String> distributionAreas;
    @JsonProperty("sales_methods")
    List<String> salesMethods;
    @JsonProperty("sessions_limit")
    String sessions_limits;
    @JsonProperty("one_terminal_limit")
    String oneTerminalLimit;
    @JsonProperty("max_devices_for_simultaneous_play_est")
    Long maxDevisesOneTimeEst;
    @JsonProperty("max_devices_for_simultaneous_play_non_est")
    Long maxDevisesOneTimeNonEst;
    @JsonProperty("max_ott_devices_per_account")
    Long maxOttDevicesPerAccount;
    @JsonProperty("max_stb_devices_per_account")
    Long maxStbDevicesPerAccount;
    @JsonProperty(value = "packages_tvh")
    private List<String> packagesTvh;
    private List<String> downloadable;
    @JsonProperty(value = "master_objs")
    private List<MasterObject> masterObjects;
    @JsonIgnore//(value = "trailer_array")
    private List<Objects> trailerArray;
    @JsonProperty(value = "is_presale")
    private Boolean isPresale;
    @JsonProperty(value = "datetime_publication")
    private String datetimePublication;
    @JsonProperty(value = "rightholder_content_id")
    private Long rightholderContentId;
    @JsonProperty(value = "playback_id")
    private Long playbackId;
    @JsonProperty(value = "sound_tracks_description")
    private List<String> soundTracksDescription;
    @JsonProperty(value = "external_ids")
    private List<ExternalId> externalIds;
    @JsonProperty(value = "movie_drm")
    private String movieDrm;
    private Price price;
    @JsonProperty(value = "presale_price")
    private String presalePrice;
    @JsonProperty(value = "trailer_available")
    private Boolean trailerAvailable;
    @JsonProperty(value = "file_data")
    private FileData fileData;
    private String year;
    private List<Country> countries;
    private List<Genre> genres;
    private List<Actor> actors;
    private List<Director> directors;
    @JsonProperty(value = "in_subscriptions")
    private List<String> inSubscription;
    @JsonProperty(value = "areas_id_prices")
    private List<AreasPrice> areasPrices;
    @JsonProperty(value = "segment_prices")
    private List<SegmentPrice> segmentPrices;
    @JsonProperty(value = "avaible_category")
    private List<String> avaibleCategory;
}
