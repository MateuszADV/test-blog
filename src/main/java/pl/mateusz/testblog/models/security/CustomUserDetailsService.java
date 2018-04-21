package pl.mateusz.testblog.models.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mateusz.testblog.models.entities.User;
import pl.mateusz.testblog.models.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;


    //wyciąganie name z bazy danych i sprawdzanie hasła z bazy danych
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> nameOptional = userRepository.findByName(username);
        if(!nameOptional.isPresent()){
            throw new UsernameNotFoundException("Nie znaleziono użytkownika "+username);
        }

        User user = nameOptional.get();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if("admin".equals(user.getName())){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

//        CustomUserDetails userDetails = new CustomUserDetails
//                (username, user.getPassword(), true, true, true, true, grantedAuthorities);
//        userDetails.setEmail(user.getEmail());

        UserDetails userDetails = new CustomUserDetails
                (username, user.getPassword(), true, true, true, true
                        , grantedAuthorities,user.getEmail());


        return userDetails;
    }
}
