package com.epam.lab.controller;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.dto.NewsTo;
import com.epam.lab.dto.TagTo;
import com.epam.lab.service.AuthorService;
import com.epam.lab.service.NewsService;
import com.epam.lab.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/manager")
public class MainController {

    @Autowired
    private TagService tagService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private NewsService newsService;

    @GetMapping(value = "/tags/{id}")
    public @ResponseBody TagTo getTagById(@PathVariable("id") Long id) {
        TagTo tag = tagService.findById(id);
        return tag;
    }

    @GetMapping(value = "/tags")
    public @ResponseBody Set<TagTo> getAllTags() {
        Set<TagTo> all = tagService.findAll();
        return all;
    }

    @GetMapping(value = "/tags/count")
    public @ResponseBody Integer getCountTags() {
        return tagService.countAll();
    }

    @GetMapping(value = "/authors/{id}")
    public @ResponseBody AuthorTo getAuthorById(@PathVariable("id") Long id) {
        AuthorTo authorTo = authorService.findById(id);
        return authorTo;
    }

    @GetMapping(value = "/authors")
    public @ResponseBody List<AuthorTo> getAllAuthors() {
        List<AuthorTo> all = authorService.findAll();
        return all;
    }

    @GetMapping(value = "/authors/count")
    public @ResponseBody Integer getCountAuthors() {
        return authorService.countAll();
    }

    @GetMapping(value = "/news/{id}")
    public @ResponseBody NewsTo getNewsById(@PathVariable("id") Long id) {
        NewsTo newsTo = newsService.findById(id);
        return newsTo;
    }

    @GetMapping(value = "/news")
    public @ResponseBody List<NewsTo> getAllNews() {
        List<NewsTo> all = newsService.findAll();
        return all;
    }

    @GetMapping(value = "/news/count")
    public @ResponseBody Integer getCountNews() {
        return newsService.countAll();
    }

    @PostMapping(value = "/tags/post")
    public @ResponseBody TagTo postTag(@RequestBody TagTo tagTo) {
        return tagService.create(tagTo);
    }

    @PostMapping(value = "/authors/post")
    public @ResponseBody AuthorTo postAuthor(@RequestBody AuthorTo authorTo) {
        return authorService.create(authorTo);
    }

    @PostMapping(value = "/news/post")
    public @ResponseBody NewsTo postNews(@RequestBody NewsTo newsTo) {
        return newsService.create(newsTo);
    }

    @PutMapping(value = "/tags/put")
    public @ResponseBody TagTo putTag(@RequestBody TagTo tagTo) {
        return tagService.update(tagTo);
    }

    @PutMapping(value = "/authors/put")
    public @ResponseBody AuthorTo putAuthor(@RequestBody AuthorTo authorTo) {
        return authorService.update(authorTo);
    }

    @PutMapping(value = "/news/put")
    public @ResponseBody NewsTo putTag(@RequestBody NewsTo newsTo) {
        return newsService.update(newsTo);
    }

    @DeleteMapping(value = "/tags/delete/{id}")
    public boolean deleteTag(@PathVariable("id") long id) {
        return tagService.delete(id);
    }

    @DeleteMapping(value = "/authors/delete/{id}")
    public boolean deleteAuthor(@PathVariable("id") long id) {
        return authorService.delete(id);
    }

    @DeleteMapping(value = "/news/delete/{id}")
    public boolean deleteNews(@PathVariable("id") long id) {
        return newsService.delete(id);
    }


//    @ModelAttribute

}
