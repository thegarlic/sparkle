package thegarlic.forum.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

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
