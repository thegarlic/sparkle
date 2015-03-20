package thegarlic.forum.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.TypeDef;
import org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime;
import org.joda.time.LocalDateTime;

import thegarlic.forum.Const;

@Data
@Entity
@NoArgsConstructor
@TypeDef(defaultForType = LocalDateTime.class, typeClass = PersistentLocalDateTime.class)
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private LocalDateTime writeDate;
    private String author;
    private String title;
    private String text;
    
    public String getWriteDateString() {
        return writeDate.toString(Const.DATE_TIME_PATTERN);
    }
    
    public Article(LocalDateTime writeDate, String author, String title, String text) {
        this.author = author;
        this.writeDate = writeDate;
        this.title = title;
        this.text = text;
    }
}

