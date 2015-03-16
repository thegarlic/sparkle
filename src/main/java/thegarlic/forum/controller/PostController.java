package thegarlic.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import thegarlic.forum.domain.Post;
import thegarlic.forum.repository.PostRepository;

import java.util.Date;

@Controller
public class PostController {
    @Autowired
    PostRepository postRepository;


    @RequestMapping(value="/post", method=RequestMethod.GET)
    public String registerPost(){
        return "registerPost";
    }


    @RequestMapping(value="/post", method=RequestMethod.POST)
    public String registerPost(Post post){
        postRepository.save(post);
        return "redirect:/post/" + post.getId();
    }


    @RequestMapping(value="/post/{postId}",method = RequestMethod.GET)
    public String readPost(@PathVariable long postId, Model model){
        Post post = postRepository.findOne(postId);
        model.addAttribute(post);
        return "viewPost";
    }

    @RequestMapping(value="/post/{postId}", method=RequestMethod.DELETE)
    public String deletePost(@PathVariable long postId){
        postRepository.delete(postId);
        return "redirect:/post";
    }

    @RequestMapping(value="/post/{postId}/modify", method=RequestMethod.GET)
    public String modifyPost(@PathVariable long postId, Model model){
        Post post = postRepository.findOne(postId);
        model.addAttribute(post);
        return "modifyPost";
    }

    @RequestMapping(value="/post/{postId}/modify", method=RequestMethod.POST)
    public String modifyPost(Post post){
        post.setWriteDate(new Date());
        postRepository.save(post);
        return "redirect:/post/"+post.getId();
    }

}
