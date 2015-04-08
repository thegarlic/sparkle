package thegarlic.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.domain.Page;

import thegarlic.forum.domain.Article;
import thegarlic.forum.domain.Board;

@Data
@AllArgsConstructor
public class BoardArticleDto {
    private Board board;
    private Page<Article> articles;
}
