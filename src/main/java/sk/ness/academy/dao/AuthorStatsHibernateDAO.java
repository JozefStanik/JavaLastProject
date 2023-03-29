package sk.ness.academy.dao;

import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;
import sk.ness.academy.dto.AuthorStats;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorStatsHibernateDAO implements AuthorStatsDAO{
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<AuthorStats> articlesCount() {
        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery("select a.author, count(*) from articles a group by a.author").list();
        List<AuthorStats> authorStatsList = new ArrayList<>();

        for (Object[] result : results) {
            String authorName = (String) result[0];
            BigInteger articleCount = (BigInteger) result[1];

            AuthorStats authorStats = new AuthorStats();
            authorStats.setAuthorName(authorName);
            authorStats.setArticleCount(articleCount);

            authorStatsList.add(authorStats);
        }
        return authorStatsList;
    }
}
