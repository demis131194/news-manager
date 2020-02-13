package com.epam.lab.service.impl;

import com.epam.lab.dto.TagTo;
import com.epam.lab.exeption.ServiceException;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.repository.jdbc.TagRepositoryImpl;
import com.epam.lab.repository.jdbc.specification.Specification;
import com.epam.lab.repository.jdbc.specification.tag.FindTagByNameSpecification;
import com.epam.lab.repository.jdbc.specification.tag.FindTagsByNewsIdSpecification;
import com.epam.lab.service.TagService;
import com.epam.lab.service.impl.mapper.TagMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Tag service.
 */
@Service
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {
    private static final Logger logger = LogManager.getLogger(AuthorServiceImpl.class);

    private TagRepository tagRepository;
    private TagMapper mapper;

    /**
     * Instantiates a new Tag service.
     *
     * @param tagRepository the tag repository
     * @param mapper        the mapper
     */
    @Autowired
    public TagServiceImpl(TagRepositoryImpl tagRepository, TagMapper mapper) {
        this.tagRepository = tagRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public TagTo create(TagTo tagTo) {
        if (tagTo.getId() == null) {
            Tag entity = mapper.toEntity(tagTo);
            long tagId = tagRepository.create(entity);
            return findById(tagId);
        }
        throw new ServiceException("Create tag, need id == null!");
    }

    @Override
    @Transactional
    public TagTo update(TagTo tagTo) {
        if (tagTo.getId() != null) {
            Tag entity = mapper.toEntity(tagTo);
            boolean isUpdate = tagRepository.update(entity);
            return isUpdate ? findById(tagTo.getId()) : null;
        }
        throw new ServiceException("Update tag, need id != null!");
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        if (id > 0) {
            return tagRepository.delete(id);
        }
        throw new ServiceException("Delete tag, need id > 0!");
    }

    @Override
    public TagTo findById(long id) {
        if (id > 0) {
            Tag tag = tagRepository.findById(id);
            return mapper.toDto(tag);
        }
        throw new ServiceException("Find tag by id, need id > 0!");
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

    /**
     * Find tags by news id list.
     *
     * @param newsId the news id
     * @return the list
     */
    @Override
    public List<TagTo> findTagsByNewsId(long newsId) {
        if (newsId > 0) {
            Specification specification = new FindTagsByNewsIdSpecification(newsId);
            List<Tag> tagsByNewsId = tagRepository.findBySpecification(specification);
            return tagsByNewsId.stream()
                    .map(tag -> mapper.toDto(tag))
                    .collect(Collectors.toList());
        }
        throw new ServiceException("Find tag by news id, need id > 0!");
    }

    /**
     * Find tag by name tag to.
     *
     * @param tagName the tag name
     * @return the tag to
     */
    @Override
    public TagTo findTagByName(String tagName) {
        Specification specification = new FindTagByNameSpecification(tagName);
        List<Tag> result = tagRepository.findBySpecification(specification);
        return !result.isEmpty() ? mapper.toDto(result.get(0)) : null;
    }
}
