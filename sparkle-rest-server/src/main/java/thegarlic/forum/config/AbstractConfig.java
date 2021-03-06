package thegarlic.forum.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
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
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                HttpServletResponse res = (HttpServletResponse)response;
                HttpServletRequest req = (HttpServletRequest)request;
                
                res.setHeader("Access-Control-Allow-Origin", "*");
                res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
                res.setHeader("Access-Control-Max-Age", "3600");
                res.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
                chain.doFilter(request, res);
            }
            
            @Override public void destroy() {}
            @Override public void init(FilterConfig filterConfig) throws ServletException {}
        };
    }
    
    @Bean
    private ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        
        mapper.addConverter(new Converter<DateTime, String>() {
            @Override
            public String convert(MappingContext<DateTime, String> context) {
                return context.getSource() == null ? "" : context.getSource().withZone(DateTimeZone.UTC).toString();
            }
        });
        
        return mapper;
    }
    
}
