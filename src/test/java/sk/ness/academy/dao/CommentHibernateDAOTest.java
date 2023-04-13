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
import sk.ness.academy.dao.ArticleHibernateDAO;
import sk.ness.academy.dao.CommentHibernateDAO;
import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfig.class, CommentHibernateDAO.class})
@Transactional
@Sql({"/initdb.sql"})
public class CommentHibernateDAOTest {
    @Autowired
    private CommentHibernateDAO commentHibernateDAO;
    private Comment comment;

    private Comment commentToDelete;

    @BeforeEach
    public void beforeEach(){
        comment = new Comment();
        comment.setId(6);
        comment.setAuthor("Author C6");
        comment.setText("Text C6");
        comment.setArticle_id(1);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findByIdTest(){
        final Comment comment = commentHibernateDAO.findByID(1);
        Assertions.assertEquals(1,comment.getId());
        Assertions.assertEquals("Author C1",comment.getAuthor());
        Assertions.assertEquals("Text C1",comment.getText());
    }

    @Test
    void findAllTest(){
        final List<Comment> comments = commentHibernateDAO.findAll();
        Assertions.assertEquals(5,comments.size());
        Assertions.assertEquals("Author C1",comments.get(0).getAuthor());
        Assertions.assertEquals("Text C3",comments.get(2).getText());
    }

    @Test
    void persistTest(){
        commentHibernateDAO.persist(comment);
        Assertions.assertEquals("Author C6", commentHibernateDAO.findByID(6).getAuthor());
    }

    @Test
    void deleteByIdTest(){
        commentToDelete = commentHibernateDAO.findByID(6);
        commentHibernateDAO.deleteByID(6);
        Assertions.assertNull(commentToDelete);
    }
}
