package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterObject {
    Long id;
    String type;
    Long size;
    String mime;
    String location;
    String path;
    String url;
    String description;
    String lang;
    Integer duration;
    Integer width;
    Integer height;
    Long bitrate;
    @JsonProperty(value = "content_resolution")
    String contentResolution;
    Integer fps;
    @JsonProperty(value = "is_hdr")
    String isHdr;
    @JsonProperty(value = "audio_type")
    String audioType;
    @JsonProperty(value = "skipping_fragments")
    List<String> skippingFragmenst;
    @JsonProperty(value = "is_preview")
    Boolean isPreview;
    @JsonProperty(value = "preview_duration")
    Long previewDuration;
    @JsonProperty(value = "preview_start_time")
    String previewStartTime;
    @JsonProperty(value = "duration_text")
    String durationText;
    String format;
    List<String> subtitles;
}
