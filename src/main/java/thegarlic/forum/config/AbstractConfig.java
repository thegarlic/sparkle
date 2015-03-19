package thegarlic.forum.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public abstract class AbstractConfig {

	@Bean
	protected abstract String getMode();
	
    @Bean
    protected CommandLineRunner clr(String mode) {
    	return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				log.debug("running mode is {} !!!", mode);
			}
		};
    }
}
