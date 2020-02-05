package com.epam.lab.service;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.service.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements BaseService<AuthorTo> {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper mapper;

    @Override
    public AuthorTo create(AuthorTo obj) {
        return null;
    }

    @Override
    public AuthorTo update(AuthorTo obj) {
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

    @Override
    public int countAll() {
        return 0;
    }
}
