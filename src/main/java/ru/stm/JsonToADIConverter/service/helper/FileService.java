package ru.stm.JsonToADIConverter.service.helper;

import java.nio.charset.Charset;
import java.util.List;

public interface FileService {
    String prepareOutputDirectory(String path);
    void deleteAllFilesFolder(String path);
    List<String> readFiles(String path, Charset encoding);

}
