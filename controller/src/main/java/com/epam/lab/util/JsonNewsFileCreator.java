package com.epam.lab.util;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.dto.NewsTo;
import com.epam.lab.dto.TagTo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class JsonNewsFileCreator {
    private static AtomicInteger newsCounter = new AtomicInteger();

    private static final String FILE_NAME = "json_news_file_%d.json";
    private static final String TITLE = "title_%d";
    private static final String SHORT_TEXT = "Short_text";
    private static final String VIOLATED_SHORT_TEXT = Strings.repeat(SHORT_TEXT, 11);
    private static final String FULL_TEXT = "Full_text";
    private static final AuthorTo AUTHOR = new AuthorTo("Generated", "Generated");
    private static final Collection<TagTo> TAGS = Sets.newHashSet(
            new TagTo("Generated tag-1"),
            new TagTo("Generated tag-2"),
            new TagTo("Generated tag-3")
    );

    private Path generatePath;
    private AtomicInteger countFile = new AtomicInteger();

    private ObjectMapper mapper = new ObjectMapper();

    public JsonNewsFileCreator(Path generatePath) {
        this.generatePath = generatePath;
    }

    public void createValidFile() {
        Path filePath = generatePath.resolve(Paths.get(String.format(FILE_NAME, countFile.incrementAndGet())));
        try {
            Files.createFile(filePath);
            ArrayList<NewsTo> news = createValidNews();
            String json = mapper.writeValueAsString(news);
            Files.write(filePath, json.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createWrongJsonFile() {
        Path filePath = generatePath.resolve(Paths.get(String.format(FILE_NAME, countFile.incrementAndGet())));
        try {
            Files.createFile(filePath);
            ArrayList<NewsTo> news = createValidNews();
            String str = mapper.writeValueAsString(news).replaceAll(":", "?");
            Files.write(filePath, str.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createWrongFieldNameFile() {
        Path filePath = generatePath.resolve(Paths.get(String.format(FILE_NAME, countFile.incrementAndGet())));
        try {
            Files.createFile(filePath);
            ArrayList<NewsTo> news = createValidNews();
            String str = mapper.writeValueAsString(news).replace("author:", "autQWEs:");
            Files.write(filePath, str.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNonValidBeanFile() {
        Path filePath = generatePath.resolve(Paths.get(String.format(FILE_NAME, countFile.incrementAndGet())));
        try {
            Files.createFile(filePath);
            ArrayList<NewsTo> news = createNonValidBeanNews();
            String str = mapper.writeValueAsString(news);
            Files.write(filePath, str.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createViolationDbConstraintFile() {
        Path filePath = generatePath.resolve(Paths.get(String.format(FILE_NAME, countFile.incrementAndGet())));
        try {
            Files.createFile(filePath);
            ArrayList<NewsTo> news = createViolateDbConstraintNews();
            String str = mapper.writeValueAsString(news);
            Files.write(filePath, str.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<NewsTo> createValidNews() {
        return Lists.newArrayList(new NewsTo(String.format(TITLE, newsCounter.incrementAndGet()), SHORT_TEXT, FULL_TEXT, AUTHOR, TAGS),
                new NewsTo(String.format(TITLE, newsCounter.incrementAndGet()), SHORT_TEXT, FULL_TEXT, AUTHOR, TAGS),
                new NewsTo(String.format(TITLE, newsCounter.incrementAndGet()), SHORT_TEXT, FULL_TEXT, AUTHOR, TAGS)
        );
    }

    private ArrayList<NewsTo> createViolateDbConstraintNews() {
        return Lists.newArrayList(new NewsTo(String.format(TITLE, newsCounter.incrementAndGet()), SHORT_TEXT, FULL_TEXT, AUTHOR, TAGS),
                new NewsTo(String.format(TITLE, newsCounter.incrementAndGet()), VIOLATED_SHORT_TEXT, FULL_TEXT, AUTHOR, TAGS),
                new NewsTo(String.format(TITLE, newsCounter.incrementAndGet()), SHORT_TEXT, FULL_TEXT, AUTHOR, TAGS)
        );
    }

    private ArrayList<NewsTo> createNonValidBeanNews() {
        return Lists.newArrayList(new NewsTo(String.format(TITLE, newsCounter.incrementAndGet()), SHORT_TEXT, FULL_TEXT, AUTHOR, TAGS),
                new NewsTo(String.format(TITLE, newsCounter.incrementAndGet()), VIOLATED_SHORT_TEXT, FULL_TEXT, AUTHOR, TAGS),
                new NewsTo(String.format(TITLE, newsCounter.incrementAndGet()), SHORT_TEXT, FULL_TEXT, AUTHOR, TAGS)
        );
    }

}
