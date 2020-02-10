package com.epam.lab.controller;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(value = "/{id}")
    public @ResponseBody AuthorTo getAuthorById(@PathVariable("id") Long id) {
        AuthorTo authorTo = authorService.findById(id);
        return authorTo;
    }

    @GetMapping
    public @ResponseBody List<AuthorTo> getAllAuthors() {
        List<AuthorTo> all = authorService.findAll();
        return all;
    }

    @GetMapping(value = "/count")
    public @ResponseBody Integer getCountAuthors() {
        return authorService.countAll();
    }

    @PostMapping(value = "/post")
    public @ResponseBody AuthorTo postAuthor(@RequestBody AuthorTo authorTo) {
        return authorService.create(authorTo);
    }

    @PutMapping(value = "/put")
    public @ResponseBody AuthorTo putAuthor(@RequestBody AuthorTo authorTo) {
        return authorService.update(authorTo);
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean deleteAuthor(@PathVariable("id") long id) {
        return authorService.delete(id);
    }
}
