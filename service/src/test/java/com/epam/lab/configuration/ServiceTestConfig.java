package com.epam.lab.configuration;

import com.epam.lab.model.Tag;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.repository.specification.Specification;
import com.epam.lab.repository.specification.author.FindAuthorByNewsIdSpecification;
import com.epam.lab.repository.specification.tag.FindTagByNameSpecification;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

import static com.epam.lab.configuration.TestObjects.*;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

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
        configTagMockRepo(mockTagRepo);
        return mockTagRepo;
    }

    @Bean
    public AuthorRepository authorRepository() {
        AuthorRepository mockAuthorRepo = Mockito.mock(AuthorRepository.class);
        configAuthorMockRepo(mockAuthorRepo);
        return mockAuthorRepo;
    }

    @Bean
    public NewsRepository newsRepository() {
        NewsRepository mockNewsRepository = Mockito.mock(NewsRepository.class);
        return mockNewsRepository;
    }

    private void configTagMockRepo(TagRepository mockTagRepo) {
        Mockito.when(mockTagRepo.findById(EXPECTED_TAG_1.getId())).thenReturn(EXPECTED_TAG_1);
        Mockito.when(mockTagRepo.findById(EXPECTED_TAG_2.getId())).thenReturn(EXPECTED_TAG_2);
        Mockito.when(mockTagRepo.findById(EXPECTED_TAG_2.getId())).thenReturn(EXPECTED_TAG_3);
        Mockito.when(mockTagRepo.create(CREATE_TEST_TAG_4)).thenReturn(INIT_TEST_ID + 3);
        Mockito.when(mockTagRepo.findById(INIT_TEST_ID + 3)).thenReturn(new Tag(INIT_TEST_ID + 3, CREATE_TEST_TAG_4.getName()));
        Mockito.when(mockTagRepo.update(UPDATE_TEST_TAG_5)).thenReturn(true);
        Mockito.when(mockTagRepo.findById(UPDATE_TEST_TAG_5.getId())).thenReturn(UPDATE_TEST_TAG_5);
        Mockito.when(mockTagRepo.delete(EXPECTED_DTO_TAG_1.getId())).thenReturn(true);
        Mockito.when(mockTagRepo.delete(EXPECTED_DTO_TAG_2.getId())).thenReturn(true);
        Mockito.when(mockTagRepo.delete(EXPECTED_DTO_TAG_3.getId())).thenReturn(true);
        Specification specification = new FindTagByNameSpecification(EXPECTED_DTO_TAG_1.getName());
        Mockito.when(mockTagRepo.findBySpecification(specification)).thenReturn(Collections.singletonList(EXPECTED_TAG_1));
        Mockito.when(mockTagRepo.countAll()).thenReturn(EXPECTED_COUNT_ALL_TAGS);
        Mockito.when(mockTagRepo.findAll()).thenReturn(Arrays.asList(EXPECTED_TAG_1, EXPECTED_TAG_2, EXPECTED_TAG_3));
    }

    private void configAuthorMockRepo(AuthorRepository mockAuthorRepo) {
        Mockito.when(mockAuthorRepo.create(CREATE_TEST_AUTHOR_4)).thenReturn(CREATE_TEST_DTO_AUTHOR_4.getId());
        Mockito.when(mockAuthorRepo.update(UPDATE_TEST_AUTHOR_5)).thenReturn(true);
        Mockito.when(mockAuthorRepo.delete(INIT_TEST_ID)).thenReturn(true);
        Mockito.when(mockAuthorRepo.findById(INIT_TEST_ID)).thenReturn(EXPECTED_AUTHOR_1);
        Mockito.when(mockAuthorRepo.findAll()).thenReturn(Arrays.asList(EXPECTED_AUTHOR_1, EXPECTED_AUTHOR_2, EXPECTED_AUTHOR_3));
        Mockito.when(mockAuthorRepo.countAll()).thenReturn(EXPECTED_COUNT_ALL_AUTHORS);
        Specification specification = new FindAuthorByNewsIdSpecification(INIT_TEST_ID);
        Mockito.when(mockAuthorRepo.findBySpecification(specification)).thenReturn(Collections.singletonList(EXPECTED_AUTHOR_1));
    }
}
