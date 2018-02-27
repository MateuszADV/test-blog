package pl.mateusz.testblog.models.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;

    //private Date added = new Date();

    @Embedded
    private AuditEntity audit = new AuditEntity();

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
