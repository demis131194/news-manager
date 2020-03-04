package com.epam.lab.service.impl;

import com.epam.lab.dto.TagTo;
import com.epam.lab.exeption.ServiceException;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.repository.specification.JpaSpecification;
import com.epam.lab.repository.specification.tag.FindTagByNameJpaSpecification;
import com.epam.lab.service.TagService;
import com.epam.lab.service.impl.mapper.TagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private static final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

    private TagRepository tagRepository;
    private TagMapper mapper;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagMapper mapper) {
        this.tagRepository = tagRepository;
        this.mapper = mapper;
    }

    @Override
    public TagTo save(TagTo tagTo) {
        logger.trace("Start TagServiceImpl save");
        Tag entity = mapper.toEntity(tagTo);
        logger.debug("TagTo mapped to entity - {}", entity);
        Tag savedTag = tagRepository.save(entity);
        TagTo result = mapper.toDto(savedTag);
        logger.trace("End TagServiceImpl save");
        return result;
    }

    @Override
    public boolean delete(long id) {
        logger.trace("Start TagServiceImpl delete");
        boolean isDelete = tagRepository.delete(id);
        logger.trace("End TagServiceImpl delete");
        return isDelete;
    }

    @Override
    public TagTo findById(long tagId) {
        logger.trace("Start TagServiceImpl findById");
        Tag tagById = tagRepository.findById(tagId);
        if (tagById == null) {
            throw new ServiceException("Tag not found, tag id = " + tagId);
        }
        TagTo result = mapper.toDto(tagById);
        logger.debug("Find TagTo - {}", result);
        logger.trace("End TagServiceImpl findById");
        return result;
    }

    @Override
    public List<TagTo> findAll() {
        logger.trace("Start TagServiceImpl findAll");
        List<Tag> allTags = tagRepository.findAll();
        List<TagTo> resultList = convertToTagTo(allTags);
        logger.info("List of TagTo - {}", resultList);
        logger.trace("End TagServiceImpl findAll");
        return resultList;
    }

    @Override
    public long countAll() {
        logger.trace("Start TagServiceImpl countAll");
        long count = tagRepository.countAll();
        logger.trace("End TagServiceImpl countAll");
        return count;
    }

    @Override
    public TagTo findTagByName(String tagName) {
        logger.trace("Start TagServiceImpl findTagByName");
        JpaSpecification<Tag> specification = new FindTagByNameJpaSpecification(tagName);
        List<Tag> result = tagRepository.findAllBySpecification(specification);
        if (result.isEmpty()) {
            throw new ServiceException("Tag not found, tag name - " + tagName);
        }
        TagTo resultTag = mapper.toDto(result.get(0));
        logger.info("Find TagTo - {}", resultTag);
        logger.trace("End TagServiceImpl findTagByName");
        return resultTag;
    }

    private List<TagTo> convertToTagTo(List<Tag> tagsByNewsId) {
        return tagsByNewsId.stream()
                .map(tag -> mapper.toDto(tag))
                .collect(Collectors.toList());
    }
}
