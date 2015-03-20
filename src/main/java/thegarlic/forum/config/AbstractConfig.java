package thegarlic.forum.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Slf4j
@Configuration
public abstract class AbstractConfig {


    @Bean
    protected abstract String getMode();

    @Bean
    private CommandLineRunner showMode(final String mode) {
        return new CommandLineRunner() {

            @Override
            public void run(String... args) throws Exception {
                log.debug("running mode is {} !!!", mode);
            }
        };
    }
    
    @Bean
    private JpaVendorAdapter jpaVenderAdapter() {
        
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        
        adapter.setDatabase(Database.DB2);
        adapter.setGenerateDdl(true);
        adapter.setShowSql(true);
        
        return adapter;
    }
    
}
