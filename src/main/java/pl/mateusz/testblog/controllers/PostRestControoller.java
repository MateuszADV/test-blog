package pl.mateusz.testblog.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mateusz.testblog.models.dtos.PostCommentDto;
import pl.mateusz.testblog.models.entities.Post;
import pl.mateusz.testblog.models.entities.PostComment;
import pl.mateusz.testblog.models.repositories.PostRepository;

import java.util.Optional;

@RestController
public class PostRestControoller {

    @Autowired
    PostRepository postRepository;


    @PostMapping("/api/post/{postId}/comments")
    public ResponseEntity<PostCommentDto> addPostComment(@PathVariable Long postId,
                                                         @RequestParam String comments){

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


}
