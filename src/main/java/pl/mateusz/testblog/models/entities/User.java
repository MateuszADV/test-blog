package pl.mateusz.testblog.models.entities;

import pl.mateusz.testblog.models.dtos.UserDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastname;
    private int age;
    private String email;

    private String login;
    private String password;

    private Date addedUser = new Date();

    @Embedded
    private AuditEntity audit = new AuditEntity();

    @ManyToMany(mappedBy = "users")
    private List<Post> posts = new ArrayList<>();

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAddedUser() {
        return addedUser;
    }

    public void setAddedUser(Date addedUser) {
        this.addedUser = addedUser;
    }

    public AuditEntity getAudit() {
        return audit;
    }

    public void setAudit(AuditEntity audit) {
        this.audit = audit;
    }

    public User(UserDto userDto){
        name = userDto.getName();
        lastname = userDto.getLastname();
        age = userDto.getAge();
        login = userDto.getLogin();
        password = userDto.getPassword();
        email = userDto.getEmail();
        addedUser = userDto.getAddedUser();
    }
}
