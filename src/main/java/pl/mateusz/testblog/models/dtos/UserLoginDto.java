package pl.mateusz.testblog.models.dtos;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class UserLoginDto {

    private Long id;
    private String name;
    private String lastname;
    private int age;
    private String email;
    private String login;



}
