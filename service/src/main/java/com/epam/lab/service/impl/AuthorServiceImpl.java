package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.exeption.ServiceException;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.jdbc.AuthorRepositoryImpl;
import com.epam.lab.repository.jdbc.specification.Specification;
import com.epam.lab.repository.jdbc.specification.author.FindAuthorByNewsIdSpecification;
import com.epam.lab.service.AuthorService;
import com.epam.lab.service.impl.mapper.AuthorMapper;
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
public class AuthorServiceImpl implements AuthorService {
    private static final Logger logger = LogManager.getLogger(AuthorServiceImpl.class);

    private AuthorRepository authorRepository;
    private AuthorMapper mapper;

    /**
     * Instantiates a new Author service.
     *
     * @param authorRepository the author repository
     * @param mapper           the mapper
     */
    @Autowired
    public AuthorServiceImpl(AuthorRepositoryImpl authorRepository, AuthorMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public AuthorTo create(AuthorTo authorTo) {
        if (authorTo.getId() == null) {
            Author entity = mapper.toEntity(authorTo);
            long authorId = authorRepository.create(entity);
            return findById(authorId);
        }
        throw new ServiceException("Create author, need id == null!");
    }

    @Override
    @Transactional
    public AuthorTo update(AuthorTo authorTo) {
        if (authorTo.getId() != null) {
            Author entity = mapper.toEntity(authorTo);
            boolean isUpdate = authorRepository.update(entity);
            return isUpdate ? findById(authorTo.getId()) : null;
        }
        throw new ServiceException("Update author, need id != null!");
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        if (id > 0) {
            return authorRepository.delete(id);
        }
        throw new ServiceException("Delete author, need id > 0!");
    }

    @Override
    public AuthorTo findById(long id) {
        if (id > 0) {
            Author author = authorRepository.findById(id);
            return mapper.toDto(author);
        }
        throw new ServiceException("Find author, need id > 0!");
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
        if (newsId > 0) {
            Specification specification = new FindAuthorByNewsIdSpecification(newsId);
            List<Author> author = authorRepository.findBySpecification(specification);
            return !author.isEmpty() ? mapper.toDto(author.get(0)) : null;
        }
        throw new ServiceException("Find author by news id, need id > 0!");
    }
}
