package com.epam.lab.service;

import com.epam.lab.dto.UserTo;

public interface UserService extends BaseService<UserTo> {
    UserTo findByLogin(String login);
}
