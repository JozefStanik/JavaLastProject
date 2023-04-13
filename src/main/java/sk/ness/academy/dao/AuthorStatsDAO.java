package sk.ness.academy.dao;

import sk.ness.academy.dto.AuthorStats;
import sk.ness.academy.dto.ArticlesWithoutComments;

import java.util.List;

public interface AuthorStatsDAO {

    List<AuthorStats> articlesCount();
}
