package pl.mateusz.testblog.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tagName;

   // private Date added = new Date();

    @Embedded
    private AuditEntity entity = new AuditEntity();

    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();


    public AuditEntity getEntity() {
        return entity;
    }

    public void setEntity(AuditEntity entity) {
        this.entity = entity;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

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

}
