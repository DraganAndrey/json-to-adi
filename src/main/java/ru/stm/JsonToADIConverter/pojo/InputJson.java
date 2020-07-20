package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InputJson {
    JsonParams params;
    @JsonProperty(value = "api_version")
    String apiVerison;
    List<JsonResult> result;
    List<String> warning;
    List<String> error;
}
