package pl.mateusz.testblog.controllers;

import org.modelmapper.ModelMapper;
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
import pl.mateusz.testblog.models.forms.LoginForm;
import pl.mateusz.testblog.models.forms.RegisterForm;
import pl.mateusz.testblog.models.repositories.UserRepository;
import pl.mateusz.testblog.service.UserSessionService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    public UserController(UserRepository userRepository, UserSessionService userSessionService) {
        this.userRepository = userRepository;
        this.userSessionService = userSessionService;
    }

    private UserRepository userRepository;
    private UserSessionService userSessionService;



    @GetMapping("/register")
    public String registerGet(ModelMap modelMap){
        modelMap.addAttribute("userDto",new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("userDto")@Valid RegisterForm registerForm, BindingResult bindingResult,
                               @RequestParam String repeatPassword,
                               ModelMap modelMap){

        if (bindingResult.hasErrors()){

            return "register";
        }

        User user = (new ModelMapper()).map(registerForm, User.class);
        userRepository.save(user);


        return "index";
    }

    //---------------------------------------------------------------------------------------------

    @GetMapping("/login")
    public String loginGet(ModelMap modelMap){

        modelMap.addAttribute("loginForm", new LoginForm());

        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute("loginForm")@Valid LoginForm loginForm, BindingResult bindingResult,
                               ModelMap modelMap) {

        boolean logged = userSessionService.loginUser(loginForm.getName(),loginForm.getPassword());
        if(!logged){
            bindingResult.addError(new ObjectError("name", "Użytkownik nie istnieje"));
        }

        if(bindingResult.hasErrors()) {
            return "login";
        }

        modelMap.addAttribute("loggedUser", logged);

        return "index";
    }
}
