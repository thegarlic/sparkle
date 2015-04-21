package thegarlic.forum.dto.view;

import lombok.Data;

@Data
public class ArticleDto {
	private long id;
	private String writeDate;
	private String modifyDate;
	private String author;
	private String title;
	private Integer readCount;
}