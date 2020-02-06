package com.epam.lab.service;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.service.mapper.AuthorMapper;
import com.epam.lab.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService implements BaseService<AuthorTo> {   // FIXME: 2/5/2020 Use validator exception?

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper mapper;

    @Override
    public AuthorTo create(AuthorTo authorTo) {
        if (Validator.validate(authorTo) && authorTo.getId() == null) {
            Author entity = mapper.toEntity(authorTo);
            long authorId = authorRepository.create(entity);
            authorTo.setId(authorId);
            return authorTo;
        }
        return null;
    }

    @Override
    public AuthorTo update(AuthorTo authorTo) {
        if (Validator.validate(authorTo) && authorTo.getId() != null) {
            Author entity = mapper.toEntity(authorTo);
            boolean isUpdate = authorRepository.update(entity);
            if (isUpdate) {
                Author updatedAuthor = authorRepository.findById(entity.getId());
                return mapper.toDto(updatedAuthor);
            }
        }
        return null;
    }

    @Override
    public boolean delete(long id) {
        if (Validator.validateId(id)) {
            return authorRepository.delete(id);
        }
        return false;
    }

    @Override
    public AuthorTo findById(long id) {
        if (Validator.validateId(id)) {
            Author author = authorRepository.findById(id);
            return mapper.toDto(author);
        }
        return null;
    }

    @Override
    public List<AuthorTo> findAll() {
        List<Author> allAuthors = authorRepository.findAll();
        List<AuthorTo> allAuthorsTo = allAuthors.stream()
                .map(author -> mapper.toDto(author))
                .collect(Collectors.toList());
        return allAuthorsTo;
    }

    @Override
    public int countAll() {
        return authorRepository.countAll();
    }

    public AuthorTo findByNewsId(long newsId) {
        if (Validator.validateId(newsId)) {
            Author authorByNewsId = authorRepository.findByNewsId(newsId);
            return mapper.toDto(authorByNewsId);
        }
        return null;
    }

    public List<AuthorTo> findByName(String name) {
        if (Validator.validateAuthorName(name)) {
            List<Author> allByName = authorRepository.findByName(name);
            List<AuthorTo> result = allByName.stream()
                    .map(author -> mapper.toDto(author))
                    .collect(Collectors.toList());
            return result;
        }
        return Collections.emptyList();
    }

    public List<AuthorTo> findBySurname(String surname) {
        if (Validator.validateAuthorSurname(surname)) {
            List<Author> allByName = authorRepository.findBySurname(surname);
            List<AuthorTo> result = allByName.stream()
                    .map(author -> mapper.toDto(author))
                    .collect(Collectors.toList());
            return result;
        }
        return Collections.emptyList();
    }
}
