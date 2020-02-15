package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.dto.NewsTo;
import com.epam.lab.dto.TagTo;
import com.epam.lab.exeption.ServiceException;
import com.epam.lab.model.News;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.jdbc.specification.Specification;
import com.epam.lab.repository.jdbc.specification.news.FindAllNewsSpecification;
import com.epam.lab.repository.jdbc.specification.news.FindNewsByIdSpecification;
import com.epam.lab.repository.jdbc.specification.news.FindNewsBySearchCriteriaSpecification;
import com.epam.lab.repository.jdbc.specification.news.SearchCriteria;
import com.epam.lab.service.AuthorService;
import com.epam.lab.service.NewsService;
import com.epam.lab.service.TagService;
import com.epam.lab.service.impl.mapper.NewsMapper;
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

    private static final FindAllNewsSpecification FIND_ALL_NEWS_SPECIFICATION = new FindAllNewsSpecification();

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
        if (newsTo.getId() == null) {
            createAuthorIfAbsent(newsTo);
            createTagsIfAbsent(newsTo);
            long newsId = newsRepository.create(mapper.toEntity(newsTo));
            newsRepository.createNewsAuthorBound(newsId, newsTo.getAuthor().getId());
            newsTo.getTags().forEach(tagTo -> newsRepository.createNewsTagBound(newsId, tagTo.getId()));
            return findById(newsId);
        }
        throw new ServiceException("Create news, news id should be null!");
    }

    @Override
    @Transactional
    public NewsTo update(NewsTo newsTo) {
        if (newsTo.getId() != null) {
            boolean isUpdateNews = newsRepository.update(mapper.toEntity(newsTo));
            if (isUpdateNews) {
                createAuthorIfAbsent(newsTo);
                createTagsIfAbsent(newsTo);
                newsRepository.updateNewsAuthorBound(newsTo.getId(), newsTo.getAuthor().getId());
                newsRepository.deleteAllNewsTagBounds(newsTo.getId());
                newsTo.getTags().forEach(tagTo -> newsRepository.createNewsTagBound(newsTo.getId(), tagTo.getId()));
                return findById(newsTo.getId());
            }
            throw new ServiceException("Can't update news, news id = " + newsTo.getId());
        }
        throw new ServiceException("Update news, news id shouldn't be null!");
    }

    @Override
    public boolean delete(long newsId) {
        if (newsId > 0) {
            return newsRepository.delete(newsId);
        }
        throw new ServiceException("Delete news, id should be > 0!");
    }

    @Override
    @Transactional
    public NewsTo findById(long newsId) {
        if (newsId > 0) {
            Specification specification = new FindNewsByIdSpecification(newsId);
            List<News> news = newsRepository.findBySpecification(specification);
            if (news.isEmpty()) {
                throw new ServiceException("Not fund news with id = " + newsId);
            }
            News newsEntity = news.get(0);
            AuthorTo authorTo = authorService.findByNewsId(newsId);
            List<TagTo> tags = tagService.findTagsByNewsId(newsId);
            return mapper.toDto(newsEntity, authorTo, tags);
        }
        throw new ServiceException("Find news by id, news id should be > 0!");
    }

    @Override
    @Transactional
    public List<NewsTo> findAll() {
        return findAllNewsToBySpecification(FIND_ALL_NEWS_SPECIFICATION);
    }

    /**
     * Find all list.
     *
     * @param searchCriteria the search criteria
     * @return the list
     */
    @Transactional
    @Override
    public List<NewsTo> findAllBySearchCriteria(SearchCriteria searchCriteria) {
        Specification specification = new FindNewsBySearchCriteriaSpecification(searchCriteria);
        return findAllNewsToBySpecification(specification);
    }

    @Override
    public long countAll() {
        return newsRepository.countAll();
    }

    private List<NewsTo> findAllNewsToBySpecification(Specification findAllNewsSpecification) {
        List<News> all = newsRepository.findBySpecification(findAllNewsSpecification);
        return convertToNewsTo(all);
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

    private List<NewsTo> convertToNewsTo(List<News> all) {
        return all.stream()
                .map(news -> {
                    AuthorTo author = authorService.findByNewsId(news.getId());
                    List<TagTo> tags = tagService.findTagsByNewsId(news.getId());
                    return mapper.toDto(news, author, tags);
                })
                .collect(Collectors.toList());
    }
}
