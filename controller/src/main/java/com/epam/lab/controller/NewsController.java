package com.epam.lab.controller;

import com.epam.lab.dto.NewsTo;
import com.epam.lab.repository.specification.news.SearchCriteria;
import com.epam.lab.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(value = "/news")
@CrossOrigin("http://localhost:3000")
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
    public NewsResponseEntity getAllNews(SearchCriteria searchCriteria,
                                         @RequestParam(required = false, defaultValue = "1") @Positive int page,
                                         @RequestParam(required = false, defaultValue = "4") @Positive int pageSize) {
        List<NewsTo> allBySearchCriteria = newsService.findAllBySearchCriteria(searchCriteria, page, pageSize);
        long countAll = newsService.countAll(searchCriteria);

        return new NewsResponseEntity(allBySearchCriteria, countAll);
    }

    @GetMapping(value = "/count")
    public Long getCountNews() {
        return newsService.countAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public NewsTo postNews(@RequestBody @Validated NewsTo newsTo) {
        return newsService.save(newsTo);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public NewsTo putNews(@RequestBody @Validated NewsTo newsTo) {
        return newsService.save(newsTo);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteNews(@PathVariable("id") @Positive(message = WRONG_ID_MESSAGE) long id) {
        return newsService.delete(id);
    }

    private static class NewsResponseEntity {
        private List<NewsTo> news;
        private Long totalCount;

        public NewsResponseEntity(List<NewsTo> news, Long totalCount) {
            this.news = news;
            this.totalCount = totalCount;
        }

        public List<NewsTo> getNews() {
            return news;
        }

        public Long getTotalCount() {
            return totalCount;
        }
    }
}
