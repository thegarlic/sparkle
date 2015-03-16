package thegarlic.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import thegarlic.forum.domain.Post;
import thegarlic.forum.repository.PostRepository;

@Controller
public class PostController {
    @Autowired
    PostRepository postRepository;

    @RequestMapping(value="/post/{postId}",method = RequestMethod.GET)
    public String readPost(long postId, Model model){
        Post post = postRepository.findOne(postId);
        model.addAttribute(post);
        return "post";
    }

    @RequestMapping(value="/post/{postId}", method=RequestMethod.PUT)
    public void modifyPost(Post post){

    }
}
