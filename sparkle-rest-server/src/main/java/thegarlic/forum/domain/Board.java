package thegarlic.forum.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String title;

    public Board(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
