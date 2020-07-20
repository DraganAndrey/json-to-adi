package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InputJson {
    JsonParams params;
    @JsonProperty(value = "api_version")
    String apiVerison;
    JsonResult result;
    List<String> warning;
    List<String> error;
}
