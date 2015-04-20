package thegarlic.forum.dto;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import thegarlic.forum.dto.view.Article;

import java.util.List;

@Data
public class ArticlePageView {
	private static ModelMapper modelMapper = new ModelMapper();

	private thegarlic.forum.dto.view.Board board;
	private Page<thegarlic.forum.dto.view.Article> articles;

	public ArticlePageView(thegarlic.forum.domain.Board originalBoard, Page<thegarlic.forum.domain.Article> originalArticlePage) {
		board = modelMapper.map(originalBoard, thegarlic.forum.dto.view.Board.class);

		List<thegarlic.forum.domain.Article> originalArticlePageContent = originalArticlePage.getContent();
		Page<thegarlic.forum.dto.view.Article> articlePageContent = new PageImpl<Article>();

		modelMapper.map(originalArticlePageContent, articlePageContent);


		articles = modelMapper.map(originalArticlePage, Page.class);

		System.out.println(articles);
	}

}
