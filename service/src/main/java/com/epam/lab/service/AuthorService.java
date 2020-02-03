package com.epam.lab.service;

import com.epam.lab.exeption.ServiceException;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public long createAuthor(Author author) throws ServiceException {
        if (Validator.validate(author)) {
            if (author.isNew()) {
                long authorId = authorRepository.create(author);
                author.setId(authorId);
            }
            return author.getId();
        }
        throw new ServiceException("Illegal argument author");
    }

    public boolean updateAuthor(Author author) {
        if (Validator.validate(author)) {
            return authorRepository.update(author);
        }
        return false;
    }

    public boolean deleteAuthor(long authorId) {
        return authorRepository.delete(authorId);
    }

    public Author findById(long id) {
        return authorRepository.findById(id);
    }

}
