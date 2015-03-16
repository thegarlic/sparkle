package thegarlic.forum.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private Date writeDate;
    private String title;
    private String text;


    public Post(Date writeDate, String title, String text) {
        this.writeDate = writeDate;
        this.title = title;
        this.text = text;
    }
}

