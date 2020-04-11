package com.epam.lab.service.impl.mapper;

import com.epam.lab.dto.UserTo;
import com.epam.lab.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper {

    private ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User toEntity(UserTo userTo) {
        Objects.requireNonNull(userTo);
        return modelMapper.map(userTo, User.class);
    }

    public UserTo toDto(User user) {
        Objects.requireNonNull(user);
        return modelMapper.map(user, UserTo.class);
    }
}
