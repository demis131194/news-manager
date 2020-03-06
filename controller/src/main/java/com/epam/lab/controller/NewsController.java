package com.epam.lab.controller;

import com.epam.lab.dto.NewsTo;
import com.epam.lab.dto.group.CreateGroup;
import com.epam.lab.dto.group.UpdateGroup;
import com.epam.lab.repository.specification.news.SearchCriteria;
import com.epam.lab.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/news")
@Validated
public class NewsController {

    private static final String WRONG_ID_MESSAGE = "Id must be greater than 0!";

    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping(value = "/{id}")
    public NewsTo getNewsById(@PathVariable("id") @Positive(message = WRONG_ID_MESSAGE) long id) {
        return newsService.findById(id);
    }

    @GetMapping
    public List<NewsTo> getAllNews(SearchCriteria searchCriteria) {
        return newsService.findAllBySearchCriteria(searchCriteria);
    }

    @GetMapping(value = "/count")
    public Long getCountNews() {
        return newsService.countAll();
    }

    @PostMapping
    public NewsTo postNews(@RequestBody @Validated(CreateGroup.class) NewsTo newsTo) {
        return newsService.save(newsTo);
    }

    @PutMapping
    public NewsTo putTag(@RequestBody @Validated(UpdateGroup.class) NewsTo newsTo) {
        return newsService.save(newsTo);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteNews(@PathVariable("id") @Positive(message = WRONG_ID_MESSAGE) long id) {
        return newsService.delete(id);
    }
}
