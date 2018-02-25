package pl.mateusz.testblog.models.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mateusz.testblog.models.entities.Post;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByTitleContains(String title);
    List<Post> findAllByTitleContainsOrContentContains(String title, String content);
    List<Post> findAllByTitleContains(String title, Sort sort);
}
