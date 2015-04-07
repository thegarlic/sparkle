package thegarlic.forum.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import thegarlic.forum.domain.Article;
import thegarlic.forum.domain.Board;
import thegarlic.forum.repository.ArticleRepository;
//import thegarlic.forum.repository.BoardRepository;
import thegarlic.forum.repository.BoardRepository;

@Component
@Profile("product")
public class AppConfig extends AbstractConfig {

    @Override
    protected String getMode() {
        return "product";
    }

    @Bean
    CommandLineRunner addTestData(final BoardRepository b, final ArticleRepository a) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
            	
            	Board board = b.save(new Board("free", "자유게시판"));
            	
            	Integer i = 0;
            	while(++i < 100) {
            	    a.save(new Article("윤재철", "안녕하세요" + i, "반갑습니다" + i, board));    
            	}
            }
        };
    }

}
