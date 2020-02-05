package com.epam.lab.service;

import com.epam.lab.dto.NewsTo;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.service.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class NewsService implements BaseService<NewsTo> {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private TagService tagService;

    @Autowired
    private NewsMapper mapper;

    @Override
    public NewsTo create(NewsTo obj) {
        return null;
    }

    @Override
    public NewsTo update(NewsTo obj) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public NewsTo findById(long id) {
        return null;
    }

    @Override
    public Collection<NewsTo> findAll() {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }
}
