package com.epam.lab.service;

import com.epam.lab.dto.TagTo;

import java.util.List;

public interface TagService extends BaseService<TagTo> {
    List<TagTo> findTagsByNewsId(long newsId);
    TagTo findTagByName(String tagName);
}
