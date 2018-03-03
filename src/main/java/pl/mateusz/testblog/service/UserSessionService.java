package pl.mateusz.testblog.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.mateusz.testblog.models.dtos.UserDto;
import pl.mateusz.testblog.models.entities.User;
import pl.mateusz.testblog.models.repositories.UserRepository;

import java.util.Optional;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSessionService {


    private boolean logged;
    private UserDto userDto;

    @Autowired
    private UserRepository userRepository;

    public boolean loginUser(String name, String password){
        Optional<User> optionalUser = userRepository.findByName(name);

        if (!optionalUser.isPresent()){
            return false;
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(password)){
            return false;
        }

        userDto = (new ModelMapper().map(user, UserDto.class));

        logged = true;
        return logged;
    }
}
