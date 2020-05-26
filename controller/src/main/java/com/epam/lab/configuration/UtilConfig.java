package com.epam.lab.configuration;

import com.epam.lab.service.NewsService;
import com.epam.lab.util.creator.NewsFileStructureCreator;
import com.epam.lab.util.scaner.FileScanScheduledExecutorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@PropertySource("classpath:bootdata/create-data-config.properties")
public class UtilConfig {

    @Value("${folder.path}")
    private String folderPath;

    @Value("${files.numb}")
    private Integer numberOfFiles;

    @Value("${folders.count}")
    private Integer numberOfSubfolder;

    @Bean
    public NewsFileStructureCreator newsFileStructureCreator() {
        Path path = Paths.get(folderPath);
        NewsFileStructureCreator newsFileStructureCreator = new NewsFileStructureCreator(path, numberOfSubfolder, numberOfFiles);
        newsFileStructureCreator.generateNewsFileStructure();
        return newsFileStructureCreator;
    }

    @Bean
    public FileScanScheduledExecutorService fileScanScheduledExecutorService(NewsService newsService) {
        Path path = Paths.get(folderPath);
        FileScanScheduledExecutorService fileScanScheduledExecutorService = new FileScanScheduledExecutorService(path.toFile(),  newsService);
        fileScanScheduledExecutorService.startScheduleScan();
        return fileScanScheduledExecutorService;
    }

}
