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

import sk.ness.academy.dao.AuthorDAO;
import sk.ness.academy.dao.AuthorHibernateDAO;
import sk.ness.academy.dto.Author;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfig.class, AuthorHibernateDAO.class})
public class AuthorServiceImplTest {
    @Mock
    private AuthorDAO authorDAO;
    @InjectMocks
    private AuthorServiceImpl authorService;
    private List<Author> authors;

    private Author author1;

    private Author author2;

    private Author author3;

    @BeforeEach
    private void setup() {
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
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindAll(){
        Mockito.when(authorDAO.findAll()).thenReturn(authors);
        final List<Author> authors = authorService.findAll();

        Assertions.assertEquals(3,authors.size());
        Assertions.assertEquals("Name1",authors.get(0).getName());
        Assertions.assertEquals("Name2",authors.get(1).getName());
        Assertions.assertEquals("Name3",authors.get(2).getName());
    }
}
