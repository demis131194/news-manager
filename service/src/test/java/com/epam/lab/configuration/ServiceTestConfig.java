package com.epam.lab.configuration;

import com.epam.lab.dto.NewsTo;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.repository.specification.Specification;
import com.epam.lab.repository.specification.author.FindAuthorByNewsIdSpecification;
import com.epam.lab.repository.specification.news.FindNewsBySearchCriteriaSpecification;
import com.epam.lab.repository.specification.tag.FindTagByNameSpecification;
import com.epam.lab.repository.specification.tag.FindTagsByNewsIdSpecification;
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
        configNewsMockRepo(mockNewsRepository);
        return mockNewsRepository;
    }

    private void configTagMockRepo(TagRepository mockTagRepo) {
        Mockito.when(mockTagRepo.findById(EXPECTED_TAG_1.getId())).thenReturn(EXPECTED_TAG_1);
        Mockito.when(mockTagRepo.findById(EXPECTED_TAG_2.getId())).thenReturn(EXPECTED_TAG_2);
        Mockito.when(mockTagRepo.findById(EXPECTED_TAG_2.getId())).thenReturn(EXPECTED_TAG_3);
        Mockito.when(mockTagRepo.create(CREATE_TEST_TAG_4)).thenReturn(CREATE_DTO_TAG_4.getId());
        Mockito.when(mockTagRepo.findById(CREATE_DTO_TAG_4.getId())).thenReturn(new Tag(CREATE_DTO_TAG_4.getId(), CREATE_TEST_TAG_4.getName()));
        Mockito.when(mockTagRepo.update(UPDATE_TEST_TAG_5)).thenReturn(true);
        Mockito.when(mockTagRepo.findById(UPDATE_TEST_TAG_5.getId())).thenReturn(UPDATE_TEST_TAG_5);
        Mockito.when(mockTagRepo.delete(Mockito.anyLong())).thenReturn(true);

        Specification specification = new FindTagByNameSpecification(EXPECTED_DTO_TAG_1.getName());
        Mockito.when(mockTagRepo.findBySpecification(specification)).thenReturn(Collections.singletonList(EXPECTED_TAG_1));

        Specification specification_1 =  new FindTagsByNewsIdSpecification(EXPECTED_DTO_NEWS_1.getId());
        Mockito.when(mockTagRepo.findBySpecification(specification_1)).thenReturn(Arrays.asList(EXPECTED_TAG_1, EXPECTED_TAG_2));

        Specification specification_2 =  new FindTagsByNewsIdSpecification(EXPECTED_DTO_NEWS_2.getId());
        Mockito.when(mockTagRepo.findBySpecification(specification_2)).thenReturn(Arrays.asList(EXPECTED_TAG_1, EXPECTED_TAG_3));

        Specification specification_3 =  new FindTagsByNewsIdSpecification(EXPECTED_DTO_NEWS_3.getId());
        Mockito.when(mockTagRepo.findBySpecification(specification_3)).thenReturn(Collections.singletonList(EXPECTED_TAG_3));

        Specification specification_4 =  new FindTagsByNewsIdSpecification(CREATE_TEST_DTO_NEWS_4.getId());
        Mockito.when(mockTagRepo.findBySpecification(specification_4)).thenReturn(Collections.singletonList(EXPECTED_TAG_3));

        Specification specification_5 =  new FindTagsByNewsIdSpecification(UPDATE_TEST_DTO_NEWS_5.getId());
        Mockito.when(mockTagRepo.findBySpecification(specification_5)).thenReturn(Arrays.asList(EXPECTED_TAG_2, EXPECTED_TAG_3));

        Mockito.when(mockTagRepo.countAll()).thenReturn(EXPECTED_COUNT_ALL_TAGS);
        Mockito.when(mockTagRepo.findAll()).thenReturn(Arrays.asList(EXPECTED_TAG_1, EXPECTED_TAG_2, EXPECTED_TAG_3));
    }

    private void configAuthorMockRepo(AuthorRepository mockAuthorRepo) {
        Mockito.when(mockAuthorRepo.create(CREATE_TEST_AUTHOR_4)).thenReturn(CREATE_TEST_DTO_AUTHOR_4.getId());
        Mockito.when(mockAuthorRepo.update(UPDATE_TEST_AUTHOR_5)).thenReturn(true);
        Mockito.when(mockAuthorRepo.delete(INIT_TEST_ID)).thenReturn(true);
        Mockito.when(mockAuthorRepo.findById(EXPECTED_AUTHOR_1.getId())).thenReturn(EXPECTED_AUTHOR_1);
        Mockito.when(mockAuthorRepo.findById(EXPECTED_AUTHOR_2.getId())).thenReturn(EXPECTED_AUTHOR_2);
        Mockito.when(mockAuthorRepo.findById(EXPECTED_AUTHOR_3.getId())).thenReturn(EXPECTED_AUTHOR_3);
        Mockito.when(mockAuthorRepo.findById(CREATE_TEST_DTO_AUTHOR_4.getId())).thenReturn(
                new Author(CREATE_TEST_DTO_AUTHOR_4.getId(), CREATE_TEST_DTO_AUTHOR_4.getName(), CREATE_TEST_DTO_AUTHOR_4.getSurname())
        );
        Mockito.when(mockAuthorRepo.findById(UPDATE_TEST_DTO_AUTHOR_5.getId())).thenReturn(UPDATE_TEST_AUTHOR_5);
        Mockito.when(mockAuthorRepo.findAll()).thenReturn(Arrays.asList(EXPECTED_AUTHOR_1, EXPECTED_AUTHOR_2, EXPECTED_AUTHOR_3));
        Mockito.when(mockAuthorRepo.countAll()).thenReturn(EXPECTED_COUNT_ALL_AUTHORS);

        Specification specification_1 = new FindAuthorByNewsIdSpecification(EXPECTED_NEWS_1.getId());
        Mockito.when(mockAuthorRepo.findBySpecification(specification_1)).thenReturn(Collections.singletonList(EXPECTED_AUTHOR_1));

        Specification specification_2 = new FindAuthorByNewsIdSpecification(EXPECTED_NEWS_2.getId());
        Mockito.when(mockAuthorRepo.findBySpecification(specification_2)).thenReturn(Collections.singletonList(EXPECTED_AUTHOR_2));

        Specification specification_3 = new FindAuthorByNewsIdSpecification(EXPECTED_NEWS_3.getId());
        Mockito.when(mockAuthorRepo.findBySpecification(specification_3)).thenReturn(Collections.singletonList(EXPECTED_AUTHOR_3));

        Specification specification_4 = new FindAuthorByNewsIdSpecification(CREATE_TEST_DTO_NEWS_4.getId());
        Mockito.when(mockAuthorRepo.findBySpecification(specification_4)).thenReturn(Collections.singletonList(EXPECTED_AUTHOR_3));

        Specification specification_5 = new FindAuthorByNewsIdSpecification(UPDATE_TEST_DTO_NEWS_5.getId());
        Mockito.when(mockAuthorRepo.findBySpecification(specification_5)).thenReturn(Collections.singletonList(EXPECTED_AUTHOR_3));

    }

    private void configNewsMockRepo(NewsRepository mockNewsRepo) {
        Mockito.when(mockNewsRepo.create(CREATE_TEST_NEWS_4)).thenReturn(CREATE_TEST_DTO_NEWS_4.getId());
        Mockito.when(mockNewsRepo.createNewsAuthorBound(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);
        Mockito.when(mockNewsRepo.updateNewsAuthorBound(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);
        Mockito.when(mockNewsRepo.createNewsTagBound(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);
        Mockito.when(mockNewsRepo.deleteAllNewsTagBounds(Mockito.anyLong())).thenReturn(true);
        Mockito.when(mockNewsRepo.update(UPDATE_TEST_NEWS_5)).thenReturn(true);
        Mockito.when(mockNewsRepo.delete(EXPECTED_DTO_NEWS_1.getId())).thenReturn(true);
        Mockito.when(mockNewsRepo.findById(EXPECTED_DTO_NEWS_1.getId())).thenReturn(EXPECTED_NEWS_1);
        Mockito.when(mockNewsRepo.findById(EXPECTED_DTO_NEWS_2.getId())).thenReturn(EXPECTED_NEWS_2);
        Mockito.when(mockNewsRepo.findById(EXPECTED_DTO_NEWS_3.getId())).thenReturn(EXPECTED_NEWS_3);
        Mockito.when(mockNewsRepo.findById(CREATE_TEST_DTO_NEWS_4.getId())).thenReturn(new News(
                CREATE_TEST_DTO_NEWS_4.getId(), CREATE_TEST_DTO_NEWS_4.getTitle(),
                CREATE_TEST_DTO_NEWS_4.getShortText(), CREATE_TEST_DTO_NEWS_4.getFullText())
        );
        Mockito.when(mockNewsRepo.findById(UPDATE_TEST_DTO_NEWS_5.getId())).thenReturn(UPDATE_TEST_NEWS_5);
        Mockito.when(mockNewsRepo.findAll()).thenReturn(Arrays.asList(EXPECTED_NEWS_1, EXPECTED_NEWS_2, EXPECTED_NEWS_3));
        Mockito.when(mockNewsRepo.countAll()).thenReturn(EXPECTED_COUNT_ALL_NEWS);

        Specification specification = new FindNewsBySearchCriteriaSpecification(SEARCH_CRITERIA_1);
        Mockito.when(mockNewsRepo.findBySpecification(specification)).thenReturn(Collections.singletonList(EXPECTED_NEWS_1));
    }
}
