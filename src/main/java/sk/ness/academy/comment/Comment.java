package sk.ness.academy.comment;


import sk.ness.academy.domain.Article;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "coments")
@SequenceGenerator(name = "articles_seq_store", sequenceName = "comment_seq", allocationSize = 1)
public class Comment {

    public Comment() {
        this.createTimestamp = new Date();
    }

    @Id
    @Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "articles_seq_store")
    private Integer id;

    //@ManyToOne
    //@JoinColumn(name = "article_id")
    //private Article article;

    @Column(name = "author", length = 250)
    private String authorOfComment;

    @Column(name = "text", length = 2000)
    private String textOfComment;

    @Column(name = "author", length = 250)
    private String author;

    @Column(name = "create_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTimestamp;
}
