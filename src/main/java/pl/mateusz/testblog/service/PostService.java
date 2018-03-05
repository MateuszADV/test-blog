package pl.mateusz.testblog.service;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mateusz.testblog.models.dtos.PostDto;
import pl.mateusz.testblog.models.entities.Post;
import pl.mateusz.testblog.models.entities.User;
import pl.mateusz.testblog.models.repositories.PostRepository;
import pl.mateusz.testblog.models.repositories.UserRepository;

import java.util.Optional;

@Service
public class PostService {

    private UserRepository userRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;


    @Autowired
    public PostService(UserRepository userRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    public PostDto createPost (String title, String content, Long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        Post post = new Post(title, content);

        optionalUser.ifPresent(u -> post.setUser(u));

        postRepository.save(post);

        //zastąpone przez BasicConfiguration
       /* ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Post.class, PostDto.class)
                .addMapping(pst -> pst.getUser().getId(), PostDto::setIdOfUser)
                .addMapping(p -> p.getAuditEntity().getAdded(), PostDto::setCreated);
*/
        return modelMapper.map(post, PostDto.class);
    }

}
