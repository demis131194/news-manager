package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.specification.Specification;
import com.epam.lab.repository.specification.author.FindAuthorByNewsIdSpecification;
import com.epam.lab.service.AuthorServiceInterface;
import com.epam.lab.service.impl.mapper.AuthorMapper;
import com.epam.lab.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Author service.
 */
@Service
@Transactional(readOnly = true)
public class AuthorService implements AuthorServiceInterface {
    private static final Logger logger = LogManager.getLogger(AuthorService.class);

    private AuthorRepository authorRepository;
    private AuthorMapper mapper;

    /**
     * Instantiates a new Author service.
     *
     * @param authorRepository the author repository
     * @param mapper           the mapper
     */
    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public AuthorTo create(AuthorTo authorTo) {
        if (Validator.validate(authorTo) && authorTo.getId() == null) {
            Author entity = mapper.toEntity(authorTo);
            long authorId = authorRepository.create(entity);
            return findById(authorId);
        }
        logger.warn("AuthorService, validation fail : " + authorTo.toString());
        return null;
    }

    @Override
    @Transactional
    public AuthorTo update(AuthorTo authorTo) {
        if (Validator.validate(authorTo) && authorTo.getId() != null) {
            Author entity = mapper.toEntity(authorTo);
            boolean isUpdate = authorRepository.update(entity);
            return isUpdate ? findById(authorTo.getId()) : null;
        }
        logger.warn("AuthorService, validation fail : " + authorTo.toString());
        return null;
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        if (Validator.validateId(id)) {
            return authorRepository.delete(id);
        }
        logger.warn("AuthorService, validation fail id: " + id);
        return false;
    }

    @Override
    public AuthorTo findById(long id) {
        if (Validator.validateId(id)) {
            Author author = authorRepository.findById(id);
            return mapper.toDto(author);
        }
        logger.warn("AuthorService, validation fail id: " + id);
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

    /**
     * Find by news id author to.
     *
     * @param newsId the news id
     * @return the author to
     */
    @Override
    public AuthorTo findByNewsId(long newsId) {
        if (Validator.validateId(newsId)) {
            Specification specification = new FindAuthorByNewsIdSpecification(newsId);
            List<Author> author = authorRepository.findBySpecification(specification);
            return !author.isEmpty() ? mapper.toDto(author.get(0)) : null;
        }
        logger.warn("AuthorService, validation fail newsId: " + newsId);
        return null;
    }
}
