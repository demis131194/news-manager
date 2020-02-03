package com.epam.lab.service.mapper;

import com.epam.lab.dto.NewsTo;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;

import java.util.Set;

public class NewsMapper {

    public static NewsTo toDto(News news, Set<Tag> tags, Author author) {
        return new NewsTo(news, author, tags);
    }
}
