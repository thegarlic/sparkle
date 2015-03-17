package thegarlic.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import thegarlic.forum.domain.Article;

@Controller
public class ArticleController {

	@Autowired
	ArticleApiController articleApiController;

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public String registerArticle() {
        return "registerArticle";
    }


    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public String registerArticle(Article article) {
    	article = articleApiController.registerArticle(article);
        return "redirect:/article/" + article.getId();
    }


    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public String readArticle(@PathVariable long articleId, Model model) {
        Article article = articleApiController.readArticle(articleId);
        model.addAttribute(article);
        return "viewArticle";
    }

    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.DELETE)
    public String deleteArticle(@PathVariable long articleId) {
        articleApiController.deleteArticle(articleId);
        return "redirect:/article";
    }

    @RequestMapping(value = "/article/{articleId}/modify", method = RequestMethod.GET)
    public String modifyArticle(@PathVariable long articleId, Model model) {
        Article article = articleApiController.modifyArticle(articleId);
        model.addAttribute(article);
        return "modifyArticle";
    }

    @RequestMapping(value = "/article/{articleId}/modify", method = RequestMethod.PUT)
    public String modifyArticle(Article article) {
    	article = articleApiController.modifyArticle(article);
        return "redirect:/article/" + article.getId();
    }

}
