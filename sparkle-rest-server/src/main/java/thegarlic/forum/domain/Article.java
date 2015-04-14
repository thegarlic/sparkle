package thegarlic.forum.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

import org.hibernate.annotations.TypeDef;
import org.jadira.usertype.dateandtime.joda.PersistentDateTime;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@Entity
@TypeDef(defaultForType = DateTime.class, typeClass = PersistentDateTime.class)
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @JsonIgnore
    private DateTime writeDate;
    @JsonIgnore
    private DateTime modifyDate;
    private String author;
    private String title;
    private String text;
    private Integer readCount = 0;
    
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "boardId")
    private Board board;
    
    @OneToMany(mappedBy = "article")
    private List<Comment> comments;
    
    public String getFormattedWriteDate() {
        return writeDate.withZone(DateTimeZone.UTC).toString();
    }
    
    @JsonInclude(Include.NON_NULL)
    public String getFormattedModifyDate() {
        return modifyDate == null ? null : writeDate.withZone(DateTimeZone.UTC).toString();
    }
    
    public Article() {
    	this("", "", "");
    }
    
    public Article(String author, String title, String text) {
    	this(author, title, text, null);
    }
    
    public Article(String author, String title, String text, Board board) {
        this.author = author;
        this.title = title;
        this.text = text;
        this.board = board;
        
        this.writeDate = DateTime.now();
    }
}

