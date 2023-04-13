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
import sk.ness.academy.dao.CommentDAO;
import sk.ness.academy.dao.CommentHibernateDAO;
import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfig.class, CommentHibernateDAO.class})
public class CommentServiceImplTest {

    @Mock
    private CommentDAO commentDAO;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Comment comment;

    private Comment comment1;

    private Comment comment2;

    private List<Comment> comments;

    private Article article;


    @BeforeEach
    private void setUp() {
        article = new Article();
        article.setId(1);
        article.setTitle("Title 1");
        article.setText("Text 1");
        article.setAuthor("Author 1");

        comment = new Comment();
        comment.setId(0);
        comment.setAuthor("Author");
        comment.setText("Text");

        comment1 = new Comment();
        comment1.setId(1);
        comment1.setAuthor("Author 1");
        comment1.setText("Text 1");

        comment2 = new Comment();
        comment2.setId(2);
        comment2.setAuthor("Author 2");
        comment2.setText("Text 2");

        comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment1);

        article.setComments(comments);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindById(){
        Mockito.when(commentDAO.findByID(1)).thenReturn(comment);
        final Comment comment = commentService.findByID(1);
        Assertions.assertEquals(0,comment.getId());
        Assertions.assertEquals("Author",comment.getAuthor());
        Assertions.assertEquals("Text",comment.getText());
    }

    @Test
    void testFindAll(){
        Mockito.when(commentDAO.findAll()).thenReturn(comments);
        final List<Comment> commentList = commentService.findAll();
        Assertions.assertEquals(2,commentList.size());
        Assertions.assertEquals("Author",commentList.get(0).getAuthor());
        Assertions.assertEquals("Text 1",commentList.get(1).getText());
    }

    @Test
    void testCreateComment(){
        commentDAO.persist(comment2);
        Mockito.verify(commentDAO).persist(comment2);
    }

    @Test
    void testDeleteComment(){
        commentDAO.deleteByID(3);
        Mockito.verify(commentDAO).deleteByID(3);
    }
}
