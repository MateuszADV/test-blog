package pl.mateusz.testblog.models.dtos;

import pl.mateusz.testblog.models.entities.AuditEntity;
import pl.mateusz.testblog.models.entities.Post;

import java.util.Date;

public class PostCommentDto {

    private Long id;
    private String comments;
    private Date added;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

}
