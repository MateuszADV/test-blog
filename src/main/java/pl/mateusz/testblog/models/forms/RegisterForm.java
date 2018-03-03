package pl.mateusz.testblog.models.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class RegisterForm {
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
}
