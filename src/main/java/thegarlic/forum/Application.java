package thegarlic.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import thegarlic.forum.domain.Article;
import thegarlic.forum.repository.ArticleRepository;

import java.util.Date;

@SpringBootApplication
public class Application {

    //FIXME 임시데이터 이므로 삭제필요
    ArticleRepository articleRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {

        this.articleRepository = articleRepository;

        //FIXME 임시 데이터는 나중에 삭제
        Date now = new Date();
        articleRepository.save(new Article(now, "aa", "title1", "text1"));
        articleRepository.save(new Article(now, "bb", "title2", "text2"));
        articleRepository.save(new Article(now, "cc", "title3", "text3"));
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}