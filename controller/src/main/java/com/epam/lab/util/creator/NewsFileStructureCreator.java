package com.epam.lab.util.creator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NewsFileStructureCreator {
    private static String SUB_FOLDER_NAME = "subfolder_%d";

    private int subFolderCount;
    private Path rootCreationPath;
    private int filesCount;
    private int averageDepth;
    private int filesPerDirectory;

    private List<CreateFilesThread> threads = new ArrayList<>();

    public NewsFileStructureCreator(Path rootCreationPath, int subFolderCount, int filesCount) {
        this.rootCreationPath = rootCreationPath;
        this.subFolderCount = subFolderCount;
        this.filesCount = filesCount;
        averageDepth = (int) Math.ceil(subFolderCount / 3.0);
        filesPerDirectory = filesCount / (subFolderCount + 1);
    }

    public void generateNewsFileStructure() {
        threads.add(new CreateFilesThread(rootCreationPath, filesPerDirectory));
        try {
            for (int i = 0; i < subFolderCount;) {
                Path currentPath = rootCreationPath.resolve(Paths.get(String.format(SUB_FOLDER_NAME, i)));
                Files.createDirectories(currentPath);
                threads.add(new CreateFilesThread(currentPath, filesPerDirectory));
                i++;

                for (int j = 1; j<averageDepth && i<subFolderCount; j++, i++) {
                    currentPath = currentPath.resolve(Paths.get(String.format(SUB_FOLDER_NAME, i)));
                    Files.createDirectories(currentPath);
                    threads.add(new CreateFilesThread(currentPath, filesPerDirectory));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        threads.forEach(CreateFilesThread::start);
    }

}
