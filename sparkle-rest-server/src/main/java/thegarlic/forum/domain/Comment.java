package thegarlic.forum.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
public class Comment {
    
    @Id @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "articleId")
    private Article article;

    private String text;
    private String author;

    @JsonIgnore
    private DateTime writeDate;

    @JsonIgnore
    private DateTime modifyDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public DateTime getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(DateTime writeDate) {
        this.writeDate = writeDate;
    }

    public DateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(DateTime modifyDate) {
        this.modifyDate = modifyDate;
    }
}
