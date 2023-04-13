package sk.ness.academy.service;

import org.springframework.stereotype.Service;
import sk.ness.academy.dao.CommentDAO;
import sk.ness.academy.domain.Comment;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{

    @Resource
    private CommentDAO commentDAO;

    @Override
    public void createComment(final Comment comment) {
        this.commentDAO.persist(comment);
    }

    @Override
    public Comment findByID(final Integer commentId) {
        return this.commentDAO.findByID(commentId);
    }

    @Override
    public void deleteByID(final Integer commentId) {
        this.commentDAO.deleteByID(commentId);
    }

    @Override
    public List<Comment> findAll() { return this.commentDAO.findAll(); }
}
