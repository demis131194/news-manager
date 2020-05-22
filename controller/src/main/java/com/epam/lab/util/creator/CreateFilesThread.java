package com.epam.lab.util.creator;

import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class CreateFilesThread extends Thread {
    private static long PERIOD_TIME = TimeUnit.MILLISECONDS.toMillis(80);

    private Path generatePath;
    private JsonNewsFileCreator generator;

    private int totalNumbOfFiles;
    private int numbOfWrongFiles;
    private int numbOfValidFiles;

    public CreateFilesThread(Path generatePath, int totalNumbOfFiles) {
        this.generatePath = generatePath;
        this.totalNumbOfFiles = totalNumbOfFiles;
        this.numbOfWrongFiles = (int) Math.ceil(this.totalNumbOfFiles / 20.0d);
        this.numbOfValidFiles = totalNumbOfFiles - this.numbOfWrongFiles * 4;
    }

    @Override
    public void run() {
        generator = new JsonNewsFileCreator(generatePath);
        createAllValidFiles();
        createAllInvalidFiles();
    }

    private void createAllValidFiles() {
        for(int i = 0; i < numbOfValidFiles; i++) {
            generator.createValidFile();
            sleep();
        }
    }

    private void createAllInvalidFiles() {
        for(int i = 0; i < numbOfWrongFiles; i++) {
            generator.createNonValidBeanFile();
            sleep();
            generator.createViolationDbConstraintFile();
            sleep();
            generator.createWrongFieldNameFile();
            sleep();
            generator.createWrongJsonFile();
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(PERIOD_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
