package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.exeption.ServiceException;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.jdbc.AuthorRepositoryImpl;
import com.epam.lab.repository.jdbc.specification.Specification;
import com.epam.lab.repository.jdbc.specification.author.FindAllAuthorsSpecification;
import com.epam.lab.repository.jdbc.specification.author.FindAuthorByIdSpecification;
import com.epam.lab.repository.jdbc.specification.author.FindAuthorByNewsIdSpecification;
import com.epam.lab.service.AuthorService;
import com.epam.lab.service.impl.mapper.AuthorMapper;
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

    private static final FindAllAuthorsSpecification FIND_ALL_AUTHORS_SPECIFICATION = new FindAllAuthorsSpecification();

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
        throw new ServiceException("Create author, id should be null!");
    }

    @Override
    @Transactional
    public AuthorTo update(AuthorTo authorTo) {
        if (authorTo.getId() != null) {
            Author entity = mapper.toEntity(authorTo);
            boolean isUpdate = authorRepository.update(entity);
            if (isUpdate) {
                return findById(authorTo.getId());
            }
            throw new ServiceException("Cant update author, author id = " + authorTo.getId());
        }
        throw new ServiceException("Update author, id shouldn't be null!");
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
            Specification specification = new FindAuthorByIdSpecification(authorId);
            List<Author> result = authorRepository.findBySpecification(specification);
            if (result.isEmpty()) {
                throw new ServiceException("Author not found by author id = " + authorId);
            }
            return mapper.toDto(result.get(0));
        }
        throw new ServiceException("Find author, author id should be > 0!");
    }

    @Override
    public List<AuthorTo> findAll() {
        List<Author> allAuthors = authorRepository.findBySpecification(FIND_ALL_AUTHORS_SPECIFICATION);
        return convertToAuthorTo(allAuthors);
    }

    @Override
    public long countAll() {
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
            if (author.isEmpty()) {
                throw new ServiceException("Not found author by news id, news id = " + newsId);
            }
            return mapper.toDto(author.get(0));
        }
        throw new ServiceException("Find author by news id, news id should be > 0!");
    }

    private List<AuthorTo> convertToAuthorTo(List<Author> allAuthors) {
        return allAuthors.stream()
                .map(author -> mapper.toDto(author))
                .collect(Collectors.toList());
    }
}
