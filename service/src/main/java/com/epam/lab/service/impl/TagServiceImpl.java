package com.epam.lab.service.impl;

import com.epam.lab.dto.TagTo;
import com.epam.lab.exeption.ServiceException;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.repository.specification.JpaSpecification;
import com.epam.lab.repository.specification.tag.FindTagByNameJpaSpecification;
import com.epam.lab.service.TagService;
import com.epam.lab.service.impl.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;
    private TagMapper mapper;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagMapper mapper) {
        this.tagRepository = tagRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public TagTo save(TagTo tagTo) {
        Tag entity = mapper.toEntity(tagTo);
        Tag savedTag = tagRepository.save(entity);
        return mapper.toDto(savedTag);
    }

    @Override
    public boolean delete(long id) {
        if (id > 0) {
            return tagRepository.delete(id);
        }
        throw new ServiceException("Delete tag, rag id should be > 0!");
    }

    @Override
    public TagTo findById(long tagId) {
        if (tagId > 0) {
            Tag tagById = tagRepository.findById(tagId);
            if (tagById == null) {
                throw new ServiceException("Tag not found, tag id = " + tagId);
            }
            return mapper.toDto(tagById);
        }
        throw new ServiceException("Tag not found, tag id should be > 0!");
    }

    @Override
    public List<TagTo> findAll() {
        List<Tag> allTags = tagRepository.findAll();
        return convertToTagTo(allTags);
    }

    @Override
    public long countAll() {
        return tagRepository.countAll();
    }

    @Override
    public TagTo findTagByName(String tagName) {
        JpaSpecification<Tag> specification = new FindTagByNameJpaSpecification(tagName);
        List<Tag> result = tagRepository.findAllBySpecification(specification);
        if (result.isEmpty()) {
            throw new ServiceException("Tag not found, tag name - " + tagName);
        }
        return mapper.toDto(result.get(0));
    }

    private List<TagTo> convertToTagTo(List<Tag> tagsByNewsId) {
        return tagsByNewsId.stream()
                .map(tag -> mapper.toDto(tag))
                .collect(Collectors.toList());
    }
}
