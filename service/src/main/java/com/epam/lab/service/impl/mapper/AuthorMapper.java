package com.epam.lab.service.impl.mapper;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.model.Author;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthorMapper {
    private ModelMapper modelMapper;

    @Autowired
    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Author toEntity(AuthorTo authorTo) {
        return Objects.isNull(authorTo) ? null : modelMapper.map(authorTo, Author.class);
    }

    public AuthorTo toDto(Author author) {
        return Objects.isNull(author) ? null : modelMapper.map(author, AuthorTo.class);
    }
}
