package thegarlic.forum.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import thegarlic.forum.domain.Article;
import thegarlic.forum.repository.ArticleRepository;

@RestController
@RequestMapping("/api/article")
public class ArticleApiController {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Article registerArticle(Article article) {
		article.setWriteDate(new Date());
		article = articleRepository.save(article);
		
		return article;
	}
	
	@RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
	public Article readArticle(@PathVariable long articleId) {
		Article article = articleRepository.findOne(articleId);
		return article;
	}
	
	@RequestMapping(value = "/{articleId}", method = RequestMethod.DELETE)
    public void deleteArticle(@PathVariable long articleId) {
        articleRepository.delete(articleId);
    }
	
    @RequestMapping(value = "/{articleId}/modify", method = RequestMethod.GET)
    public Article modifyArticle(@PathVariable long articleId) {
        Article article = articleRepository.findOne(articleId);
        return article;
    }

    @RequestMapping(value = "/article/{articleId}/modify", method = RequestMethod.PUT)
    public Article modifyArticle(Article article) {
        article.setWriteDate(new Date());
        article = articleRepository.save(article);
        return article;
    }

}
