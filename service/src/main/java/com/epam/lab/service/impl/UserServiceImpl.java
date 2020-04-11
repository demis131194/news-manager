package com.epam.lab.service.impl;

import com.epam.lab.dto.UserTo;
import com.epam.lab.exeption.ServiceException;
import com.epam.lab.model.User;
import com.epam.lab.repository.UserRepository;
import com.epam.lab.service.UserService;
import com.epam.lab.service.impl.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserTo save(UserTo userTo) {
        User entity = mapper.toEntity(userTo);
        User savedUser = userRepository.save(entity);
        if (savedUser == null) {
            throw new ServiceException("Can't update user, wrong id - " + userTo.getId());
        }
        return mapper.toDto(savedUser);
    }

    @Override
    public boolean delete(long id) {
        return userRepository.delete(id);
    }

    @Override
    public UserTo findById(long userId) {
        User userById = userRepository.findById(userId);
        if (userById == null) {
            throw new ServiceException("User not found by user id = " + userId);
        }
        return mapper.toDto(userById);
    }

    @Override
    public List<UserTo> findAll() {
        List<User> allAuthors = userRepository.findAll();
        return convertToAuthorTo(allAuthors);
    }

    @Override
    public long countAll() {
        return userRepository.countAll();
    }

    @Override
    public UserTo findByLogin(String login) {
        Optional<User> byLogin = userRepository.findByLogin(login);
        User user = byLogin.orElseThrow(() -> new ServiceException("User not found by login - " + login));
        return mapper.toDto(user);
    }

    private List<UserTo> convertToAuthorTo(List<User> allUsers) {
        return allUsers.stream()
                .map(user -> mapper.toDto(user))
                .collect(Collectors.toList());
    }
}
