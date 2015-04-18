package thegarlic.forum.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class Comment {
    
    @Id @GeneratedValue
    private Long id;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "articleId")
    private Article article;
}
