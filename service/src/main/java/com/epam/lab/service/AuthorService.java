package com.epam.lab.service;

import com.epam.lab.dto.AuthorTo;

public interface AuthorService extends BaseService<AuthorTo> {
    AuthorTo findByNewsId(long id);
}
