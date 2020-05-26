package com.epam.lab.util.scaner;

import com.epam.lab.service.NewsService;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileScanScheduledExecutorService {

    private static final int THREADS_NUMBER = 1;
    private static final int SCAN_DELAY = 1;

    private ExecutorService service = Executors.newFixedThreadPool(THREADS_NUMBER);
    private File rootFile;
    private NewsService newsService;

    public FileScanScheduledExecutorService(File rootFile, NewsService newsService) {
        this.newsService = newsService;
        this.rootFile = rootFile;
    }

    public void startScheduleScan() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Runnable scheduledTask = () -> {
            ScanTask scanTask = new ScanTask(rootFile, newsService);
            for (int i = 0; i < THREADS_NUMBER; i++) {
                service.execute(scanTask);
            }
        };
        scheduledExecutorService.scheduleAtFixedRate(scheduledTask, SCAN_DELAY, SCAN_DELAY, TimeUnit.SECONDS);
    }

}
