package sk.ness.academy.dao;

import sk.ness.academy.dto.AuthorStats;

import java.util.List;

public interface AuthorStatsDAO {

    List<AuthorStats> articlesCount();
}
