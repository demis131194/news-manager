package com.epam.lab.service;

import com.epam.lab.dto.TagTo;

public interface TagService extends BaseService<TagTo> {
    TagTo findTagByName(String name);
}
