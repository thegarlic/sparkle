package thegarlic.forum.config;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

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
	CommandLineRunner addTestData(ArticleRepository r) {
		
		log.debug("addTestData");
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				
				Article a = new Article(new Date(), "author", "title", "text");
				r.save(a);
				
			}
		};
	}

}
