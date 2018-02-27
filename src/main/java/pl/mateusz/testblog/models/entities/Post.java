package pl.mateusz.testblog.models.entities;

import javax.persistence.*;
import java.util.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;

   /* @Temporal(TemporalType.TIMESTAMP)
    private Date added = new Date();*/

   @Embedded
   private AuditEntity auditEntity = new AuditEntity();

    @OneToMany(mappedBy = "post",cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    //@JoinColumn(name = "postId")
    List<PostComment> comments = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "mapPostTag",
            joinColumns = {@JoinColumn(name = "postId")},
            inverseJoinColumns = {@JoinColumn(name = "tagId")})
    private Set<Tag> tags = new HashSet<>();

    public AuditEntity getAuditEntity() {
        return auditEntity;
    }

    public void setAuditEntity(AuditEntity auditEntity) {
        this.auditEntity = auditEntity;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Post() {
    }

    public Post(String title, String content, AuditEntity auditEntity, List<PostComment> comments) {
        this.title = title;
        this.content = content;
        this.auditEntity = auditEntity;
        this.comments = comments;
    }

    public Post(String title, String content, AuditEntity auditEntity) {
        this.title = title;
        this.content = content;
        this.auditEntity = auditEntity;
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public List<PostComment> getComments() {
        return comments;
    }

    public void setComments(List<PostComment> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //Dodawanie posta
    public void addComent(PostComment postComment){
        comments.add(postComment);
        postComment.setPost(this);
    }


    //usuwanie posta
    public void removeComment(PostComment postComment){
        comments.remove(postComment);
        postComment.setPost(null);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", added=" +
                '}';
    }
}
