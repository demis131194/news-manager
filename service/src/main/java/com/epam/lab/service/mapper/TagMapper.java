package com.epam.lab.service.mapper;

import com.epam.lab.dto.AuthorTo;
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
        return Objects.isNull(tagTo) ? null : modelMapper.map(tagTo, Tag.class);
    }

    public TagTo toDto(Tag tag) {
        return Objects.isNull(tag) ? null : modelMapper.map(tag, TagTo.class);
    }
}
