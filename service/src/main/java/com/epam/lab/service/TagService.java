package com.epam.lab.service;

import com.epam.lab.dto.TagTo;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.service.mapper.TagMapper;
import com.epam.lab.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagService implements BaseService<TagTo> {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagMapper mapper;

    @Override
    public TagTo create(TagTo tagTo) {
        if (Validator.validate(tagTo) && tagTo.getId() == null) {
            Tag entity = mapper.toEntity(tagTo);
            long tagId = tagRepository.create(entity);
            entity = tagRepository.findById(tagId);
            return mapper.toDto(entity);
        }
        return null;
    }

    @Override
    public TagTo update(TagTo tagTo) {
        if (Validator.validate(tagTo) && tagTo.getId() != null) {
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
            return tagRepository.delete(id);
        }
        return false;
    }

    @Override
    public TagTo findById(long id) {
        if (Validator.validateId(id)) {
            Tag tag = tagRepository.findById(id);
            return mapper.toDto(tag);
        }
        return null;
    }

    @Override
    public Set<TagTo> findAll() {
        Set<Tag> allTags = tagRepository.findAll();
        Set<TagTo> resultTagToSet = allTags.stream()
                .map(tag -> mapper.toDto(tag))
                .collect(Collectors.toSet());
        return resultTagToSet;
    }

    @Override
    public int countAll() {
        return tagRepository.countAll();
    }

    public Set<TagTo> findTagsByNewsId(long newsId) {
        if (Validator.validateId(newsId)) {
            Set<Tag> tagsByNewsId = tagRepository.findTagsByNewsId(newsId);
            Set<TagTo> resultTagTo = tagsByNewsId.stream()
                    .map(tag -> mapper.toDto(tag))
                    .collect(Collectors.toSet());
            return resultTagTo;
        }
        return Collections.<TagTo>emptySet();
    }

    public TagTo findTagByName(String tagName) {
        if (Validator.validateTagName(tagName)) {
            Tag tagByName = tagRepository.findTagByName(tagName);
            TagTo tagTo = mapper.toDto(tagByName);
            return tagTo;
        }
        return null;
    }
}
