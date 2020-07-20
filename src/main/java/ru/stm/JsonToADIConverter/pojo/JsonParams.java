package ru.stm.JsonToADIConverter.pojo;

import java.util.List;
import java.util.Map;

public class JsonParams {
    Map<String, String> cookies;
    Argumets arguments;

    public static class Argumets {
        List<String> client;
    }

}
