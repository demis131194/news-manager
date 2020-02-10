package com.epam.lab.service;

import com.epam.lab.dto.TagTo;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.repository.specification.Specification;
import com.epam.lab.repository.specification.tag.FindTagByNameSpecification;
import com.epam.lab.repository.specification.tag.FindTagsByNewsIdSpecification;
import com.epam.lab.service.mapper.TagMapper;
import com.epam.lab.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService implements BaseService<TagTo> {
    private static final Logger logger = LogManager.getLogger(AuthorService.class);

    private TagRepository tagRepository;
    private TagMapper mapper;

    @Autowired
    public TagService(TagRepository tagRepository, TagMapper mapper) {
        this.tagRepository = tagRepository;
        this.mapper = mapper;
    }

    @Override
    public TagTo create(TagTo tagTo) {
        if (Validator.validate(tagTo) && tagTo.getId() == null) {
            Tag entity = mapper.toEntity(tagTo);
            long tagId = tagRepository.create(entity);
            return findById(tagId);
        }
        logger.warn("TagService, validation fail : " + tagTo.toString());
        return null;
    }

    @Override
    public TagTo update(TagTo tagTo) {
        if (Validator.validate(tagTo) && tagTo.getId() != null) {
            Tag entity = mapper.toEntity(tagTo);
            boolean isUpdate = tagRepository.update(entity);
            return isUpdate ? findById(tagTo.getId()) : null;
        }
        logger.warn("TagService, validation fail : " + tagTo.toString());
        return null;
    }

    @Override
    public boolean delete(long id) {
        if (Validator.validateId(id)) {
            return tagRepository.delete(id);
        }
        logger.warn("TagService, validation fail id: " + id);
        return false;
    }

    @Override
    public TagTo findById(long id) {
        if (Validator.validateId(id)) {
            Tag tag = tagRepository.findById(id);
            return mapper.toDto(tag);
        }
        logger.warn("TagService, validation fail id: " + id);
        return null;
    }

    @Override
    public List<TagTo> findAll() {
        List<Tag> allTags = tagRepository.findAll();
        return allTags.stream()
                .map(tag -> mapper.toDto(tag))
                .collect(Collectors.toList());
    }

    @Override
    public int countAll() {
        return tagRepository.countAll();
    }

    public List<TagTo> findTagsByNewsId(long newsId) {
        if (Validator.validateId(newsId)) {
            Specification specification = new FindTagsByNewsIdSpecification(newsId);
            List<Tag> tagsByNewsId = tagRepository.findBySpecification(specification);
            return tagsByNewsId.stream()
                    .map(tag -> mapper.toDto(tag))
                    .collect(Collectors.toList());
        }
        logger.warn("TagService, validation fail newsId: " + newsId);
        return Collections.<TagTo>emptyList();
    }

    public TagTo findTagByName(String tagName) {
        if (Validator.validateTagName(tagName)) {
            Specification specification = new FindTagByNameSpecification(tagName);
            List<Tag> result = tagRepository.findBySpecification(specification);
            return !result.isEmpty() ? mapper.toDto(result.get(0)) : null;
        }
        logger.warn("TagService, validation fail tagName: " + tagName);
        return null;
    }
}
