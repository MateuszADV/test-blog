package pl.mateusz.testblog.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.testblog.models.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
