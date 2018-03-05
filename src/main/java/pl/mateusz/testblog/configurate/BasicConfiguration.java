package pl.mateusz.testblog.configurate;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mateusz.testblog.models.dtos.PostDto;
import pl.mateusz.testblog.models.entities.Post;

@Configuration
public class BasicConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Post.class, PostDto.class)
                .addMapping(pst -> pst.getUser().getId(), PostDto::setIdOfUser)
                .addMapping(p -> p.getAuditEntity().getAdded(), PostDto::setCreated);

        return modelMapper;
    }
}
