package sk.ness.academy.dao;

import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;

import java.util.List;

public interface CommentDAO {

    Comment findByID(Integer commentID);

    void deleteByID(Integer commentID);


    /** Returns all available {@link Article}s */
    List<Comment> findAll();

    /** Persists {@link Article} into the DB */
    void persist(Comment comment);
}
