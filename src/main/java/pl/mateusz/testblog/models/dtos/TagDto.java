package pl.mateusz.testblog.models.dtos;

import pl.mateusz.testblog.models.entities.Post;

import java.util.Date;
import java.util.Set;

public class TagDto {
    private Long id;
    private String tagName;
    private Set<PostDto> postSet;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Set<PostDto> getPostSet() {
        return postSet;
    }

    public void setPostSet(Set<PostDto> postSet) {
        this.postSet = postSet;
    }
}
