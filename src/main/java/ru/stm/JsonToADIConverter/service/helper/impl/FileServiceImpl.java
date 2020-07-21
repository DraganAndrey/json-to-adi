package ru.stm.JsonToADIConverter.service.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.stm.JsonToADIConverter.service.helper.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void deleteAllFilesFolder(String path) {
        if (path.isEmpty()) {
            log.info("Каталог пуст");
            return;
        }
        File [] files = new File(path).listFiles();
        if (Objects.nonNull(files)) {
            for (File myFile :files)
            if (myFile.isFile()) {
                myFile.delete();
                log.info("Файл {} был удален", myFile.getName());
            }
        }

    }

    public List<String> readFiles(String outputPath, Charset encoding) {
        List<String> fileList = new ArrayList<>();
        File [] files = new File(outputPath).listFiles();
        if (Objects.nonNull(files)) {
            for (File myFile :files)
                if (myFile.isFile()) {
                    byte[] encoded = new byte[0];
                    try {
                        encoded = Files.readAllBytes(Paths.get(myFile.getPath()));
                    } catch (IOException e) {
                        log.error("Ошибка чтения файла");
                    }
                    fileList.add(new String(encoded, encoding));
                }
        }
        return fileList;
    }

}
