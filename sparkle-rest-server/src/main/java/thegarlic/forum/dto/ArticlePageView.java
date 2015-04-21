package thegarlic.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.domain.Page;

import thegarlic.forum.dto.view.ArticleDto;
import thegarlic.forum.dto.view.BoardDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePageView {
	private BoardDto board;
	private Page<ArticleDto> articles;
}
