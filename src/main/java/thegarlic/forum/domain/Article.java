package thegarlic.forum.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private Date writeDate;
    private String author;
    private String title;
    private String text;


    public Article(Date writeDate, String author, String title, String text) {
        this.author = author;
        this.writeDate = writeDate;
        this.title = title;
        this.text = text;
    }
}

