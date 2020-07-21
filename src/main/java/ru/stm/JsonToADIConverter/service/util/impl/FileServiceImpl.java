package ru.stm.JsonToADIConverter.service.util.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.stm.JsonToADIConverter.service.util.FileService;

import java.io.File;
import java.nio.file.Files;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Override
    public String prepareOutputDirectory(String path) {
        if (!path.isEmpty() && createOutputDirectory(new File(path))) {
            return path + "/";
        }
        return StringUtils.EMPTY;
    }

    private boolean createOutputDirectory(File dir) {
        if (!dir.exists()) {
            return dir.mkdirs();
        }
        if (dir.exists() && !Files.isDirectory(dir.toPath())) {
            log.warn("Файл с таким именем уже существует");
            return false;
        }
        return true;
    }

}
