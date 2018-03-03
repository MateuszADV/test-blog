package pl.mateusz.testblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mateusz.testblog.models.dtos.UserDto;
import pl.mateusz.testblog.models.dtos.UserLoginDto;
import pl.mateusz.testblog.models.entities.User;
import pl.mateusz.testblog.models.repositories.UserRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    UserRepository userRepository;

    @GetMapping("/register")
    public String registerGet(ModelMap modelMap){
        modelMap.addAttribute("userDto",new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("userDto")@Valid UserDto userDto, BindingResult bindingResult,
                               @RequestParam String repeatPassword,
                               ModelMap modelMap){

        if (bindingResult.hasErrors()){

            return "register";
        }

        userRepository.save(new User(userDto));


        return "register";
    }

    //---------------------------------------------------------------------------------------------

    @GetMapping("/login")
    public String loginGet(ModelMap modelMap){

        modelMap.addAttribute("userLoginDto", new UserLoginDto());

        return "login";
    }

    @PostMapping("/login")
    public String registerPost(@ModelAttribute("userLoginDto")@Valid UserLoginDto userLoginDto, BindingResult bindingResult,
                               ModelMap modelMap) {

        Optional<UserDto> optionalUser = userRepository.findByName(userLoginDto.getName());

        if(!optionalUser.isPresent()){
            bindingResult.addError(
                    new ObjectError("name","Użytkownik ni eistnieje"));
        }

        if(bindingResult.hasErrors()) {

            return "login";
        }


        return "index";
    }
}
