package thegarlic.forum.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private String boardTitle = "자유게시판";
    private Board freeBoard = new Board(boardName, boardTitle);
    

    @Before
    public void before() {
        articleRepository.deleteAll();
        boardRepository.deleteAll();

        mvc = webAppContextSetup(wac).build();
    }

    @Test
    public void getArticleListTest() throws Exception {
        
        Board board = boardRepository.save(freeBoard);
        Article article = new Article("author", "title", "text", board);
        articleRepository.save(article);
        
        mvc.perform(get(String.format("/boards/%s/articles/page/1", boardName)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(mediaType))
            .andExpect(jsonPath("$.data", is(notNullValue())))
            .andExpect(jsonPath("$.data.board.name", is(boardName)))
            .andExpect(jsonPath("$.data.board.title", is(boardTitle)))
            .andExpect(jsonPath("$.data.articles.content", hasSize(1)));
    }
    
    @Test
    public void readArticle() throws Exception {
        
        Board board = boardRepository.save(freeBoard);
        Article article = new Article("author", "title", "text", board);
        articleRepository.save(article);
        Long articleId = article.getId();
        
        mvc.perform(get(String.format("/boards/%s/articles/%d", boardName, articleId)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.meta.ok", is(true)))
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.id", is(articleId.intValue())));
    }
    
    @Test
    public void writeArticle() throws Exception {
        
        boardRepository.save(freeBoard);
        String author = "작성자";
        String title = "제목";
        String text = "내용";
        
        mvc.perform(post(String.format("/boards/%s/articles/write", boardName))
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"title\":\"%s\", \"author\":\"%s\", \"text\": \"%s\" }", title, author, text)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.author", is(author)))
            .andExpect(jsonPath("$.data.title", is(title)))
            .andExpect(jsonPath("$.data.text", is(text)));
    }
    
    @Test
    public void modifyArticle() throws Exception {
        
        Board board = boardRepository.save(freeBoard);
        String author = "작성자";
        String title = "제목";
        String text = "내용";
        Article article = articleRepository.save(new Article(author, title, text, board));
        
        author += "_update";
        title += "_update";
        text += "_update";
        
        mvc.perform(put(String.format("/boards/%s/articles/%d", boardName, article.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"title\":\"%s\", \"author\":\"%s\", \"text\": \"%s\" }", title, author, text)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.author", is(author)))
            .andExpect(jsonPath("$.data.title", is(title)))
            .andExpect(jsonPath("$.data.text", is(text)));
    }
    
    
}
