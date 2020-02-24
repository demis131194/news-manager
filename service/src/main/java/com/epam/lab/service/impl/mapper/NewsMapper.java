package com.epam.lab.service.impl.mapper;

import com.epam.lab.dto.NewsTo;
import com.epam.lab.model.News;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class NewsMapper {

    private ModelMapper modelMapper;

    @Autowired
    public NewsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public News toEntity(NewsTo newsTo) {
        Objects.requireNonNull(newsTo);
        return modelMapper.map(newsTo, News.class);
    }

    public NewsTo toDto(News news) {
        Objects.requireNonNull(news);
        return modelMapper.map(news, NewsTo.class);
    }
}
