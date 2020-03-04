package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.exeption.ServiceException;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.service.AuthorService;
import com.epam.lab.service.impl.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private AuthorMapper mapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Override
    public AuthorTo save(AuthorTo authorTo) {
        Author entity = mapper.toEntity(authorTo);
        Author savedAuthor = authorRepository.save(entity);
        if (savedAuthor == null) {
            throw new ServiceException("Can't update author, wrong id - " + authorTo.getId());
        }
        return mapper.toDto(savedAuthor);
    }

    @Override
    public boolean delete(long id) {
        return authorRepository.delete(id);
    }

    @Override
    public AuthorTo findById(long authorId) {
        Author authorById = authorRepository.findById(authorId);
        if (authorById == null) {
            throw new ServiceException("Author not found by author id = " + authorId);
        }
        return mapper.toDto(authorById);
    }

    @Override
    public List<AuthorTo> findAll() {
        List<Author> allAuthors = authorRepository.findAll();
        return convertToAuthorTo(allAuthors);
    }

    @Override
    public long countAll() {
        return authorRepository.countAll();
    }

    private List<AuthorTo> convertToAuthorTo(List<Author> allAuthors) {
        return allAuthors.stream()
                .map(author -> mapper.toDto(author))
                .collect(Collectors.toList());
    }
}
