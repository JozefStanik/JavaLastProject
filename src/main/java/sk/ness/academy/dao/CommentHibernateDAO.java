package sk.ness.academy.dao;

import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import org.springframework.web.server.ResponseStatusException;
import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;

import javax.annotation.Resource;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import sk.ness.academy.domain.Comment;
@Repository
public class CommentHibernateDAO implements  CommentDAO{
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Comment findByID(final Integer commentID) {
        Comment comment = this.sessionFactory.getCurrentSession().get(Comment.class, commentID);
        if (comment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment with given ID does not exist.");
        }
        return comment;
    }

    @Override
    public void deleteByID(final Integer commentID) {
        Comment toDelete = this.sessionFactory.getCurrentSession().load(Comment.class, commentID);
        if (toDelete == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment with given ID cannot be deleted because the ID does not exist.");
        }
        this.sessionFactory.getCurrentSession().delete(toDelete);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> findAll() {
        return this.sessionFactory.getCurrentSession().createSQLQuery("select * from comments").addEntity(Comment.class).list();
    }

    @Override
    public void persist(final Comment comment) {
        if (comment == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Comment that you are trying to save is empty.");
        }
        if (comment.getAuthor() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Comment author is missing. Please try again with correct form.");
        }
        if (comment.getText() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Comment text is missing. Please try again with correct form.");
        }
        this.sessionFactory.getCurrentSession().saveOrUpdate(comment);
    }
}
