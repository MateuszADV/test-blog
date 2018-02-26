package pl.mateusz.testblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mateusz.testblog.models.entities.Tag;
import pl.mateusz.testblog.models.repositories.TagRepository;

@RestController
public class TagRestController {

    TagRepository tagRepository;

    @Autowired
    public TagRestController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @RequestMapping(value = "/tag" ,method = RequestMethod.POST)
    public ResponseEntity<Tag> createTag(@RequestParam String tagName){
        Tag tag = new Tag();
        tag.setTagName(tagName);
        tagRepository.save(tag);

        return ResponseEntity
                .ok()
                .body(tag);
    }
}
