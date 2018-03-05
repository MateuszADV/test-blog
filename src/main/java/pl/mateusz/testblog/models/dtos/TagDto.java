package pl.mateusz.testblog.models.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import pl.mateusz.testblog.models.entities.Post;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class TagDto {
    private Long id;
    private String tagName;
    private Date created;

    @JsonBackReference
    private Set<PostDto> postSet;

}
