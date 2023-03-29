package sk.ness.academy.dto;

import java.math.BigInteger;

public class AuthorStats {

	  private String authorName;
	  private BigInteger articleCount;

	  public String getAuthorName() {
	    return this.authorName;
	  }

	  public void setAuthorName(final String authorName) {
	    this.authorName = authorName;
	  }

	  public BigInteger getArticleCount() {
	    return this.articleCount;
	  }

	  public void setArticleCount(final BigInteger articleCount) {
	    this.articleCount = articleCount;
	  }

	}
