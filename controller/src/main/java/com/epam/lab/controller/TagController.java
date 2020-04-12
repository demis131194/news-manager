package com.epam.lab.controller;

import com.epam.lab.dto.TagTo;
import com.epam.lab.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/tags")
@CrossOrigin("http://localhost:3000")
@Validated
public class TagController {

    private static final Logger logger = LoggerFactory.getLogger(TagController.class);

    private static final String WRONG_ID_MESSAGE = "Id must be greater than 0!";

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping(value = "/{id}")
    public TagTo getTagById(@PathVariable("id") @Positive(message = WRONG_ID_MESSAGE) long id) {
        logger.trace("Start TagController getTagById");
        logger.debug("Find tag by id - {}", id);
        TagTo result = tagService.findById(id);
        logger.trace("End TagController getTagById");
        return result;
    }

    @GetMapping
    public List<TagTo> getAllTags() {
        logger.trace("Start TagController getAllTags");
        List<TagTo> resultList = tagService.findAll();
        logger.trace("End TagController getTagById");
        return resultList;
    }

    @GetMapping(value = "/count")
    public Long getCountTags() {
        logger.trace("Start TagController count");
        long count = tagService.countAll();
        logger.trace("End TagController count");
        return count;
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('tags:post')")
    public TagTo postTag(@RequestBody @Validated TagTo tagTo) {
        logger.trace("Start TagController postTag");
        logger.debug("Post tagTo - {}", tagTo);
        TagTo savedTag = tagService.save(tagTo);
        logger.trace("End TagController postTag");
        return savedTag;
    }

    @PutMapping
//    @PreAuthorize("hasAuthority('tags:put')")
    public TagTo putTag(@RequestBody @Validated TagTo tagTo) {
        logger.trace("Start TagController putTag");
        logger.debug("Put tagTo - {}", tagTo);
        TagTo savedTag = tagService.save(tagTo);
        logger.trace("End TagController putTag");
        return savedTag;
    }

    @DeleteMapping(value = "/{id}")
//    @PreAuthorize("hasAuthority('tags:delete')")
    public boolean deleteTag(@PathVariable("id") @Positive(message = WRONG_ID_MESSAGE) long id) {
        logger.trace("Start TagController deleteTag");
        logger.debug("Delete tag, id - {}", id);
        boolean isDelete = tagService.delete(id);
        logger.trace("End TagController deleteTag");
        return isDelete;
    }
}
