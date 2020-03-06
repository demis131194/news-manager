package com.epam.lab.controller;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.dto.group.CreateGroup;
import com.epam.lab.dto.group.UpdateGroup;
import com.epam.lab.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/authors")
@Validated
public class AuthorController {

    private static final String WRONG_ID_MESSAGE = "Id must be greater than 0!";

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/{id}")
    public AuthorTo getAuthorById(@PathVariable("id") @Positive(message = WRONG_ID_MESSAGE) long id) {
        return authorService.findById(id);
    }

    @GetMapping
    public List<AuthorTo> getAllAuthors() {
        return authorService.findAll();
    }

    @GetMapping(value = "/count")
    public Long getCountAuthors() {
        return authorService.countAll();
    }

    @PostMapping
    public AuthorTo postAuthor(@RequestBody @Validated(CreateGroup.class) AuthorTo authorTo) {
        return authorService.save(authorTo);
    }

    @PutMapping
    public AuthorTo putAuthor(@RequestBody @Validated(UpdateGroup.class) AuthorTo authorTo) {
        return authorService.save(authorTo);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteAuthor(@PathVariable("id") @Positive(message = WRONG_ID_MESSAGE) long id) {
        return authorService.delete(id);
    }
}
