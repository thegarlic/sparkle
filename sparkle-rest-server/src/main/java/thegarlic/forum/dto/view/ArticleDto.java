package thegarlic.forum.dto.view;

import java.util.List;

import lombok.Data;
import thegarlic.forum.domain.Comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@JsonInclude(Include.NON_EMPTY)
public class ArticleDto {
	private long id;
	private String writeDate;
	private String modifyDate;
	private String author;
	private String title;
	private String text;
	private Integer readCount;
	
	private List<Comment> comments;
	private Integer commentCount;
	
}