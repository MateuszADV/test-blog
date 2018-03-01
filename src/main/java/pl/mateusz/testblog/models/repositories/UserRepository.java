package pl.mateusz.testblog.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.testblog.models.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByLogin(String login);
}
