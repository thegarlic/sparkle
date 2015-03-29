package thegarlic.forum.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.hamcrest.Matchers.*;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import thegarlic.forum.Application;
import thegarlic.forum.domain.Article;
import thegarlic.forum.domain.Board;
import thegarlic.forum.repository.ArticleRepository;
import thegarlic.forum.repository.BoardRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class BoardControllerTest {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    WebApplicationContext wac;
    MockMvc mvc;

    private MediaType mediaType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("UTF-8"));
    
    private String boardName = "free";

    @Before
    public void before() {
        articleRepository.deleteAll();
        boardRepository.deleteAll();

        mvc = webAppContextSetup(wac).build();
    }

    @Test
    public void getArticleListTest() throws Exception {
        
        Board board = boardRepository.save(new Board(boardName));
        Article article = new Article("author", "title", "text", board);
        articleRepository.save(article);
        
        mvc.perform(get(String.format("/boards/%s/articles/page/1", boardName)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(mediaType))
            .andExpect(jsonPath("$.data", is(notNullValue())))
            .andExpect(jsonPath("$.data.content", hasSize(1)));
    }
    
    @Test
    public void readArticle() throws Exception {
        
        Board board = boardRepository.save(new Board(boardName));
        Article article = new Article("author", "title", "text", board);
        articleRepository.save(article);
        Long articleId = article.getId();
        
        mvc.perform(get(String.format("/boards/%s/articles/%s", boardName, articleId)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.meta.ok", is(true)))
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.id", is(articleId.intValue())));
           
    }
    
    
}
