package sk.ness.academy.service;

import java.util.List;

import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.ArticlesWithoutComments;

public interface ArticleService {

	  /** Returns {@link Article} with provided ID */
	  Article findByID(Integer articleId);

	  /** Deletes {@link Article} with provided ID */
	  void deleteByID(Integer articleId);

	  List<ArticlesWithoutComments> searchArticle(String searchText);

	  /** Returns all available {@link Article}s */
	  List<ArticlesWithoutComments> findAll();

	  /** Creates new {@link Article} */
	  void createArticle(Article article);

	  /** Creates new {@link Article}s by ingesting all articles from json */
	  void ingestArticles(String jsonArticles);
}
