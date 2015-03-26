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
            	
            	Board board = b.save(new Board("free"));
                
                a.save(new Article("윤재철", "안녕하세요1", "반갑습니다1.", board));
                a.save(new Article("윤재철", "안녕하세요2", "반갑습니다2.", board));
                a.save(new Article("윤재철", "안녕하세요3", "반갑습니다3.", board));
                a.save(new Article("윤재철", "안녕하세요4", "반갑습니다4.", board));
            }
        };
    }

}
