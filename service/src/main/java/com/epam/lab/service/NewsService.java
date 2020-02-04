package com.epam.lab.service;

import com.epam.lab.dto.NewsTo;
import com.epam.lab.exeption.ServiceException;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class NewsService {

//    @Autowired
//    private AuthorRepository authorRepository;
//
//    @Autowired
//    private NewsRepository newsRepository;
//
//    @Autowired
//    private TagRepository tagRepository;
//
//    public NewsTo createNews(NewsTo newsTo) throws ServiceException {
//        if (Validator.validate(newsTo) && newsTo.getNews().isNew()) {
//
//            Author author = newsTo.getAuthor();
//            if (Validator.validate(author)) {
//                if (author.isNew()) {
//                    long authorId = authorRepository.create(author);
//                    author.setId(authorId);
//                }
//            } else {
//                throw new ServiceException("Illegal argument author");
//            }
//
//            Set<Tag> tags = newsTo.getTags();
//
//            for (Tag tag : tags) {
//                if (Validator.validate(tag)) {
//                    if (tag.isNew()) {
//                        long authorId = tagRepository.create(tag);
//                        tag.setId(authorId);
//                    }
//                } else {
//                    throw new ServiceException("Illegal argument tag");
//                }
//            }
//
//            News news = newsTo.getNews();
//            long newsId = newsRepository.create(news);
//            news.setId(newsId);
//
//            newsRepository.createNewsAuthorBound(news.getId(), author.getId());
//            tags.forEach(tag -> newsRepository.createNewsTagBound(news.getId(), tag.getId()));
//
//            return newsTo;
//        }
//        throw new ServiceException("Illegal argument newsTo");
//    }
//
//    public boolean addNewBoundsNewsTag(Set<Tag> tags, long newsId) {            // FIXME: 2/3/2020 return
//        Objects.requireNonNull(tags);
//        tags.forEach(tag -> {
//            if (Validator.validate(tag)) {
//                if (tag.isNew()) {
//                    long tagId = tagRepository.create(tag);
//                    tag.setId(tagId);
//                }
//                newsRepository.createNewsTagBound(newsId, tag.getId());
//            }
//        });
//        return true;
//    }
//
//    public long countAllNews() {
//        return newsRepository.countAllNews();
//    }
//
//    public NewsTo findNewsToById(long newsId) {
//        News news = newsRepository.findById(newsId);
//        long authorIdByNewsId = newsRepository.findAuthorIdByNewsId(newsId);
//        List<Long> tagsIdByNewsId = newsRepository.findTagsIdByNewsId(newsId);
//
//        Author author = authorRepository.findById(authorIdByNewsId);
//
//        Set<Tag> tags = new HashSet<>();
//        tagsIdByNewsId.forEach(tagId -> {
//            tags.add(tagRepository.findById(tagId));
//        });
//
//        return new NewsTo(news, author, tags);
//    }
//
//

}
