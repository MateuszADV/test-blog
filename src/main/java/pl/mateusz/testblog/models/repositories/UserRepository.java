package pl.mateusz.testblog.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.testblog.models.dtos.UserDto;
import pl.mateusz.testblog.models.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

//    boolean existsByName(String name);
//    boolean existsByPassword(String password);

    Optional<UserDto> findByName(String name);
}
