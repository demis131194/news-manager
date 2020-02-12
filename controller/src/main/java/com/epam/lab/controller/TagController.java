package com.epam.lab.controller;

import com.epam.lab.dto.TagTo;
import com.epam.lab.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

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

    @PostMapping
    public @ResponseBody TagTo postTag(@RequestBody @Valid TagTo tagTo) {
        return tagService.create(tagTo);
    }

    @PutMapping
    public @ResponseBody TagTo putTag(@RequestBody @Valid TagTo tagTo) {
        return tagService.update(tagTo);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteTag(@PathVariable("id") @Positive long id) {
        return tagService.delete(id);
    }

}
