package com.epam.lab.configuration;

import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.TagRepository;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;
import static com.epam.lab.configuration.DbTestObjects.*;

@Configuration
@ComponentScan("com.epam.lab.service")
public class ServiceTestConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }

    @Bean
    public TagRepository tagRepository() {
        TagRepository mockTagRepo = Mockito.mock(TagRepository.class);
        Mockito.when(mockTagRepo.findById(1)).thenReturn(EXPECTED_TAG_1);
        Mockito.when(mockTagRepo.findById(2)).thenReturn(EXPECTED_TAG_2);
        Mockito.when(mockTagRepo.findById(3)).thenReturn(EXPECTED_TAG_3);
        return mockTagRepo;
    }

    @Bean
    public AuthorRepository authorRepository() {
        AuthorRepository mockAuthorRepo = Mockito.mock(AuthorRepository.class);
        return mockAuthorRepo;
    }

    @Bean
    public NewsRepository newsRepository() {
        NewsRepository mockNewsRepository = Mockito.mock(NewsRepository.class);
        return mockNewsRepository;
    }
}
