package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostMessage {
    private String title;
    private String body;
    @JsonProperty(value = "button_text")
    private String buttonText;
    @JsonProperty(value = "button_link")
    private String buttonLink;
    @JsonProperty(value = "promoted_content")
    private String promotedContent;
}
