package com.epam.lab.service;

import com.epam.lab.configuration.ServiceTestConfig;
import com.epam.lab.dto.TagTo;
import com.epam.lab.service.impl.TagServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.epam.lab.configuration.TestObjects.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceTestConfig.class)
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Test
    public void createTest() {
        TagTo testTag = new TagTo(null, CREATE_TEST_TAG_4.getName());
        TagTo actual = tagService.create(testTag);
        assertEquals(CREATE_DTO_TAG_4, actual);
    }

    @Test
    public void createFailWrongIdTest() {
        TagTo testTagTo = new TagTo(INIT_TEST_ID, CREATE_DTO_TAG_4.getName());
        TagTo actual = tagService.create(testTagTo);
        assertNull(actual);
    }

    @Test
    public void updateTest() {
        TagTo actual = tagService.update(UPDATE_DTO_TAG_5);
        assertEquals(UPDATE_DTO_TAG_5, actual);
    }

    @Test
    public void deleteTest() {
        boolean isDelete = tagService.delete(EXPECTED_DTO_TAG_1.getId());
        assertTrue(isDelete);
    }

    @Test
    public void findByIdTest() {
        TagTo actual = tagService.findById(EXPECTED_DTO_TAG_1.getId());
        assertEquals(EXPECTED_DTO_TAG_1, actual);
    }

    @Test
    public void findTagByNameTest() {
        TagTo actual = tagService.findTagByName(EXPECTED_DTO_TAG_1.getName());
        assertEquals(EXPECTED_DTO_TAG_1, actual);
    }

    @Test
    public void findAllTest() {
        List<TagTo> expected = Arrays.asList(
                EXPECTED_DTO_TAG_1,
                EXPECTED_DTO_TAG_2,
                EXPECTED_DTO_TAG_3
        );
        List<TagTo> actual = tagService.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countAllTest() {
        int actual = tagService.countAll();
        assertEquals(EXPECTED_COUNT_ALL_TAGS, actual);
    }

}