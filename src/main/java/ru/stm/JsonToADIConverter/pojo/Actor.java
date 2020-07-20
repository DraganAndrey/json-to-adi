package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Actor {
    private Long id;
    private String name;
    private String name_en;
}
