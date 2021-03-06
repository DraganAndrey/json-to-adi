package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Genre {
    @JsonProperty(value = "genre_id")
    private Long genreId;
    private String title;
    private String type;
}
