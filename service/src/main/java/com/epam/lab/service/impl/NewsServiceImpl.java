package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.dto.NewsTo;
import com.epam.lab.dto.TagTo;
import com.epam.lab.model.News;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.specification.Specification;
import com.epam.lab.repository.specification.news.FindNewsBySearchCriteriaSpecification;
import com.epam.lab.repository.specification.news.SearchCriteria;
import com.epam.lab.service.AuthorService;
import com.epam.lab.service.NewsService;
import com.epam.lab.service.TagService;
import com.epam.lab.service.impl.mapper.NewsMapper;
import com.epam.lab.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type News service.
 */
@Service
@Transactional(readOnly = true)
public class NewsServiceImpl implements NewsService {
    private static final Logger logger = LogManager.getLogger(AuthorServiceImpl.class);

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private TagService tagService;

    @Autowired
    private NewsMapper mapper;

    @Override
    @Transactional
    public NewsTo create(NewsTo newsTo) {
        if (Validator.validate(newsTo) && newsTo.getId() == null) {
            createAuthorIfAbsent(newsTo);
            createTagsIfAbsent(newsTo);
            long newsId = newsRepository.create(mapper.toEntity(newsTo));
            newsRepository.createNewsAuthorBound(newsId, newsTo.getAuthor().getId());
            newsTo.getTags().forEach(tagTo -> newsRepository.createNewsTagBound(newsId, tagTo.getId()));
            return findById(newsId);
        }
        logger.warn("NewsService, validation fail : " + newsTo.toString());
        return null;
    }

    @Override
    @Transactional
    public NewsTo update(NewsTo newsTo) {
        if (Validator.validate(newsTo) && newsTo.getId() != null) {
            boolean isUpdateNews = newsRepository.update(mapper.toEntity(newsTo));
            if (isUpdateNews) {
                createAuthorIfAbsent(newsTo);
                createTagsIfAbsent(newsTo);
                newsRepository.updateNewsAuthorBound(newsTo.getId(), newsTo.getAuthor().getId());
                newsRepository.deleteAllNewsTagBounds(newsTo.getId());
                newsTo.getTags().forEach(tagTo -> newsRepository.createNewsTagBound(newsTo.getId(), tagTo.getId()));
                return findById(newsTo.getId());
            }
        }
        logger.warn("NewsService, validation fail : " + newsTo.toString());
        return null;
    }

    @Override
    @Transactional
    public boolean delete(long newsId) {
        if (Validator.validateId(newsId)) {
            return newsRepository.delete(newsId);
        }
        logger.warn("NewsService, validation fail newsId: " + newsId);
        return false;
    }

    @Override
    @Transactional
    public NewsTo findById(long newsId) {
        if (Validator.validateId(newsId)) {
            News newsEntity = newsRepository.findById(newsId);
            AuthorTo authorTo = authorService.findByNewsId(newsId);
            List<TagTo> tags = tagService.findTagsByNewsId(newsId);
            return mapper.toDto(newsEntity, authorTo, tags);
        }
        logger.warn("NewsService, validation fail newsId: " + newsId);
        return null;
    }

    @Override
    @Transactional
    public List<NewsTo> findAll() {
        List<News> all = newsRepository.findAll();
        return all.stream()
                .map(news -> findById(news.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public int countAll() {
        return newsRepository.countAll();
    }

    /**
     * Find all list.
     *
     * @param searchCriteria the search criteria
     * @return the list
     */
    @Transactional
    @Override
    public List<NewsTo> findAll(SearchCriteria searchCriteria) {
        Specification specification = new FindNewsBySearchCriteriaSpecification(searchCriteria);
        List<News> allNews = newsRepository.findBySpecification(specification);
        return allNews.stream()
                .map(news -> {
                    AuthorTo author = authorService.findByNewsId(news.getId());
                    List<TagTo> tags = tagService.findTagsByNewsId(news.getId());
                    return mapper.toDto(news, author, tags);
                })
                .collect(Collectors.toList());
    }

    private void createAuthorIfAbsent(NewsTo newsTo) {
        if (newsTo.getAuthor().getId() == null) {
            AuthorTo createdAuthor = authorService.create(newsTo.getAuthor());
            newsTo.setAuthor(createdAuthor);
        }
    }

    private void createTagsIfAbsent(NewsTo newsTo) {
        newsTo.getTags().forEach(tagTo -> {
            if (tagTo.getId() == null) {
                TagTo tagByName = tagService.findTagByName(tagTo.getName());
                long tagId = tagByName == null ? tagService.create(tagTo).getId() : tagByName.getId();
                tagTo.setId(tagId);
            }
        });
    }
}
