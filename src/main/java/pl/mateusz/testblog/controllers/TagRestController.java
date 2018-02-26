package pl.mateusz.testblog.controllers;

import javafx.geometry.Pos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.mateusz.testblog.models.dtos.TagDto;
import pl.mateusz.testblog.models.entities.Post;
import pl.mateusz.testblog.models.entities.Tag;
import pl.mateusz.testblog.models.repositories.PostRepository;
import pl.mateusz.testblog.models.repositories.TagRepository;

@RestController
public class TagRestController {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    PostRepository postRepository;



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

    @PutMapping("/tag/addPost")
    public ResponseEntity<Post> addTagToPost(@RequestParam Long tagId,
                                            @RequestParam Long postId){
        Tag tag = tagRepository.getOne(tagId);
        Post post = postRepository.getOne(postId);

        post.getTags().add(tag);
        postRepository.save(post);

        return ResponseEntity
                .ok()
                .body(post);
    }
}
