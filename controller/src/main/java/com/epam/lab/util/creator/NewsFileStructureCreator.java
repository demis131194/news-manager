package com.epam.lab.util.creator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NewsFileStructureCreator {
    private static String SUB_FOLDER_NAME = "news_subfolder_%d";

    private int subFolderCount;
    private Path rootCreationPath;
    private int filesCount;
    private int averageDepth;
    private int filesPerDirectory;

    private int periodTimeMilSec;

    private List<CreateFilesThread> threads = new ArrayList<>();

    public NewsFileStructureCreator(Path rootCreationPath, int subFolderCount, int filesCount, Integer periodTimeMilSec) {
        this.rootCreationPath = rootCreationPath;
        this.subFolderCount = subFolderCount;
        this.filesCount = filesCount;
        this.periodTimeMilSec = periodTimeMilSec;
    }

    public void generateNewsFileStructure() {
        averageDepth = (int) Math.ceil(subFolderCount / 3.0);
        filesPerDirectory = filesCount / (subFolderCount + 1);

        threads.add(new CreateFilesThread(rootCreationPath, filesPerDirectory, periodTimeMilSec));
        try {
            if (!Files.exists(rootCreationPath)) {
                Files.createDirectory(rootCreationPath);
            }

            createSubFolders();

            threads.forEach(CreateFilesThread::start);
            for (CreateFilesThread thread : threads) {
                thread.join();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createSubFolders() throws IOException {
        for (int i = 0; i < subFolderCount;) {
            Path currentPath = rootCreationPath.resolve(Paths.get(String.format(SUB_FOLDER_NAME, i)));
            Files.createDirectories(currentPath);
            threads.add(new CreateFilesThread(currentPath, filesPerDirectory, periodTimeMilSec));
            i++;

            for (int j = 1; j<averageDepth && i<subFolderCount; j++, i++) {
                currentPath = currentPath.resolve(Paths.get(String.format(SUB_FOLDER_NAME, i)));
                Files.createDirectories(currentPath);
                threads.add(new CreateFilesThread(currentPath, filesPerDirectory, periodTimeMilSec));
            }
        }
    }

}
