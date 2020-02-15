package com.epam.lab.configuration;

import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.jdbc.AuthorRepositoryImpl;
import com.epam.lab.repository.jdbc.NewsRepositoryImpl;
import com.epam.lab.repository.jdbc.TagRepositoryImpl;
import com.epam.lab.repository.jdbc.specification.author.FindAllAuthorsSpecification;
import com.epam.lab.repository.jdbc.specification.author.FindAuthorByIdSpecification;
import com.epam.lab.repository.jdbc.specification.author.FindAuthorByNewsIdSpecification;
import com.epam.lab.repository.jdbc.specification.news.FindAllNewsSpecification;
import com.epam.lab.repository.jdbc.specification.news.FindNewsByIdSpecification;
import com.epam.lab.repository.jdbc.specification.news.FindNewsBySearchCriteriaSpecification;
import com.epam.lab.repository.jdbc.specification.news.SearchCriteria;
import com.epam.lab.repository.jdbc.specification.tag.FindAllTagsSpecification;
import com.epam.lab.repository.jdbc.specification.tag.FindTagByIdSpecification;
import com.epam.lab.repository.jdbc.specification.tag.FindTagByNameSpecification;
import com.epam.lab.repository.jdbc.specification.tag.FindTagsByNewsIdSpecification;
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
    public TagRepositoryImpl tagRepository() {
        TagRepositoryImpl mockTagRepo = Mockito.mock(TagRepositoryImpl.class);
        configTagMockRepo(mockTagRepo);
        return mockTagRepo;
    }

    @Bean
    public AuthorRepositoryImpl authorRepository() {
        AuthorRepositoryImpl mockAuthorRepo = Mockito.mock(AuthorRepositoryImpl.class);
        configAuthorMockRepo(mockAuthorRepo);
        return mockAuthorRepo;
    }

    @Bean
    public NewsRepositoryImpl newsRepository() {
        NewsRepositoryImpl mockNewsRepository = Mockito.mock(NewsRepositoryImpl.class);
        configNewsMockRepo(mockNewsRepository);
        return mockNewsRepository;
    }

    private void configTagMockRepo(TagRepositoryImpl mockTagRepo) {
        configTagFindByIdSpecification(mockTagRepo);
        configTagFindByNameSpecification(mockTagRepo);
        configFindByNewsIdSpecification(mockTagRepo);

        Mockito.when(mockTagRepo.create(CREATE_TEST_TAG_4)).thenReturn(CREATE_DTO_TAG_4.getId());
        Mockito.when(mockTagRepo.update(UPDATE_TEST_TAG_5)).thenReturn(true);
        Mockito.when(mockTagRepo.delete(Mockito.anyLong())).thenReturn(true);
        Mockito.when(mockTagRepo.countAll()).thenReturn(EXPECTED_COUNT_ALL_TAGS);
        Mockito.when(mockTagRepo.findBySpecification(Mockito.any(FindAllTagsSpecification.class)))
                .thenReturn(Arrays.asList(EXPECTED_TAG_1, EXPECTED_TAG_2, EXPECTED_TAG_3));
    }

    private void configAuthorMockRepo(AuthorRepositoryImpl mockAuthorRepo) {
        configAuthorFindByIdSpecification(mockAuthorRepo);
        configAuthorFindByNewsIdSpecification(mockAuthorRepo);

        Mockito.when(mockAuthorRepo.create(CREATE_TEST_AUTHOR_4)).thenReturn(CREATE_TEST_DTO_AUTHOR_4.getId());
        Mockito.when(mockAuthorRepo.update(UPDATE_TEST_AUTHOR_5)).thenReturn(true);
        Mockito.when(mockAuthorRepo.delete(INIT_TEST_ID)).thenReturn(true);
        Mockito.when(mockAuthorRepo.findBySpecification(Mockito.any(FindAllAuthorsSpecification.class)))
                .thenReturn(Arrays.asList(EXPECTED_AUTHOR_1, EXPECTED_AUTHOR_2, EXPECTED_AUTHOR_3));
        Mockito.when(mockAuthorRepo.countAll()).thenReturn(EXPECTED_COUNT_ALL_AUTHORS);


    }

    private void configNewsMockRepo(NewsRepositoryImpl mockNewsRepo) {
        configNewsFindByIdSpecification(mockNewsRepo);
        configNewsFindBySearchSpecification(mockNewsRepo);

        Mockito.when(mockNewsRepo.create(CREATE_TEST_NEWS_4)).thenReturn(CREATE_TEST_DTO_NEWS_4.getId());
        Mockito.when(mockNewsRepo.createNewsAuthorBound(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);
        Mockito.when(mockNewsRepo.updateNewsAuthorBound(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);
        Mockito.when(mockNewsRepo.createNewsTagBound(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);
        Mockito.when(mockNewsRepo.deleteAllNewsTagBounds(Mockito.anyLong())).thenReturn(true);
        Mockito.when(mockNewsRepo.update(UPDATE_TEST_NEWS_5)).thenReturn(true);
        Mockito.when(mockNewsRepo.delete(EXPECTED_DTO_NEWS_1.getId())).thenReturn(true);
        Mockito.when(mockNewsRepo.findBySpecification(Mockito.any(FindAllNewsSpecification.class)))
                .thenReturn(Arrays.asList(EXPECTED_NEWS_1, EXPECTED_NEWS_2, EXPECTED_NEWS_3));
        Mockito.when(mockNewsRepo.countAll()).thenReturn(EXPECTED_COUNT_ALL_NEWS);

    }


    private void configTagFindByIdSpecification(TagRepositoryImpl mockTagRepo) {
        Mockito.when(mockTagRepo.findBySpecification(Mockito.any(FindTagByIdSpecification.class))).thenAnswer(invocation -> {
            FindTagByIdSpecification argument = invocation.getArgument(0);
            long tagSpecId = argument.getTagId();

            if (tagSpecId == EXPECTED_TAG_1.getId()) {
                return Collections.singletonList(EXPECTED_TAG_1);
            } else if (tagSpecId == EXPECTED_TAG_2.getId()) {
                return Collections.singletonList(EXPECTED_TAG_2);
            } else if (tagSpecId == EXPECTED_TAG_3.getId()) {
                return Collections.singletonList(EXPECTED_TAG_3);
            } else if (tagSpecId == CREATE_DTO_TAG_4.getId()) {
                return Collections.singletonList(new Tag(CREATE_DTO_TAG_4.getId(), CREATE_TEST_TAG_4.getName()));
            } else if (tagSpecId == UPDATE_TEST_TAG_5.getId()) {
                return Collections.singletonList(UPDATE_TEST_TAG_5);
            }
            return Collections.emptyList();
        });
    }

    private void configTagFindByNameSpecification(TagRepositoryImpl mockTagRepo) {
        Mockito.when(mockTagRepo.findBySpecification(Mockito.any(FindTagByNameSpecification.class))).thenAnswer( invocation -> {
            FindTagByNameSpecification argument = invocation.getArgument(0);
            String specTagName = argument.getTagName();

            if (EXPECTED_DTO_TAG_1.getName().equals(specTagName)) {
                return Collections.singletonList(EXPECTED_TAG_1);
            }
            return Collections.emptyList();
        });
    }

    private void configAuthorFindByIdSpecification(AuthorRepositoryImpl mockAuthorRepo) {
        Mockito.when(mockAuthorRepo.findBySpecification(Mockito.any(FindAuthorByIdSpecification.class))).thenAnswer(invocation -> {
            FindAuthorByIdSpecification argument = invocation.getArgument(0);
            long authorId = argument.getAuthorId();
            Author createdAuthor = new Author(CREATE_TEST_DTO_AUTHOR_4.getId(),
                    CREATE_TEST_DTO_AUTHOR_4.getName(), CREATE_TEST_DTO_AUTHOR_4.getSurname());

            if (authorId == EXPECTED_AUTHOR_1.getId()) {
                return Collections.singletonList(EXPECTED_AUTHOR_1);
            } else if (authorId == EXPECTED_AUTHOR_2.getId()) {
                return Collections.singletonList(EXPECTED_AUTHOR_2);
            } else if (authorId == EXPECTED_AUTHOR_3.getId()) {
                return Collections.singletonList(EXPECTED_AUTHOR_3);
            } else if (authorId == CREATE_TEST_DTO_AUTHOR_4.getId()) {
                return Collections.singletonList(createdAuthor);
            } else if (authorId == UPDATE_TEST_DTO_AUTHOR_5.getId()) {
                return Collections.singletonList(UPDATE_TEST_AUTHOR_5);
            }
            return Collections.emptyList();
        });
    }

    private void configAuthorFindByNewsIdSpecification(AuthorRepositoryImpl mockAuthorRepo) {
        Mockito.when(mockAuthorRepo.findBySpecification(Mockito.any(FindAuthorByNewsIdSpecification.class))).thenAnswer(invocation -> {
            FindAuthorByNewsIdSpecification argument = invocation.getArgument(0);
            long newsId = argument.getNewsId();

            if (newsId == EXPECTED_NEWS_1.getId()) {
                return Collections.singletonList(EXPECTED_AUTHOR_1);
            } else if (newsId == EXPECTED_NEWS_2.getId()) {
                return Collections.singletonList(EXPECTED_AUTHOR_2);
            } else if (newsId == EXPECTED_NEWS_3.getId()) {
                return Collections.singletonList(EXPECTED_AUTHOR_3);
            } else if (newsId == CREATE_TEST_DTO_NEWS_4.getId()) {
                return Collections.singletonList(EXPECTED_AUTHOR_3);
            } else if (newsId == UPDATE_TEST_DTO_NEWS_5.getId()) {
                return Collections.singletonList(EXPECTED_AUTHOR_3);
            }
            return Collections.emptyList();
        });
    }

    private void configNewsFindByIdSpecification(NewsRepositoryImpl mockNewsRepo) {
        Mockito.when(mockNewsRepo.findBySpecification(Mockito.any(FindNewsByIdSpecification.class))).thenAnswer(invocation -> {
            FindNewsByIdSpecification argument = invocation.getArgument(0);
            long newsId = argument.getNewsId();
            News createdNews = new News(
                    CREATE_TEST_DTO_NEWS_4.getId(), CREATE_TEST_DTO_NEWS_4.getTitle(),
                    CREATE_TEST_DTO_NEWS_4.getShortText(), CREATE_TEST_DTO_NEWS_4.getFullText());

            if (newsId == EXPECTED_DTO_NEWS_1.getId()) {
                return Collections.singletonList(EXPECTED_NEWS_1);
            } else if (newsId == EXPECTED_DTO_NEWS_2.getId()) {
                return Collections.singletonList(EXPECTED_NEWS_2);
            } else if (newsId == EXPECTED_DTO_NEWS_3.getId()) {
                return Collections.singletonList(EXPECTED_NEWS_3);
            } else if (newsId == CREATE_TEST_DTO_NEWS_4.getId()) {
                return Collections.singletonList(createdNews);
            } else if (newsId == UPDATE_TEST_DTO_NEWS_5.getId()) {
                return Collections.singletonList(UPDATE_TEST_NEWS_5);
            }
            return Collections.emptyList();
        });
    }

    private void configNewsFindBySearchSpecification(NewsRepositoryImpl mockNewsRepo) {
        Mockito.when(mockNewsRepo.findBySpecification(Mockito.any(FindNewsBySearchCriteriaSpecification.class))).thenAnswer(invocation -> {
            FindNewsBySearchCriteriaSpecification argument = invocation.getArgument(0);
            SearchCriteria specSearchCriteria = argument.getSearchCriteria();

            if (SEARCH_CRITERIA_1.equals(specSearchCriteria)) {
                return Collections.singletonList(EXPECTED_NEWS_1);
            }
            return Collections.emptyList();
        });
    }

    private void configFindByNewsIdSpecification(TagRepositoryImpl mockTagRepo) {
        Mockito.when(mockTagRepo.findBySpecification(Mockito.any(FindTagsByNewsIdSpecification.class))).thenAnswer(invocation -> {
            FindTagsByNewsIdSpecification argument = invocation.getArgument(0);
            long newsId = argument.getNewsId();

            if (newsId == EXPECTED_DTO_NEWS_1.getId()) {
                return Arrays.asList(EXPECTED_TAG_1, EXPECTED_TAG_2);
            } else if (newsId == EXPECTED_DTO_NEWS_2.getId()) {
                return Arrays.asList(EXPECTED_TAG_1, EXPECTED_TAG_3);
            } else if (newsId == EXPECTED_DTO_NEWS_3.getId()) {
                return Collections.singletonList(EXPECTED_TAG_3);
            } else if (newsId == CREATE_TEST_DTO_NEWS_4.getId()) {
                return Collections.singletonList(EXPECTED_TAG_3);
            } else if (newsId == UPDATE_TEST_DTO_NEWS_5.getId()) {
                return Arrays.asList(EXPECTED_TAG_2, EXPECTED_TAG_3);
            }
            return Collections.emptyList();
        });
    }
}
