package com.epam.lab.service.mapper;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.dto.NewsTo;
import com.epam.lab.dto.TagTo;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Component
public class NewsMapper {

    private ModelMapper modelMapper;

    @Autowired
    public NewsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public News toEntity(NewsTo newsTo) {
        return Objects.isNull(newsTo) ? null : modelMapper.map(newsTo, News.class);
    }

    public NewsTo toDto(News news, Author author, Set<Tag> tags) {
        if (news == null || author == null || tags == null) {
            return null;
        }
        NewsTo newsTo = modelMapper.map(news, NewsTo.class);
        newsTo.setAuthor(modelMapper.map(author, AuthorTo.class));
        tags.forEach(tag -> newsTo.getTags().add(modelMapper.map(tag, TagTo.class)));
        return newsTo;
    }
}
