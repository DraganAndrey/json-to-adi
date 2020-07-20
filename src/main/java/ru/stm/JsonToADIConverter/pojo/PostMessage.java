package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostMessage {
    String title;
    String body;
    @JsonProperty(value = "button_text")
    String buttonText;
    @JsonProperty(value = "button_link")
    String buttonLink;
    @JsonProperty(value = "promoted_content")
    String promotedContent;
}
