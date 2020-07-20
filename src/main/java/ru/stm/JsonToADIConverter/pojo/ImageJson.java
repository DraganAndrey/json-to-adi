package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageJson {
    String type;
    String mime;
    String lang;
    Integer width;
    Integer height;
    String url;
}
