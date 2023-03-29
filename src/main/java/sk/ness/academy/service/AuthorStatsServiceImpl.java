package sk.ness.academy.service;

import org.springframework.stereotype.Service;
import sk.ness.academy.dao.AuthorDAO;
import sk.ness.academy.dao.AuthorStatsDAO;
import sk.ness.academy.dto.AuthorStats;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorStatsServiceImpl implements AuthorStatsService{

    @Resource
    private AuthorStatsDAO authorStatsDAO;

    @Override
    public List<AuthorStats> articlesCount() {
        return this.authorStatsDAO.articlesCount();
    }
}
