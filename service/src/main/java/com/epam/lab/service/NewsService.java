package com.epam.lab.service;

import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private TagService tagService;


}
