package pl.mateusz.testblog.models.dtos;

import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@ToString(exclude = "password")
public class UserDto {

    private Long id;

    @Size(min = 5, message = "Nazwa urzytkownka powinna posiadać min {min} znaków. Podana fraza ${validatedValue} nie pasuje do tych wytycznych")
    private String name;
    private String lastname;
    private int age;
    private String login;

    @Size(min = 6, max = 10)
    private String password;

    @NotBlank
    @Pattern(regexp = "[A-z0-9.]+@[a-z0-9\\-]+\\.[a-z]{2,5}",message = "podany mail jest nipoprawny")
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
