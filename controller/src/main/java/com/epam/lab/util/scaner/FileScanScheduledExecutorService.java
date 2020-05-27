package com.epam.lab.util.scaner;

import com.epam.lab.service.NewsService;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileScanScheduledExecutorService {

    private final int THREADS_NUMBER;
    private final int SCAN_DELAY;
    private final File ROOT_FILE;
    private final NewsService NEWS_SERVICE;

    private ExecutorService service;

    public FileScanScheduledExecutorService(File rootFile, NewsService newsService, int threadsNumber, int scanDelay) {
        this.NEWS_SERVICE = newsService;
        this.ROOT_FILE = rootFile;
        this.THREADS_NUMBER = threadsNumber;
        this.SCAN_DELAY = scanDelay;
    }

    public void startScheduleScan() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        service = Executors.newFixedThreadPool(THREADS_NUMBER);
        Runnable scheduledTask = () -> {
            ScanTask scanTask = new ScanTask(ROOT_FILE, NEWS_SERVICE);
            for (int i = 0; i < THREADS_NUMBER; i++) {
                service.execute(scanTask);
            }
        };
        scheduledExecutorService.scheduleAtFixedRate(scheduledTask, SCAN_DELAY, SCAN_DELAY, TimeUnit.MILLISECONDS);
    }

}
