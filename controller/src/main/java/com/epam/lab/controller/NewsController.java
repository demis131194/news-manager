package com.epam.lab.controller;

import com.epam.lab.dto.NewsTo;
import com.epam.lab.repository.specification.news.SearchCriteria;
import com.epam.lab.service.NewsServiceInterface;
import com.epam.lab.service.impl.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsServiceInterface newsService;

    @GetMapping(value = "/{id}")
    public @ResponseBody NewsTo getNewsById(@PathVariable("id") @Positive long id) {
        return newsService.findById(id);
    }

    @GetMapping
    public @ResponseBody List<NewsTo> getAllNews(@RequestParam(name = "sortAuthor", defaultValue = "false") boolean sortAuthor,
                                                 @RequestParam(name = "sortDate", defaultValue = "false") boolean sortDate,
                                                 @RequestParam(name = "tagId", required = false) List<Long> tagsId,
                                                 @RequestParam(name = "authorId", required = false) Long authorId) {

        SearchCriteria searchCriteria = new SearchCriteria(authorId, tagsId, sortAuthor, sortDate);
        return newsService.findAll(searchCriteria);
    }

    @GetMapping(value = "/count")
    public @ResponseBody Integer getCountNews() {
        return newsService.countAll();
    }

    @PostMapping(value = "/post")
    public @ResponseBody NewsTo postNews(@RequestBody @Valid NewsTo newsTo) {
        return newsService.create(newsTo);
    }

    @PutMapping(value = "/put")
    public @ResponseBody NewsTo putTag(@RequestBody @Valid NewsTo newsTo) {
        return newsService.update(newsTo);
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean deleteNews(@PathVariable("id") @Positive long id) {
        return newsService.delete(id);
    }
}
