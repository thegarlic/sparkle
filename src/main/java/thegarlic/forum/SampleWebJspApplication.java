package thegarlic.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;
import thegarlic.forum.domain.Post;
import thegarlic.forum.repository.PostRepository;

import java.util.Date;

@SpringBootApplication
public class SampleWebJspApplication extends SpringBootServletInitializer {

    //FIXME 임시데이터 이므로 삭제필요
    @Autowired
    PostRepository postRepository;

    @Override
    protected WebApplicationContext run(SpringApplication application) {

        //FIXME 임시 데이터는 나중에 삭제
        Date now = new Date();
        postRepository.save(new Post(now, "title1", "text1"));
        postRepository.save(new Post(now, "title2", "text2"));
        postRepository.save(new Post(now, "title3", "text3"));

        return super.run(application);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SampleWebJspApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleWebJspApplication.class, args);
	}
}