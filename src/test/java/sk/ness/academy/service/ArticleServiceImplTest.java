package sk.ness.academy.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import sk.ness.academy.config.TestDatabaseConfig;
import sk.ness.academy.dao.ArticleDAO;
import sk.ness.academy.dao.ArticleHibernateDAO;
import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.ArticlesWithoutComments;

import java.util.*;

@SpringBootTest
@ContextConfiguration(classes = { TestDatabaseConfig.class, ArticleHibernateDAO.class })
class ArticleServiceImplTest {
    @Mock
    private ArticleDAO articleDAO;

    @InjectMocks
    private ArticleServiceImpl articleService;

    private Article article1;

    private Article article2;

    private Article article3;

    private ArticlesWithoutComments article4;

    private List<Article> articles;

    private List<ArticlesWithoutComments> articles1;

    @BeforeEach
    void setUp() {
        article1 = new Article();
        article1.setId(1);
        article1.setTitle("Title 1");
        article1.setText("Text 1");
        article1.setAuthor("Author 1");

        article2 = new Article();
        article2.setId(2);
        article2.setTitle("Title 2");
        article2.setText("Text 2");
        article2.setAuthor("Author 2");

        article3 = new Article();
        article3.setId(3);
        article3.setTitle("Title 3");
        article3.setText("Text 3");
        article3.setAuthor("Author 3");

        articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);

        article4 = new ArticlesWithoutComments();
        article4.setId(3);
        article4.setTitle("TitleWithout");
        article4.setText("TextWithout");
        article4.setAuthor("AuthorWithout");

        articles1 = new ArrayList<>();
        articles1.add(article4);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByID() {
        Mockito.when(articleDAO.findByID(1)).thenReturn(article1);
        Mockito.when(articleDAO.findByID(2)).thenReturn(article2);

        final Article article1 = articleService.findByID(1);
        final Article article2 = articleService.findByID(2);

        //HAPPY PATH
        Assertions.assertEquals("Title 1", article1.getTitle());
        Assertions.assertEquals("Title 2", article2.getTitle());
    }

    @Test
    void findAll() {
        Mockito.when(articleDAO.findAll()).thenReturn(articles1);

        final List<ArticlesWithoutComments> articleList = articleService.findAll();

        Assertions.assertEquals(1, articleList.size());
        Assertions.assertEquals("AuthorWithout", articleList.get(0).getAuthor());
    }

    @Test
    void createArticle() {
        articleService.createArticle(article3);
        Mockito.verify(articleDAO, Mockito.times(1)).persist(article3);
    }

    @Test
    void deleteByID() {
        articleService.deleteByID(3);
        Mockito.verify(articleDAO, Mockito.times(1)).deleteByID(3);
    }
}
