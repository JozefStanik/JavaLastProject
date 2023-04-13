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

import sk.ness.academy.dao.AuthorHibernateDAO;
import sk.ness.academy.dao.AuthorStatsDAO;
import sk.ness.academy.dao.AuthorStatsHibernateDAO;
import sk.ness.academy.dto.Author;
import sk.ness.academy.dto.AuthorStats;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfig.class, AuthorStatsHibernateDAO.class})
public class AuthorStatsServiceImplTest {

    @Mock
    private AuthorStatsDAO authorStatsDAO;

    @InjectMocks
    private AuthorStatsServiceImpl authorStatsService;
    private List<Author> authors;
    private List<AuthorStats> authorStats;

    private Author author1;

    private Author author2;

    private Author author3;

    private AuthorStats authorStats1;

    private AuthorStats authorStats2;

    private AuthorStats authorStats3;

    @BeforeEach
    private void init() {
        author1 = new Author();
        author1.setName("Name1");
        author2 = new Author();
        author2.setName("Name2");
        author3 = new Author();
        author3.setName("Name3");
        authors = new ArrayList<>();

        authors.add(author1);
        authors.add(author2);
        authors.add(author3);

        authorStats1 = new AuthorStats();
        authorStats1.setAuthorName(author1.getName());
        authorStats1.setArticleCount(BigInteger.valueOf(1));
        authorStats2 = new AuthorStats();
        authorStats2.setAuthorName(author2.getName());
        authorStats2.setArticleCount(BigInteger.valueOf(2));
        authorStats3 = new AuthorStats();
        authorStats3.setAuthorName(author3.getName());
        authorStats3.setArticleCount(BigInteger.valueOf(3));

        authorStats = new ArrayList<>();
        authorStats.addAll(List.of(authorStats1,authorStats2,authorStats3));
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void testGetAuthorStats(){
        Mockito.when(authorStatsDAO.articlesCount()).thenReturn(authorStats);
        final List<AuthorStats> authorStatsDtos = authorStatsService.articlesCount();
        Assertions.assertEquals(3,authorStatsDtos.size());
        Assertions.assertEquals("Name1",authorStatsDtos.get(0).getAuthorName());
        Assertions.assertEquals("Name2",authorStatsDtos.get(1).getAuthorName());
        Assertions.assertEquals("Name3",authorStatsDtos.get(2).getAuthorName());
        Assertions.assertEquals(BigInteger.valueOf(1),authorStatsDtos.get(0).getArticleCount());
        Assertions.assertEquals(BigInteger.valueOf(2),authorStatsDtos.get(1).getArticleCount());
        Assertions.assertEquals(BigInteger.valueOf(3),authorStatsDtos.get(2).getArticleCount());
    }
}
