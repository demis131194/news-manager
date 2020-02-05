package com.epam.lab.service;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.service.mapper.TagMapper;
import com.epam.lab.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService implements BaseService<AuthorTo> {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagMapper mapper;

    @Override
    public AuthorTo create(AuthorTo tagTo) {
        if (Validator.validate(tagTo)) {

        }
        return null;
    }

    @Override
    public AuthorTo update(AuthorTo tagTo) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public AuthorTo findById(long id) {
        return null;
    }

    @Override
    public List<AuthorTo> findAll() {
        return null;
    }
}
