package com.epam.lab.controller;

import com.epam.lab.dto.TagTo;
import com.epam.lab.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news-manager")
public class MainController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody TagTo getTagById(@PathVariable("id") Long id) {
        TagTo tag = tagService.findById(id);
        return tag;
    }

//    @ModelAttribute

}
