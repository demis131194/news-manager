package com.epam.lab.service;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.dto.TagTo;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.service.mapper.TagMapper;
import com.epam.lab.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService implements BaseService<TagTo> {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagMapper mapper;

    @Override
    public TagTo create(TagTo tagTo) {
        if (Validator.validate(tagTo)) {
            Tag entity = mapper.toEntity(tagTo);
            long tagId = tagRepository.create(entity);
            tagTo.setId(tagId);
            return tagTo;
        }
        return null;
    }

    @Override
    public TagTo update(TagTo tagTo) {
        if (Validator.validate(tagTo)) {
            Tag entity = mapper.toEntity(tagTo);
            boolean isUpdate = tagRepository.update(entity);
            if (isUpdate) {
                Tag updatedTag = tagRepository.findById(entity.getId());
                return mapper.toDto(updatedTag);
            }
        }
        return null;
    }

    @Override
    public boolean delete(long id) {
        if (Validator.validateId(id)) {

        }
        return false;
    }

    @Override
    public TagTo findById(long id) {
        return null;
    }

    @Override
    public List<TagTo> findAll() {
        return null;
    }
}
