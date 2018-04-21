package pl.mateusz.testblog.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mateusz.testblog.models.dtos.PostCommentDto;
import pl.mateusz.testblog.models.dtos.PostDto;
import pl.mateusz.testblog.models.entities.Post;
import pl.mateusz.testblog.models.entities.PostComment;
import pl.mateusz.testblog.models.entities.User;
import pl.mateusz.testblog.models.repositories.PostRepository;
import pl.mateusz.testblog.models.repositories.UserRepository;
import pl.mateusz.testblog.service.PostService;

import java.util.Optional;

@RestController
public class PostRestControoller {

    PostRepository postRepository;
    UserRepository userRepository;
    PostService postService;

    @Autowired
    public PostRestControoller(PostRepository postRepository, UserRepository userRepository, PostService postService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postService = postService;
    }

    @PostMapping("/api/post/{postId}/comments")
    public ResponseEntity<PostCommentDto> addPostComment(@RequestParam String comments,
                                                         @PathVariable Long postId){

        Optional<Post> postOptional = postRepository.findById(postId);
        PostComment postComment = new PostComment();

        postComment.setComment(comments);

        Post post = postOptional.get();

        post.addComent(postComment);
        postRepository.save(post);

        PostComment savedComment = post.getComments().get(post.getComments().size()-1);

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(PostComment.class, PostCommentDto.class)
                .addMapping(pc -> pc.getAudit().getAdded(), PostCommentDto :: setAdded);

        //return ResponseEntity.ok().body((modelMapper.map(postComment, PostCommentDto.class)));

        return ResponseEntity.ok().body(modelMapper.map(savedComment, PostCommentDto.class));
    }

    @PostMapping("api/post")
    public ResponseEntity<PostDto> addPosy (@RequestParam String titlePost,
                                            @RequestParam String content,
                                            @RequestParam Long userId){

        PostDto post = postService.createPost(titlePost, content, userId);

        return ResponseEntity.ok().body(post);
    }

}
