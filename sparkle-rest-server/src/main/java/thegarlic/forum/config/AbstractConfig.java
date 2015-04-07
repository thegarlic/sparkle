package thegarlic.forum.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

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
        
        adapter.setDatabase(Database.H2);
        adapter.setGenerateDdl(true);
        adapter.setShowSql(true);
        
        return adapter;
    }
    
    @Bean
    private Filter SimpleCORSFilter() {
        return new Filter() {
            
            @Override
            public void doFilter(ServletRequest request, ServletResponse response,
                    FilterChain chain) throws IOException, ServletException {
                HttpServletResponse res = (HttpServletResponse)response;
                res.setHeader("Access-Control-Allow-Origin", "*");
                res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                res.setHeader("Access-Control-Max-Age", "3600");
                res.setHeader("Access-Control-Allow-Headers", "x-requested-with");
                chain.doFilter(request, res);
            }
            
            @Override public void destroy() {}
            @Override public void init(FilterConfig filterConfig) throws ServletException {}
        };
    }
    
}
