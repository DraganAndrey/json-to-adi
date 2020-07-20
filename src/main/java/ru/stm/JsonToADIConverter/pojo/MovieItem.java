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
    String contentType;
    @JsonProperty(value = "content_id")
    Long contentId;
    @JsonProperty(value = "primary_genres")
    List<String> primaryGenres;
    List<String> studios;
    @JsonProperty(value = "is_russian")
    Boolean isRussian;
    @JsonProperty(value = "publication_unixtime")
    Long publicationUnixtime;
    @JsonProperty(value = "mpaa_rating")
    String mpaaRating;
    @JsonProperty(value = "age_rating")
    String ageRating;
    String tagline;
    @JsonProperty(value = "release_date")
    String releaseDate;
    @JsonProperty(value = "rightholder_name")
    List<RightHolder> rightHolderName;
    @JsonProperty(value = "is_perfect")
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
    String raiting;
    String description;
    @JsonProperty(value = "image_objs")
    List<ImageJson> imageObjs;
    Integer priority;
    PostMessage postMessage;
    @JsonProperty(value = "is_promotion")
    Boolean isPromotion;
    @JsonProperty(value = "is_novelty")
    Boolean isNovelty;
    @JsonProperty(value = "is_soon")
    Boolean isSoon;
    @JsonProperty(value = "has_nfc")
    Boolean hasNfc;
    @JsonProperty(value = "source_id")
    Long sourceId;
    @JsonProperty(value = "cdn_type")
    String cdnType;
    @JsonProperty(value = "in_cdn")
    Boolean inCdn;
    @JsonProperty(value = "presale_target_date")
    String presaleTargetDate;
    @JsonProperty(value = "presale_start_date")
    String presaleStartDate;
    @JsonProperty(value = "title_en")
    String titleEn;
    @JsonProperty(value = "title_ru")
    String titleRu;
    Long duration;
    @JsonProperty(value = "daration_text")
    String darationText;
    @JsonProperty(value = "tvod_window")
    Long tvodWindow;
    @JsonProperty(value = "content_category")
    String contentCategory;
    @JsonProperty(value = "description_en")
    String descriptionEn;
    @JsonProperty(value = "keywords_ru")
    String keywordsRu;
    @JsonProperty(value = "keywords_en")
    String keywordsEn;
    @JsonProperty(value = "datetime_create")
    String datetimeCreate;
    @JsonProperty(value = "datetime_modify")
    String datetimeModify;
    Long votes;
    Integer populatiry;
    @JsonProperty(value = "backdrop_available")
    Boolean backdropAvailable;
    @JsonProperty(value = "movie_url")
    String movieUrl;
    @JsonProperty(value = "trailer_url")
    String trailerUrl;
    @JsonProperty(value = "packages_tvh")
    List<String> packagesTvh;
    List<String> downloadable;
    @JsonProperty(value = "master_objs")
    List<MasterObject> masterObjects;
    @JsonIgnore//(value = "trailer_array")
    List<Objects> trailerArray;
    @JsonProperty(value = "is_presale")
    Boolean isPresale;
    @JsonProperty(value = "datetime_publication")
    String datetimePublication;
    @JsonProperty(value = "rightholder_content_id")
    Long rightholderContentId;
    @JsonProperty(value = "playback_id")
    Long playbackId;
    @JsonProperty(value = "sound_tracks_description")
    List<String> soundTracksDescription;
    @JsonProperty(value = "external_ids")
    List<ExternalId> externalIds;
    @JsonProperty(value = "movie_drm")
    String movieDrm;
    Object price;
    @JsonProperty(value = "trailer_available")
    Boolean trailerAvailable;
    @JsonProperty(value = "file_data")
    Object fileData;
    String year;
    List<Country> countries;
    List<Genre> genres;
    List<Actor> actors;
    List<Director> directors;
    @JsonProperty(value = "in_subscriptions")
    List<String> inSubscription;
}
