package pl.mateusz.testblog.models.dtos;

import java.util.Date;

public class UserDto {

    private Long id;
    private String name;
    private String lastname;
    private int age;
    private String login;
    private String password;
    private String email;
    private Date addedUser = new Date();

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
}
