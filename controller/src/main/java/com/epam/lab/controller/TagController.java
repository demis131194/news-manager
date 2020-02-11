package com.epam.lab.controller;

import com.epam.lab.dto.TagTo;
import com.epam.lab.service.TagServiceInterface;
import com.epam.lab.service.impl.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagServiceInterface tagService;

    @GetMapping(value = "/{id}")
    public @ResponseBody TagTo getTagById(@PathVariable("id") @Positive long id) {
        return tagService.findById(id);
    }

    @GetMapping
    public @ResponseBody List<TagTo> getAllTags() {
        return tagService.findAll();
    }

    @GetMapping(value = "/count")
    public @ResponseBody Integer getCountTags() {
        return tagService.countAll();
    }

    @PostMapping(value = "/post")
    public @ResponseBody TagTo postTag(@RequestBody @Valid TagTo tagTo) {
        return tagService.create(tagTo);
    }

    @PutMapping(value = "/put")
    public @ResponseBody TagTo putTag(@RequestBody @Valid TagTo tagTo) {
        return tagService.update(tagTo);
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean deleteTag(@PathVariable("id") @Positive long id) {
        return tagService.delete(id);
    }

}
