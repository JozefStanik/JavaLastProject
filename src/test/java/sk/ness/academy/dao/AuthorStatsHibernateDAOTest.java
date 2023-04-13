package sk.ness.academy.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import sk.ness.academy.config.TestDatabaseConfig;
import sk.ness.academy.dto.AuthorStats;

import javax.transaction.Transactional;

import java.util.List;


@SpringBootTest
@ContextConfiguration(classes = { TestDatabaseConfig.class, AuthorStatsHibernateDAO.class })
@Transactional
@Sql({"/initdb.sql"})
public class AuthorStatsHibernateDAOTest {

    @Autowired
    private AuthorStatsHibernateDAO authorStatsHibernateDAO;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void authorStats() {
        final List<AuthorStats> authorStatsList = authorStatsHibernateDAO.articlesCount();

        Assertions.assertEquals(3, authorStatsList.size());
        Assertions.assertEquals("Author 1", authorStatsList.get(0).getAuthorName());
        Assertions.assertEquals("Author 2", authorStatsList.get(1).getAuthorName());
        Assertions.assertEquals("Author 3", authorStatsList.get(2).getAuthorName());
        Assertions.assertEquals(2, authorStatsList.get(0).getArticleCount());
        Assertions.assertEquals(2, authorStatsList.get(1).getArticleCount());
        Assertions.assertEquals(1, authorStatsList.get(2).getArticleCount());
    }
}
