package pl.mateusz.testblog.models.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date added = new Date();

    @OneToMany(mappedBy = "post",cascade = CascadeType.PERSIST)
    //@JoinColumn(name = "postId")
    List<PostComment> comments = new ArrayList<>();

    public Post() {
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

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
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
                ", added=" + added +
                '}';
    }
}