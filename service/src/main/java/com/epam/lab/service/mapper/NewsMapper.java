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

    public NewsTo toDto(News news, AuthorTo author, Set<TagTo> tags) {
        if (news == null || tags == null) {
            return null;
        }
        NewsTo newsTo = modelMapper.map(news, NewsTo.class);
        newsTo.setAuthor(author);
        newsTo.setTags(tags);
        return newsTo;
    }
}
