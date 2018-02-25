package pl.mateusz.testblog.controllers;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mateusz.testblog.models.entities.Post;
import pl.mateusz.testblog.models.repositories.PostRepository;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/search")
    public String searchGet(@RequestParam String searchPhrase, ModelMap modelMap){
        List<Post> posts = postRepository.findAllByTitleContainsOrContentContains(searchPhrase, searchPhrase);
        modelMap.addAttribute("posts", posts);
        modelMap.addAttribute("searchPhrase", searchPhrase);
        return "posts";
    }

}
