package sk.ness.academy.service;

import sk.ness.academy.domain.Comment;

import java.util.List;

public interface CommentService {

    void createComment(Comment comment);

    void deleteByID(Integer commentId);

    Comment findByID(Integer commentId);

    List<Comment> findAll();
}
