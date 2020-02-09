package com.epam.lab.service;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.specification.Specification;
import com.epam.lab.repository.specification.author.FindAuthorByNewsIdSpecification;
import com.epam.lab.service.mapper.AuthorMapper;
import com.epam.lab.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService implements BaseService<AuthorTo> {

    private AuthorRepository authorRepository;
    private AuthorMapper mapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

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
            return isUpdate ? authorTo : null;
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
        return allAuthors.stream()
                .map(author -> mapper.toDto(author))
                .collect(Collectors.toList());
    }

    @Override
    public int countAll() {
        return authorRepository.countAll();
    }

    public AuthorTo findByNewsId(long newsId) {
        if (Validator.validateId(newsId)) {
            Specification specification = new FindAuthorByNewsIdSpecification(newsId);
            List<Author> author = authorRepository.findBySpecification(specification);
            return !author.isEmpty() ? mapper.toDto(author.get(0)) : null;
        }
        return null;
    }
}
