package pl.mateusz.testblog.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mateusz.testblog.models.dtos.PostCommentDto;
import pl.mateusz.testblog.models.dtos.PostDto;
import pl.mateusz.testblog.models.dtos.TagDto;
import pl.mateusz.testblog.models.entities.Post;
import pl.mateusz.testblog.models.entities.PostComment;
import pl.mateusz.testblog.models.entities.Tag;
import pl.mateusz.testblog.models.repositories.PostRepository;
import pl.mateusz.testblog.models.repositories.TagRepository;

import java.util.Optional;

@RestController
public class TagRestController {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;


    @RequestMapping(value = "/tag" ,method = RequestMethod.POST)
    public ResponseEntity<TagDto> createTag(@RequestParam String tagName){
        Tag tag = new Tag();
        tag.setTagName(tagName);

        tagRepository.save(tag);

       /* ModelMapper modelMapper = new ModelMapper();
        TagDto tagDto = modelMapper.map(tag, TagDto.class);*/

        TagDto tagDto = (new ModelMapper()).map(tag, TagDto.class);

        return ResponseEntity
                .ok()
                .body(tagDto);
    }

    @PutMapping("/tag/addToPost")
    public ResponseEntity<PostDto> addTagToPost(@RequestParam Long tagId,
                                             @RequestParam Long postId){
        Tag tag = tagRepository.getOne(tagId);
        Post post = postRepository.getOne(postId);

        post.getTags().add(tag);
        postRepository.save(post);


        return ResponseEntity
                .ok()
                .body((new ModelMapper()).map(post, PostDto.class));
    }

   /* @PutMapping("/post/addComment")
    public ResponseEntity<PostComment> createComment(@RequestParam String comment,
                                                        @RequestParam Long postId){
        //Post post = new Post();

        PostComment postComment = new PostComment();
        postComment.setComment(comment);

        Optional<Post> postList = postRepository.findById(postId);

        postList.ifPresent(postt -> {
            postt.addComent(postComment);
            postRepository.save(postt);
        });

//        PostDto postDto = new ModelMapper().map(post, PostDto.class);

        //PostCommentDto postCommentDto = (new ModelMapper()).map(postComment, PostCommentDto.class);



        return ResponseEntity
                .ok()
                .body(postComment);
    }*/
}
