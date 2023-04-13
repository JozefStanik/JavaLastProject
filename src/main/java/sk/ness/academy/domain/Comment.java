package sk.ness.academy.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.*;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

import sk.ness.academy.domain.Article;

@Entity
@Table(name = "comments")
@SequenceGenerator(name = "comments_seq_store", sequenceName = "comment_seq", allocationSize = 1)
public class Comment {
    public Comment(){
        this.createTimestamp = new Date();
    }
    @Id
    @Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_seq_store")
    private Integer id;

    @Column(name = "author", length = 250)
    private String author;

    @Column(name = "text", length = 2000)
    private String text;

    @Column(name = "create_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTimestamp;

    @Column(name = "article_comment")
    private Integer article_id;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "article_id")
    //private Article article;

    public Integer getArticle_id() { return this.article_id; }

    public void setArticle_id(final Integer article_id) { this.article_id = article_id; }

    public Integer getId() { return this.id; }

    public void setId(final Integer id) { this.id = id; }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getText() {
        return this.text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public Date getCreateTimestamp() {
        return this.createTimestamp;
    }

    public void setCreateTimestamp(final Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    //public Article getArticle() { return article; }

    //public void setArticle(Article article) { this.article = article; }
}
