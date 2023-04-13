package sk.ness.academy.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import org.springframework.web.server.ResponseStatusException;
import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.ArticlesWithoutComments;

@Repository
public class ArticleHibernateDAO implements ArticleDAO {

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  @Override
  public Article findByID(final Integer articleId) {
    Article article = this.sessionFactory.getCurrentSession().get(Article.class, articleId);
    if (article == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with given ID does not exist.");
    }
    Hibernate.initialize(article.getComments());
    return article;
  }

  @Override
  public void deleteByID(final Integer articleId) {
    Article toDelete = this.sessionFactory.getCurrentSession().load(Article.class, articleId);
    if (toDelete == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with given ID cannot be deleted because the ID does not exist.");
    }
    this.sessionFactory.getCurrentSession().delete(toDelete);
  }

  @SuppressWarnings("unchecked")
  @Override
  public  List<ArticlesWithoutComments> searchArticle(final String searchText){
    /*return this.sessionFactory.getCurrentSession()
            .createSQLQuery("select * from articles where lower(author) like lower(:searchText) or lower(text) like lower(:searchText) or lower(title) like lower(:searchText)")
            .setParameter("searchText", "%"+searchText+"%")
            .addEntity(ArticlesWithoutComments.class)
            .list();*/
   List<ArticlesWithoutComments> allArticles = findAll();
    List<ArticlesWithoutComments> articlesWithMatch = new ArrayList<>();
    for (ArticlesWithoutComments var: allArticles){
      if (var.getText().toLowerCase().contains(searchText.toLowerCase()) || var.getAuthor().toLowerCase().contains(searchText.toLowerCase()) || var.getTitle().toLowerCase().contains(searchText.toLowerCase())){
        articlesWithMatch.add(var);
      }
    }
    return articlesWithMatch;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<ArticlesWithoutComments> findAll() {
    //return this.sessionFactory.getCurrentSession().createSQLQuery("select * from articles").addEntity(Article.class).list();
    return this.sessionFactory.getCurrentSession().createSQLQuery("select * from articles")
            .addScalar("id", IntegerType.INSTANCE)
            .addScalar("title", StringType.INSTANCE)
            .addScalar("text", StringType.INSTANCE)
            .addScalar("author", StringType.INSTANCE)
            .setResultTransformer(new AliasToBeanResultTransformer(ArticlesWithoutComments.class)).list();
  }

  @Override
  public void persist(final Article article) {
    if (article == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Article that you are trying to save is empty.");
    }
    if (article.getTitle() == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Article title is missing. Please try again with correct form.");
    }
    if (article.getAuthor() == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Article author is missing. Please try again with correct form.");
    }
    if (article.getText() == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Article text is missing. Please try again with correct form.");
    }
    this.sessionFactory.getCurrentSession().saveOrUpdate(article);
  }

}
