package pl.mateusz.testblog.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.testblog.models.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByName(String name);
    //Optional<User> findById(Long userId);
    Optional<User> findByPassword(String password);
}
