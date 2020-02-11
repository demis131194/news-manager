package com.epam.lab.controller;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(value = "/{id}")
    public @ResponseBody AuthorTo getAuthorById(@PathVariable("id") @Positive long id) {
        return authorService.findById(id);
    }

    @GetMapping
    public @ResponseBody List<AuthorTo> getAllAuthors() {
        return authorService.findAll();
    }

    @GetMapping(value = "/count")
    public @ResponseBody Integer getCountAuthors() {
        return authorService.countAll();
    }

    @PostMapping(value = "/post")
    public @ResponseBody AuthorTo postAuthor(@RequestBody @Valid AuthorTo authorTo) {
        return authorService.create(authorTo);
    }

    @PutMapping(value = "/put")
    public @ResponseBody AuthorTo putAuthor(@RequestBody @Valid AuthorTo authorTo) {
        return authorService.update(authorTo);
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean deleteAuthor(@PathVariable("id") @Positive long id) {
        return authorService.delete(id);
    }
}
