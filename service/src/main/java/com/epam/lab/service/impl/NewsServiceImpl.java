package com.epam.lab.service.impl;

import com.epam.lab.dto.NewsTo;
import com.epam.lab.exeption.ServiceException;
import com.epam.lab.model.News;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.specification.JpaSpecification;
import com.epam.lab.repository.specification.news.FindNewsBySearchCriteriaJpaSpecification;
import com.epam.lab.repository.specification.news.SearchCriteria;
import com.epam.lab.service.NewsService;
import com.epam.lab.service.impl.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsRepository newsRepository;
    private NewsMapper mapper;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, NewsMapper mapper) {
        this.newsRepository = newsRepository;
        this.mapper = mapper;
    }

    @Override
    public NewsTo save(NewsTo newsTo) {
        News entity = mapper.toEntity(newsTo);
        News savedNews = newsRepository.save(entity);
        if (savedNews == null) {
            throw new ServiceException("Can't update news, wrong id - " + newsTo.getId());
        }
        return mapper.toDto(savedNews);
    }

    @Override
    public boolean delete(long newsId) {
        return newsRepository.delete(newsId);
    }

    @Override
    public NewsTo findById(long newsId) {
        News newsById = newsRepository.findById(newsId);
        if (newsById == null) {
            throw new ServiceException("Not fund newsById with id = " + newsId);
        }
        return mapper.toDto(newsById);
    }

    @Override
    public List<NewsTo> findAll() {
        List<News> allNewsEntity = newsRepository.findAll();
        return convertToNewsTo(allNewsEntity);
    }

    @Override
    public List<NewsTo> findAllBySearchCriteria(SearchCriteria searchCriteria) {
        JpaSpecification<News> specification = new FindNewsBySearchCriteriaJpaSpecification(searchCriteria);
        List<News> allBySpecification = newsRepository.findAllBySpecification(specification);
        return convertToNewsTo(allBySpecification);
    }

    @Override
    public long countAll() {
        return newsRepository.countAll();
    }

    private List<NewsTo> convertToNewsTo(List<News> all) {
        return all.stream()
                .map(news -> mapper.toDto(news))
                .collect(Collectors.toList());
    }
}
