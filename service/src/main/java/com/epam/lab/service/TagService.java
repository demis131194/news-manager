package com.epam.lab.service;

import com.epam.lab.exeption.ServiceException;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public long createTag(Tag tag) throws ServiceException {
        if (Validator.validate(tag)) {
            if (tag.isNew()) {
                long authorId = tagRepository.create(tag);
                tag.setId(authorId);
            }
            return tag.getId();
        }
        throw new ServiceException("Illegal argument tag");
    }

    public boolean updateTag(Tag tag) {
        if (Validator.validate(tag)) {
            return tagRepository.update(tag);
        }
        return false;
    }

    public boolean deleteTag(long tagId) {
        return tagRepository.delete(tagId);
    }

    public Tag findById(long id) {
        return tagRepository.findById(id);
    }
}
