package thegarlic.forum.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import org.jadira.usertype.dateandtime.joda.PersistentDateTime;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@TypeDef(defaultForType = DateTime.class, typeClass = PersistentDateTime.class)
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private DateTime writeDate;
	private DateTime modifyDate;
	private String author = "";
	private String title = "";
	private String text = "";
	private Integer readCount = 0;

	@OneToOne
	@JoinColumn(name = "boardId")
	private Board board;

	@OneToMany(mappedBy = "article")
	private List<Comment> comments;

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

