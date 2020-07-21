package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageJson {
    private String type;
    private String mime;
    private String lang;
    private Integer width;
    private Integer height;
    private String url;
}
