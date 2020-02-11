package com.epam.lab.service.impl.mapper;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.dto.NewsTo;
import com.epam.lab.dto.TagTo;
import com.epam.lab.model.News;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

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

    public NewsTo toDto(News news, AuthorTo author, List<TagTo> tags) {
        if (news == null || tags == null) {
            return null;
        }
        NewsTo newsTo = modelMapper.map(news, NewsTo.class);
        newsTo.setAuthor(author);
        newsTo.setTags(tags);
        return newsTo;
    }
}
