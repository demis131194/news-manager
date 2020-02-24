package com.epam.lab.configuration;

import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.repository.specification.author.FindAuthorsByNameJpaSpecification;
import com.epam.lab.repository.specification.author.FindAuthorsBySurnameJpaSpecification;
import com.epam.lab.repository.specification.news.FindNewsBySearchCriteriaJpaSpecification;
import com.epam.lab.repository.specification.news.SearchCriteria;
import com.epam.lab.repository.specification.tag.FindTagByNameJpaSpecification;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

import static com.epam.lab.TestObjects.*;
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
        configNewsMockRepo(mockNewsRepository);
        return mockNewsRepository;
    }

    private void configTagMockRepo(TagRepository mockTagRepo) {
        configTagFindById(mockTagRepo);

        Mockito.when(mockTagRepo.save(CREATE_TEST_TAG_4)).thenReturn(CREATED_TAG_4);
        Mockito.when(mockTagRepo.save(UPDATE_TEST_TAG_5)).thenReturn(UPDATE_TEST_TAG_5);
        Mockito.when(mockTagRepo.delete(Mockito.longThat(id -> id>0 && id<=3))).thenReturn(true);
        Mockito.when(mockTagRepo.findAll()).thenReturn(Arrays.asList(EXPECTED_TAG_1, EXPECTED_TAG_2, EXPECTED_TAG_3));
        Mockito.when(mockTagRepo.countAll()).thenReturn(EXPECTED_COUNT_ALL_TAGS);
        Mockito.when(mockTagRepo.findAllBySpecification(Mockito.any(FindTagByNameJpaSpecification.class)))
                .thenReturn(Arrays.asList(EXPECTED_TAG_1, EXPECTED_TAG_2, EXPECTED_TAG_3));
    }

    private void configAuthorMockRepo(AuthorRepository mockAuthorRepo) {
        configAuthorFindById(mockAuthorRepo);
        configAuthorFindByNameSpecification(mockAuthorRepo);
        configAuthorFindBySurnameSpecification(mockAuthorRepo);

        Mockito.when(mockAuthorRepo.save(CREATE_TEST_AUTHOR_4)).thenReturn(CREATED_AUTHOR_4);
        Mockito.when(mockAuthorRepo.save(UPDATE_TEST_AUTHOR_5)).thenReturn(UPDATE_TEST_AUTHOR_5);
        Mockito.when(mockAuthorRepo.delete(Mockito.longThat(id -> id>0 && id<=3))).thenReturn(true);
        Mockito.when(mockAuthorRepo.findAll()).thenReturn(Arrays.asList(EXPECTED_AUTHOR_1, EXPECTED_AUTHOR_2, EXPECTED_AUTHOR_3));
        Mockito.when(mockAuthorRepo.countAll()).thenReturn(EXPECTED_COUNT_ALL_AUTHORS);
    }

    private void configNewsMockRepo(NewsRepository mockNewsRepo) {
        configNewsFindById(mockNewsRepo);
        configNewsFindBySearchSpecification(mockNewsRepo);

        Mockito.when(mockNewsRepo.save(CREATE_TEST_NEWS_4)).thenReturn(CREATED_NEWS_4);
        Mockito.when(mockNewsRepo.save(UPDATE_TEST_NEWS_5)).thenReturn(UPDATE_TEST_NEWS_5);
        Mockito.when(mockNewsRepo.delete(Mockito.longThat(id -> id>0 && id<=3))).thenReturn(true);
        Mockito.when(mockNewsRepo.findAll()).thenReturn(Arrays.asList(EXPECTED_NEWS_1, EXPECTED_NEWS_2, EXPECTED_NEWS_3));
        Mockito.when(mockNewsRepo.countAll()).thenReturn(EXPECTED_COUNT_ALL_NEWS);

    }


    private void configTagFindById(TagRepository mockTagRepo) {
        Mockito.when(mockTagRepo.findById(EXPECTED_TAG_1.getId())).thenReturn(EXPECTED_TAG_1);
        Mockito.when(mockTagRepo.findById(EXPECTED_TAG_2.getId())).thenReturn(EXPECTED_TAG_2);
        Mockito.when(mockTagRepo.findById(EXPECTED_TAG_3.getId())).thenReturn(EXPECTED_TAG_3);
    }

    private void configAuthorFindById(AuthorRepository mockAuthorRepo) {
        Mockito.when(mockAuthorRepo.findById(EXPECTED_AUTHOR_1.getId())).thenReturn(EXPECTED_AUTHOR_1);
        Mockito.when(mockAuthorRepo.findById(EXPECTED_AUTHOR_2.getId())).thenReturn(EXPECTED_AUTHOR_2);
        Mockito.when(mockAuthorRepo.findById(EXPECTED_AUTHOR_3.getId())).thenReturn(EXPECTED_AUTHOR_3);
    }

    private void configAuthorFindByNameSpecification(AuthorRepository mockAuthorRepo) {
        Mockito.when(mockAuthorRepo.findAllBySpecification(Mockito.any(FindAuthorsByNameJpaSpecification.class))).thenAnswer(invocation -> {
            FindAuthorsByNameJpaSpecification specification = invocation.getArgument(0);
            String specificationAuthorName = specification.getAuthorName();

            if (EXPECTED_AUTHOR_1.getName().equals(specificationAuthorName)) {
                return Collections.singletonList(EXPECTED_AUTHOR_1);
            } else if (EXPECTED_AUTHOR_2.getName().equals(specificationAuthorName)) {
                return Collections.singletonList(EXPECTED_AUTHOR_2);
            } else if (EXPECTED_AUTHOR_3.getName().equals(specificationAuthorName)) {
                return Collections.singletonList(EXPECTED_AUTHOR_3);
            } else {
                return Collections.emptyList();
            }
        });
    }

    private void configAuthorFindBySurnameSpecification(AuthorRepository mockAuthorRepo) {
        Mockito.when(mockAuthorRepo.findAllBySpecification(Mockito.any(FindAuthorsBySurnameJpaSpecification.class))).thenAnswer(invocation -> {
            FindAuthorsBySurnameJpaSpecification specification = invocation.getArgument(0);
            String specificationAuthorSurname = specification.getAuthorSurname();

            if (EXPECTED_AUTHOR_1.getSurname().equals(specificationAuthorSurname)) {
                return Collections.singletonList(EXPECTED_AUTHOR_1);
            } else if (EXPECTED_AUTHOR_2.getSurname().equals(specificationAuthorSurname)) {
                return Collections.singletonList(EXPECTED_AUTHOR_2);
            } else if (EXPECTED_AUTHOR_3.getSurname().equals(specificationAuthorSurname)) {
                return Collections.singletonList(EXPECTED_AUTHOR_3);
            } else {
                return Collections.emptyList();
            }
        });
    }

    private void configNewsFindById(NewsRepository mockNewsRepo) {
        Mockito.when(mockNewsRepo.findById(EXPECTED_NEWS_1.getId())).thenReturn(EXPECTED_NEWS_1);
        Mockito.when(mockNewsRepo.findById(EXPECTED_NEWS_2.getId())).thenReturn(EXPECTED_NEWS_2);
        Mockito.when(mockNewsRepo.findById(EXPECTED_NEWS_3.getId())).thenReturn(EXPECTED_NEWS_3);
    }

    private void configNewsFindBySearchSpecification(NewsRepository mockNewsRepo) {
        Mockito.when(mockNewsRepo.findAllBySpecification(Mockito.any(FindNewsBySearchCriteriaJpaSpecification.class))).thenAnswer(invocation -> {
            FindNewsBySearchCriteriaJpaSpecification specification = invocation.getArgument(0);
            SearchCriteria specSearchCriteria = specification.getSearchCriteria();

            if (SEARCH_CRITERIA_1.equals(specSearchCriteria)) {
                return Collections.singletonList(EXPECTED_NEWS_1);
            }
            return Collections.emptyList();
        });
    }
}
