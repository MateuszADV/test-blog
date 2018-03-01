package pl.mateusz.testblog.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mateusz.testblog.models.dtos.UserDto;
import pl.mateusz.testblog.models.entities.User;
import pl.mateusz.testblog.models.repositories.UserRepository;

@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String registerGet(ModelMap modelMap){
        modelMap.addAttribute("userDto",new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("userDto")UserDto userDto,
                               @RequestParam String repeatPassword,
                               ModelMap modelMap){

        boolean checkLogin = userRepository.existsByLogin(userDto.getLogin());

        if (userDto.getPassword().equals(repeatPassword)){
            userRepository.save(new User(userDto));
            return "login";
        }
        if (checkLogin){
            modelMap.addAttribute("existLogin", "Podany login już istnieje");
            return "register";
        }
        else{
            modelMap.addAttribute("repeatPassword","Hasła są inne");
        }



        return "register";
    }



    @GetMapping("/login")
    public String loginGet(){

        return "login";
    }
}
