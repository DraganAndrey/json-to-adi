package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonParams {
    private Map<String, String> cookies;
    private Argumets arguments;

    @Data
    public static class Argumets {
        private List<String> client;
    }

}
