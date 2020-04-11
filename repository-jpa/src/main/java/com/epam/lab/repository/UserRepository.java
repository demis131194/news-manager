package com.epam.lab.repository;

import com.epam.lab.model.User;

import java.util.Optional;

public interface UserRepository extends  BaseSpecificationRepository<User> {
    Optional<User> findByLogin(String login);
}
