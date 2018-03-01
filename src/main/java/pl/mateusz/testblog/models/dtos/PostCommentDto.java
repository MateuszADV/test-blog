package pl.mateusz.testblog.models.dtos;

import pl.mateusz.testblog.models.entities.AuditEntity;
import pl.mateusz.testblog.models.entities.Post;

public class PostCommentDto {

    private Long id;
    private String comments;
    private AuditEntity audit = new AuditEntity();
    private Post post;

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

    public AuditEntity getAudit() {
        return audit;
    }

    public void setAudit(AuditEntity audit) {
        this.audit = audit;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
