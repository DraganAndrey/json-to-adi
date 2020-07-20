package ru.stm.JsonToADIConverter.pojo;

import java.util.List;

public class InputJson {
    JsonParams params;
    String apiVerison;
    List<JsonResult> result;
    List<String> warning;
    List<String> error;
}
