package com.epam.lab.controller;

import com.epam.lab.dto.TagTo;
import com.epam.lab.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping(value = "/{id}")
    public @ResponseBody TagTo getTagById(@PathVariable("id") Long id) {
        TagTo tag = tagService.findById(id);
        return tag;
    }

    @GetMapping
    public @ResponseBody List<TagTo> getAllTags() {
        List<TagTo> all = tagService.findAll();
        return all;
    }

    @GetMapping(value = "/count")
    public @ResponseBody Integer getCountTags() {
        return tagService.countAll();
    }

    @PostMapping(value = "/post")
    public @ResponseBody TagTo postTag(@RequestBody TagTo tagTo) {
        return tagService.create(tagTo);
    }

    @PutMapping(value = "/put")
    public @ResponseBody TagTo putTag(@RequestBody TagTo tagTo) {
        return tagService.update(tagTo);
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean deleteTag(@PathVariable("id") long id) {
        return tagService.delete(id);
    }

}
