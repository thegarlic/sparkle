package thegarlic.forum.config;

import lombok.extern.slf4j.Slf4j;

import org.joda.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import thegarlic.forum.domain.Article;
import thegarlic.forum.repository.ArticleRepository;

@Slf4j
@Component
@Profile("product")
public class AppConfig extends AbstractConfig {

    @Override
    protected String getMode() {
        return "product";
    }

    @Bean
    CommandLineRunner addTestData(final ArticleRepository r) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                
                r.save(new Article(LocalDateTime.now(), "윤재철", "안녕하세요1", "반갑습니다1."));
                r.save(new Article(LocalDateTime.now(), "윤재철", "안녕하세요2", "반갑습니다2."));
                r.save(new Article(LocalDateTime.now(), "윤재철", "안녕하세요3", "반갑습니다3."));
                r.save(new Article(LocalDateTime.now(), "윤재철", "안녕하세요4", "반갑습니다4."));
            }
        };
    }

}
