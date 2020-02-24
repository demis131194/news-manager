package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.exeption.ServiceException;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.service.AuthorService;
import com.epam.lab.service.impl.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private AuthorMapper mapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public AuthorTo save(AuthorTo authorTo) {
        Author entity = mapper.toEntity(authorTo);
        Author authorById = authorRepository.save(entity);
        return mapper.toDto(authorById);
    }

    @Override
    public boolean delete(long id) {
        if (id > 0) {
            return authorRepository.delete(id);
        }
        throw new ServiceException("Delete author, id should be > 0!");
    }

    @Override
    public AuthorTo findById(long authorId) {
        if (authorId > 0) {
            Author authorById = authorRepository.findById(authorId);
            if (authorById == null) {
                throw new ServiceException("Author not found by author id = " + authorId);
            }
            return mapper.toDto(authorById);
        }
        throw new ServiceException("Find author, author id should be > 0!");
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
