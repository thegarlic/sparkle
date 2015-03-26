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
	
	@Id @GeneratedValue
	private Long id;
	private String boardName;
	
	public Board(String boardName) {
		this.boardName = boardName;
	}
}
