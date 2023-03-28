package sk.ness.academy.dao;

import java.util.List;

import sk.ness.academy.domain.Article;

public interface ArticleDAO {

	  /** Returns {@link Article} with provided ID */
	  Article findByID(Integer articleId);

	  void deleteByID(Integer articleId);

	  List<Article> searchArticle(String searchText);

	  /** Returns all available {@link Article}s */
	  List<Article> findAll();

	  /** Persists {@link Article} into the DB */
	  void persist(Article article);
	}
