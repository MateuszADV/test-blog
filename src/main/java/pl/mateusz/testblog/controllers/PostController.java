package pl.mateusz.testblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mateusz.testblog.models.entities.Post;
import pl.mateusz.testblog.models.entities.PostComment;
import pl.mateusz.testblog.models.repositories.PostRepository;

import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/post/{postId}")
    public String postIdGet(@PathVariable Long postId, ModelMap modelMap){
        Optional<Post> postOptional = postRepository.findById(postId);

        postOptional.ifPresent(post -> {
            modelMap.addAttribute("post",post);
        });

        return "post";
    }

    @PostMapping("post/addComment")
    public String addComentPost(@RequestParam String commentBody,
                                @RequestParam Long postId,
                                ModelMap modelMap){
        PostComment postComment = new PostComment();
        postComment.setComment(commentBody);

        Optional<Post> postOptional = postRepository.findById(postId);
        postOptional.ifPresent(post -> {
            post.addComent(postComment);
            postRepository.save(post);
        });

        return "redirect:/post/" + postId;
    }
}
