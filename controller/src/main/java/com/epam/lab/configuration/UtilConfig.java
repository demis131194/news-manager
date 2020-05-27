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

    @Bean
    public NewsFileStructureCreator newsFileStructureCreator(@Value("${create.files}") Integer numberOfFiles,
                                                             @Value("${create.folders}") Integer numberOfSubfolder,
                                                             @Value("${create.period}") Integer timePeriod) {
        Path path = Paths.get(folderPath);
        NewsFileStructureCreator newsFileStructureCreator = new NewsFileStructureCreator(path, numberOfSubfolder, numberOfFiles, timePeriod);
        newsFileStructureCreator.generateNewsFileStructure();
        return newsFileStructureCreator;
    }

    @Bean
    public FileScanScheduledExecutorService fileScanScheduledExecutorService(NewsService newsService,
                                                                             @Value("${scan.threads}") Integer threads,
                                                                             @Value("${scan.delay}") Integer delay) {
        Path path = Paths.get(folderPath);
        FileScanScheduledExecutorService scan = new FileScanScheduledExecutorService(path.toFile(),  newsService, threads, delay);
        scan.startScheduleScan();
        return scan;
    }

}
