package sk.ness.academy.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import sk.ness.academy.dao.ArticleDAO;
import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.ArticlesWithoutComments;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

  @Resource
  private ArticleDAO articleDAO;

  @Override
  public Article findByID(final Integer articleId) {
	  return this.articleDAO.findByID(articleId);
  }

  @Override
  public void deleteByID(final Integer articleId) {
    this.articleDAO.deleteByID(articleId);
  }

  @Override
  public List<ArticlesWithoutComments> searchArticle(final String searchText) { return this.articleDAO.searchArticle(searchText); }

  @Override
  public List<ArticlesWithoutComments> findAll() { return this.articleDAO.findAll(); }

  @Override
  public void createArticle(final Article article) {
	  this.articleDAO.persist(article);
  }

  @Override
  public void ingestArticles(final String jsonArticles) {
    //throw new UnsupportedOperationException("Article ingesting not implemented.");
    ObjectMapper om = new ObjectMapper();

    try {
      List<Article> articles = List.of(om.readValue(jsonArticles, Article[].class));
      articles.forEach(a -> articleDAO.persist(a));
    } catch (JsonProcessingException e){
      e.printStackTrace();
    }
  }

}
