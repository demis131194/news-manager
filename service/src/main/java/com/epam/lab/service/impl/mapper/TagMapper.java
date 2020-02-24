package com.epam.lab.service.impl.mapper;

import com.epam.lab.dto.TagTo;
import com.epam.lab.model.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TagMapper {

    private ModelMapper modelMapper;

    @Autowired
    public TagMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Tag toEntity(TagTo tagTo) {
        Objects.requireNonNull(tagTo);
        return modelMapper.map(tagTo, Tag.class);
    }

    public TagTo toDto(Tag tag) {
        Objects.requireNonNull(tag);
        return modelMapper.map(tag, TagTo.class);
    }
}
