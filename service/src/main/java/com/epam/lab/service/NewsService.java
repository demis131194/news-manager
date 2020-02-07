package com.epam.lab.service;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.dto.NewsTo;
import com.epam.lab.dto.TagTo;
import com.epam.lab.model.News;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.specification.SearchCriteria;
import com.epam.lab.service.mapper.NewsMapper;
import com.epam.lab.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NewsService implements BaseService<NewsTo> {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private TagService tagService;

    @Autowired
    private NewsMapper mapper;

    @Override
    public NewsTo create(NewsTo newsTo) {
        if (Validator.validate(newsTo) && newsTo.getId() == null) {
            if (newsTo.getAuthor().getId() == null) {
                authorService.create(newsTo.getAuthor());
            }
            newsTo.getTags().forEach(tagTo -> {
                if (tagTo.getId() == null) {
                    long tagId = tagService.create(tagTo).getId();
                    tagTo.setId(tagId);
                }
            });
            long newsId = newsRepository.create(mapper.toEntity(newsTo));
            newsTo.setId(newsId);
            newsRepository.createNewsAuthorBound(newsId, newsTo.getAuthor().getId());
            newsTo.getTags().forEach(tagTo -> newsRepository.createNewsTagBound(newsId, tagTo.getId()));
            return newsTo;
        }
        return null;
    }

    @Override
    public NewsTo update(NewsTo newsTo) {
        if (Validator.validate(newsTo) && newsTo.getId() != null) {
            boolean isUpdateNews = newsRepository.update(mapper.toEntity(newsTo));
            if (isUpdateNews) {

                if (newsTo.getAuthor().getId() == null) {
                    AuthorTo createdAuthor = authorService.create(newsTo.getAuthor());
                    newsTo.setAuthor(createdAuthor);
                }

                newsTo.getTags().forEach(tagTo -> {
                    if (tagTo.getId() == null) {
                        long tagId = tagService.create(tagTo).getId();
                        tagTo.setId(tagId);
                    }
                });


                newsRepository.deleteNewsAuthorBound(newsTo.getId());
                newsRepository.createNewsAuthorBound(newsTo.getId(), newsTo.getAuthor().getId());

                newsRepository.deleteAllNewsTagBounds(newsTo.getId());
                newsTo.getTags().forEach(tagTo -> newsRepository.createNewsTagBound(newsTo.getId(), tagTo.getId()));

                return findById(newsTo.getId());
            }
        }
        return null;
    }

    @Override
    public boolean delete(long newsId) {
        if (Validator.validateId(newsId)) {
            return newsRepository.delete(newsId);
        }
        return false;
    }

    @Override
    public NewsTo findById(long newsId) {
        if (Validator.validateId(newsId)) {
            News newsEntity = newsRepository.findById(newsId);
            AuthorTo authorTo = authorService.findByNewsId(newsId);
            Set<TagTo> tags = tagService.findTagsByNewsId(newsId);
            NewsTo newsTo = mapper.toDto(newsEntity, authorTo, tags);
            return newsTo;
        }
        return null;
    }

    @Override
    public List<NewsTo> findAll() {
        List<News> all = newsRepository.findAll();
        List<NewsTo> allNewsTo = all.stream()
                .map(news -> findById(news.getId()))
                .collect(Collectors.toList());
        return allNewsTo;
    }

    @Override
    public int countAll() {
        return newsRepository.countAll();
    }

    public List<NewsTo> findAll(SearchCriteria searchCriteria) {       // FIXME: 2/6/2020 REFACTOR !!!

        List<News> all = newsRepository.findAll(searchCriteria);
//
//        if (searchCriteria.isAuthorSorting() && searchCriteria.isDateSorting()) {
//            allNewsTo = allNewsTo.stream()
//                    .sorted(Comparator.comparing(NewsTo::getAuthor)
//                            .thenComparing(NewsTo::getCreationDate))
//                    .collect(Collectors.toList());
//        } else if (searchCriteria.isAuthorSorting()) {
//            allNewsTo = allNewsTo.stream()
//                    .sorted(Comparator.comparing(NewsTo::getAuthor))
//                    .collect(Collectors.toList());
//        } else if (searchCriteria.isDateSorting()) {
//            allNewsTo = allNewsTo.stream()
//                    .sorted(Comparator.comparing(NewsTo::getCreationDate))
//                    .collect(Collectors.toList());
//        }
//
//        if (searchCriteria.getTagsFilterId() != null && searchCriteria.getTagsFilterId().size() > 0) {
//            allNewsTo = allNewsTo.stream()
//                    .filter(newsTo -> {
//                        List<Long> newsTagsId = newsTo.getTags()
//                                .stream()
//                                .map(TagTo::getId)
//                                .collect(Collectors.toList());
//                        return newsTagsId.containsAll(searchCriteria.getTagsFilterId());
//                    })
//                    .collect(Collectors.toList());
//        }
        return null;
    }
}
