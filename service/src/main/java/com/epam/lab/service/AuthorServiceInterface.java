package com.epam.lab.service;

import com.epam.lab.dto.AuthorTo;

public interface AuthorServiceInterface extends BaseService<AuthorTo> {
    AuthorTo findByNewsId(long id);
}
