package thegarlic.forum.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

import org.hibernate.annotations.TypeDef;
import org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime;
import org.joda.time.LocalDateTime;

import thegarlic.forum.Const;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@Entity
@TypeDef(defaultForType = LocalDateTime.class, typeClass = PersistentLocalDateTime.class)
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @JsonIgnore
    private LocalDateTime writeDate;
    @JsonIgnore
    private LocalDateTime modifyDate;
    private String author;
    private String title;
    private String text;
    private Integer readCount = 0;
    
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "boardId")
    private Board board;
    
    public String getWriteDateString() {
        return writeDate.toString(Const.DATE_PATTERN);
    }
    
    @JsonInclude(Include.NON_NULL)
    public String getModifyDateString() {
        return modifyDate == null ? null : modifyDate.toString(Const.DATE_PATTERN);
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
        
        this.writeDate = LocalDateTime.now();
    }
}

