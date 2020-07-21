package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InputJson {
    private JsonParams params;
    @JsonProperty(value = "api_version")
    private String apiVerison;
    private JsonResult result;
    private List<String> warning;
    private List<String> error;
}
