package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonParams {
    Map<String, String> cookies;
    Argumets arguments;

    public static class Argumets {
        List<String> client;
    }

}
