package ru.stm.JsonToADIConverter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkippingFragment {
    private String name;
    private String startTime;
    private String targetTime;
}
