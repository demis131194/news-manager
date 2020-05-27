package com.epam.lab.util.scaner;

import com.epam.lab.dto.NewsTo;
import com.epam.lab.service.NewsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ScanTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ScanTask.class);
    private static BlockingQueue<File> nodesToReview = new LinkedBlockingDeque<>();
    private final String ERROR_FOLDER_NAME = "error";

    private final File ROOT_FILE;
    private final Path ERROR_FOLDER;
    private final int TIMEOUT = 10;

    private ObjectMapper mapper = new ObjectMapper();

    private NewsService service;

    public ScanTask(File rootFolder, NewsService service) {
        this.ROOT_FILE = rootFolder;
        this.service = service;
        this.ERROR_FOLDER = Paths.get(ROOT_FILE.getPath()).resolve(ERROR_FOLDER_NAME);
        nodesToReview.add(ROOT_FILE);
    }

    @Override
    public void run() {
        File file = null;
        do  {
            try {
                file = nodesToReview.poll(TIMEOUT, TimeUnit.MILLISECONDS);
                if (file != null && file.exists()) {
                    handleFile(file);
                }
            } catch (InterruptedException e) {
                logger.error("Interrupted! ", e);
            }

        } while (file != null);
    }

    private void handleFile(File file) {
        if (file.isDirectory() && !ERROR_FOLDER_NAME.equals(file.getName())) {
            handleDirectory(file);
        } else if (file.isFile()) {
            handleJsonFile(file);
        }
    }

    private void handleJsonFile(File file) {
        try {
            List<NewsTo> list = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, NewsTo.class));
            list.forEach((news) -> service.save(news));
            file.delete();
        } catch (Exception e) {
            logger.warn("Due reading file exception occur.", e);
            if (!ERROR_FOLDER.toFile().exists()) {
                ERROR_FOLDER.toFile().mkdir();
            }

            moveFileToErrorDirectory(file);
        }
    }

    private void handleDirectory(File file) {
        File[] files = file.listFiles();
        if (files != null && files.length>0) {
            List<File> listFiles = Arrays.stream(files)
                    .filter(f -> !ERROR_FOLDER_NAME.equals(f.getName()))
                    .filter((f -> f.isDirectory() || (f.isFile() && f.getName().startsWith("json_news_file") && f.getName().endsWith(".json"))))
                    .collect(Collectors.toList());
            nodesToReview.addAll(listFiles);
        } else {
            file.delete();
        }
    }

    private void moveFileToErrorDirectory(File file) {
        try {
            Path errorPath = ERROR_FOLDER.resolve(file.getName());
            if(!errorPath.toFile().exists()) {
                Files.move(file, errorPath.toFile());
            } else {
                Files.move(file, ERROR_FOLDER.resolve(file.getName().concat("_RENAMED")).toFile());
            }
        } catch (IOException ex) {
            logger.warn("Due moving file exception occur.", ex);
        }
    }

}
