package thegarlic.forum.controller;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import thegarlic.forum.domain.Article;
import thegarlic.forum.repository.ArticleRepository;

@Controller
public class ArticleController {
    
    @Autowired
    ArticleRepository articleRepository;
    
    @RequestMapping(value = "/articles")
    public String viewArticles(Model model) {
        
        model.addAttribute("articles", articleRepository.findAll());
        
        return "viewArticles";
    }

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public String registerArticle() {
        return "registerArticle";
    }

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public String registerArticle(Article article) {
        article.setWriteDate(LocalDateTime.now());
        articleRepository.save(article);
        return "redirect:/article/" + article.getId();
    }

    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public String readArticle(@PathVariable long articleId, Model model) {
        Article article = articleRepository.findOne(articleId);
        model.addAttribute(article);
        return "viewArticle";
    }

    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.DELETE)
    public String deleteArticle(@PathVariable long articleId) {
        articleRepository.delete(articleId);
        return "redirect:/articles";
    }

    @RequestMapping(value = "/article/{articleId}/modify", method = RequestMethod.GET)
    public String modifyArticle(@PathVariable long articleId, Model model) {
        Article article = articleRepository.findOne(articleId);
        model.addAttribute(article);
        return "modifyArticle";
    }

    @RequestMapping(value = "/article/{articleId}/modify", method = RequestMethod.PUT)
    public String modifyArticle(Article article) {
        article.setWriteDate(LocalDateTime.now());
        articleRepository.save(article);
        return "redirect:/article/" + article.getId();
    }

}
