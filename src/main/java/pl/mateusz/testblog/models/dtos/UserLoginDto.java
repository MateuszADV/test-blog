package pl.mateusz.testblog.models.dtos;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class UserLoginDto {

    @Size(min = 5, message = "Nazwa urzytkownka powinna posiadać min {min} znaków. Podana fraza ${validatedValue} nie pasuje do tych wytycznych")
    private String name;

    @Size(min = 6, max = 10)
    private String password;

}
