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
import sk.ness.academy.dto.Author;

import javax.transaction.Transactional;

import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = { TestDatabaseConfig.class, AuthorHibernateDAO.class })
@Transactional
@Sql({"/initdb.sql"})
public class AuthorHibernateDAOTest {

    @Autowired
    private AuthorHibernateDAO authorHibernateDAO;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        final List<Author> authorList = authorHibernateDAO.findAll();

        Assertions.assertEquals(3, authorList.size());
        Assertions.assertEquals("Author 1", authorList.get(0).getName());
        Assertions.assertEquals("Author 2", authorList.get(1).getName());
        Assertions.assertEquals("Author 3", authorList.get(2).getName());
    }
}
