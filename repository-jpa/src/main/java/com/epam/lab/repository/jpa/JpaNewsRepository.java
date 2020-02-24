package com.epam.lab.repository.jpa;

import com.epam.lab.model.News;
import com.epam.lab.repository.DbConstants;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.specification.JpaSpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaNewsRepository implements NewsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public News save(News news) {
        if (news.isNew()) {
            entityManager.persist(news);
        } else {
            entityManager.merge(news);
        }
        return news;
    }

    @Override
    @Transactional
    public boolean delete(long newsId) {
        return entityManager.createNamedQuery(News.DELETE)
                .setParameter(DbConstants.ID_COLUMN_NAME, newsId)
                .executeUpdate() != 0;
    }

    @Override
    public News findById(long id) {
        return entityManager.find(News.class, id);
    }

    @Override
    public List<News> findAllBySpecification(JpaSpecification<News> specification) {
        return specification.query(entityManager).getResultList();
    }

    @Override
    public List<News> findAll() {
        return entityManager.createNamedQuery(News.FIND_ALL, News.class).getResultList();
    }

    @Override
    public long countAll() {
        return entityManager.createNamedQuery(News.COUNT_ALL, Long.class).getSingleResult();
    }
}
