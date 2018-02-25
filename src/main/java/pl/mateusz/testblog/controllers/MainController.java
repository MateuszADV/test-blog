package pl.mateusz.testblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mateusz.testblog.models.entities.Post;
import pl.mateusz.testblog.models.entities.PostComment;
import pl.mateusz.testblog.models.repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private PostRepository postRepository;

    @GetMapping("/")
    public String indexGet(ModelMap modelMap){
        modelMap.addAttribute("name","Mateusz");
        return "index";
    }

    @PostMapping("/addPost")
    public String addPostPost(@RequestParam(value = "title") String titleParam,
                              @RequestParam String content,
                              ModelMap modelMap){
        System.out.println("Params: " + titleParam + ", " + content);
        Post post = new Post(titleParam,content);

        PostComment postComment = new PostComment();
        postComment.setComment(titleParam);

        post.addComent(postComment);
        //postComment.setPost(post);

        //List<PostComment> commentList = new ArrayList<>();
        //commentList.add(postComment);

        //post.setComments(commentList);

        postRepository.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/addPost")
    public String addPostGet(){
        return "addPost";
    }

    @GetMapping("/posts")
    public String postsGet(ModelMap modelMap){
        List<Post> postList = new ArrayList<>();
        Iterable<Post> postIterable = postRepository.findAll();
        for (Post post : postIterable) {
            postList.add(post);
        }

        modelMap.addAttribute("posts",postList);
        return "posts";
    }

    @GetMapping("/posts/{title}")
    public String postSerchGet(@PathVariable String title,
                               ModelMap modelMap){
        List<Post> postList = new ArrayList<>();
        Iterable<Post> postIterable = postRepository.findAllByTitleContains(title);
        for (Post post : postIterable) {
            postList.add(post);
        }

        modelMap.addAttribute("posts",postList);
        return "posts";
    }

    @GetMapping("/posts/{title}/{sortField}/{sortDirection}")
    public String postSerchGet(@PathVariable String title,
                               @PathVariable String sortField,
                               @PathVariable String sortDirection,
                               ModelMap modelMap){

        Sort.Direction direction = Sort.Direction.ASC;

        if ("desc".equals(sortDirection)){
            direction = Sort.Direction.DESC;
        }

        List<Post> postList =  postRepository.findAllByTitleContains(title, Sort.by(direction,sortField));

        modelMap.addAttribute("posts",postList);
        return "posts";
    }


    @Autowired
    public MainController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
