package thegarlic.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;
import thegarlic.forum.domain.Post;
import thegarlic.forum.repository.PostRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application {

    //FIXME 임시데이터 이므로 삭제필요
    PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {

        this.postRepository = postRepository;

        //FIXME 임시 데이터는 나중에 삭제
        Date now = new Date();
        List<Post> posts = new ArrayList<>();
        posts.add(postRepository.save(new Post(now,"aa", "title1", "text1")));
        posts.add(postRepository.save(new Post(now,"bb", "title2", "text2")));
        posts.add(postRepository.save(new Post(now,"cc", "title3", "text3")));
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}