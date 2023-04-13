package sk.ness.academy.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import sk.ness.academy.config.TestDatabaseConfig;
import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;
import sk.ness.academy.dto.ArticlesWithoutComments;
import org.springframework.test.context.jdbc.Sql;

import java.util.*;


import javax.transaction.Transactional;

@SpringBootTest
@ContextConfiguration(classes = { TestDatabaseConfig.class, ArticleHibernateDAO.class})
@Transactional
@Sql({"/initdb.sql"})
class ArticleHibernateDAOTest {

    @Autowired
    private ArticleHibernateDAO articleHibernateDAO;
    private Article article;

    @BeforeEach
    public void beforeEach(){
        article = new Article();
        article.setId(4);
        article.setAuthor("Author 4");
        article.setTitle("Title 4");
        article.setText("Text 4");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById(){
        final Article article = articleHibernateDAO.findByID(3);
        Assertions.assertEquals(3,article.getId());
        Assertions.assertEquals("Author 3",article.getAuthor());
        Assertions.assertEquals("Text 3",article.getText());
        Assertions.assertEquals("Title 3",article.getTitle());
        Assertions.assertEquals(1,article.getComments().size());

    }

    @Test
    void findAllTest(){
        final List<ArticlesWithoutComments> articles = articleHibernateDAO.findAll();
        Assertions.assertEquals(3,articles.size());
    }

    @Test
    void searchAllTest() {
        final List<ArticlesWithoutComments> search = articleHibernateDAO.searchArticle("Author");
        Assertions.assertEquals(3, search.size());
        Assertions.assertEquals("Author 1",search.get(0).getAuthor());
        Assertions.assertEquals("Author 2",search.get(1).getAuthor());
        Assertions.assertEquals("Author 3",search.get(2).getAuthor());
    }

    @Test
    void persistTest(){
        articleHibernateDAO.persist(article);
        Assertions.assertEquals("Author 4", articleHibernateDAO.findByID(4).getAuthor());
        Assertions.assertEquals("Title 4", articleHibernateDAO.findByID(4).getTitle());
        Assertions.assertEquals("Text 4", articleHibernateDAO.findByID(4).getText());
    }

    @Test
    void deleteArticleTest(){
        articleHibernateDAO.persist(article);
        Assertions.assertEquals(article,articleHibernateDAO.findByID(4));
        articleHibernateDAO.deleteByID(article.getId());
        Assertions.assertNull(articleHibernateDAO.findByID(4));
    }
}
