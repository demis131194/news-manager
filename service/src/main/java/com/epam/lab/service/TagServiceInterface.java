package com.epam.lab.service;

import com.epam.lab.dto.TagTo;

import java.util.List;

public interface TagServiceInterface extends BaseService<TagTo> {
    List<TagTo> findTagsByNewsId(long newsId);
    TagTo findTagByName(String tagName);
}
