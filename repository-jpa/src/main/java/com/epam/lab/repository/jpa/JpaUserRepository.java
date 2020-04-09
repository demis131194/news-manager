package com.epam.lab.repository.jpa;

import com.epam.lab.exception.RepositoryException;
import com.epam.lab.model.Tag;
import com.epam.lab.model.User;
import com.epam.lab.repository.DbConstants;
import com.epam.lab.repository.UserRepository;
import com.epam.lab.repository.specification.JpaSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepository implements UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(JpaTagRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        logger.trace("Start JpaUserRepository save method");
        if (user.isNew()) {
            logger.debug("persist user - {}", user);
            entityManager.persist(user);
        } else {
            logger.debug("merge user - {}", user);
            entityManager.merge(user);
        }
        User findUser = entityManager.find(User.class, user.getId());

        if (findUser == null) {
            throw new RepositoryException("Can't update user, wrong id - " + user.getId());
        }

        logger.info("Saved user - {}", findUser);
        logger.trace("End JpaUserRepository save method");
        return findUser;
    }

    @Override
    public boolean delete(long id) {
        logger.trace("Start JpaUserRepository delete method");
        logger.debug("Deleted by id - {}", id);
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        boolean isDelete = entityManager.find(User.class, id) == null;
        logger.info("Result of delete user by id {} - {}", id, isDelete);
        logger.trace("End JpaUserRepository delete method");
        return isDelete;
    }

    @Override
    public User findById(long id) {
        logger.trace("Start JpaUserRepository findById method");
        logger.debug("Try find user by id - {}", id);
        User user = entityManager.find(User.class, id);
        logger.info("Find user by id {} - {}", id, user);
        logger.trace("End JpaUserRepository findById method");
        return user;
    }

    @Override
    public List<User> findAllBySpecification(JpaSpecification<User> specification) {
        throw new UnsupportedOperationException("Haven't any specification");
    }

    @Override
    public List<User> findAll() {
        logger.trace("Start JpaUserRepository findAll method");
        logger.debug("Try find all users");
        List<User> resultList = entityManager.createNamedQuery(User.FIND_ALL, User.class).getResultList();
        logger.debug("Find users - {}", resultList);
        logger.trace("End JpaUserRepository findAll method");
        return resultList;
    }

    @Override
    public long countAll() {
        logger.trace("Start JpaUserRepository countAll method");
        long result = entityManager.createNamedQuery(User.COUNT_ALL, Long.class).getSingleResult();
        logger.info("Users count - {}", result);
        logger.trace("End JpaUserRepository countAll method");
        return result;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        try {
            user = entityManager.createNamedQuery(User.FIND_BY_LOGIN, User.class)
                    .setParameter(1, login)
                    .getSingleResult();
        } catch (NoResultException ignored) {
        }
        return user;
    }
}
