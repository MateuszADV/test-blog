package pl.mateusz.testblog.configurate;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.mateusz.testblog.models.dtos.PostDto;
import pl.mateusz.testblog.models.dtos.TagDto;
import pl.mateusz.testblog.models.entities.Post;
import pl.mateusz.testblog.models.entities.Tag;

@Configuration
public class BasicConfiguration {

//    @Bean
//    public ModelMapper modelMapper(){
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.createTypeMap(Post.class, PostDto.class)
//                .addMapping(pst -> pst.getUser().getId(), PostDto::setIdOfUser)
//                .addMapping(p -> p.getAuditEntity().getAdded(), PostDto::setCreated);
//
//        return modelMapper;
//    }

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Post.class, PostDto.class)
                .addMapping(pst -> pst.getUser().getId(), PostDto::setIdOfUser)
                .addMapping(p -> p.getAuditEntity().getAdded(), PostDto::setCreated);
        modelMapper.createTypeMap(Tag.class, TagDto.class)
                .addMapping(t -> t.getEntity().getAdded(), TagDto::setCreated);
        return modelMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
