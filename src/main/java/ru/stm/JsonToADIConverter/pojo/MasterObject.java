package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterObject {
    private Long id;
    private String type;
    private Long size;
    private String mime;
    private String location;
    private String path;
    private String url;
    private String description;
    private String lang;
    private Integer duration;
    private Integer width;
    private Integer height;
    private Long bitrate;
    @JsonProperty(value = "content_resolution")
    private String contentResolution;
    private Integer fps;
    @JsonProperty(value = "is_hdr")
    private String isHdr;
    @JsonProperty(value = "audio_type")
    private String audioType;
    @JsonProperty(value = "skipping_fragments")
    private List<SkippingFragment> skippingFragments;
    @JsonProperty(value = "is_preview")
    private Boolean isPreview;
    @JsonProperty(value = "preview_duration")
    private Long previewDuration;
    @JsonProperty(value = "preview_start_time")
    private String previewStartTime;
    @JsonProperty(value = "duration_text")
    private String durationText;
    private String format;
    private List<String> subtitles;
}
