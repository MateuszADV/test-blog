package pl.mateusz.testblog.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.mateusz.testblog.models.dtos.UserDto;
import pl.mateusz.testblog.models.repositories.UserRepository;

import java.util.Optional;

@Service
@Scope(value = "sesion", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSessionService {


    private boolean logged;
    private UserDto userDto;

    @Autowired
    private UserRepository userRepository;

    public boolean loginUser(String name, String password){
        Optional<UserDto> optionalUser = userRepository.findByName(name);

        if (!optionalUser.isPresent()){
            return false;
        }

        if (optionalUser.get().getPassword().equals(password)){
            return false;
        }

        //userDto = (new ModelMapper().map())

        logged = true;
        return logged;
    }
}
