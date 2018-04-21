package pl.mateusz.testblog;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    @Test
    public void generateBcryptPassword(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("adminadmin"));
        System.out.println(bCryptPasswordEncoder.encode("testtest"));
    }
}
