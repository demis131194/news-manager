package com.epam.lab.service;

import com.epam.lab.configuration.ServiceTestConfig;
import com.epam.lab.dto.TagTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static com.epam.lab.configuration.TestObjects.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceTestConfig.class)
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Test
    public void createTest() {
        TagTo expected = new TagTo(INIT_TEST_ID + 3, CREATE_DTO_TAG_4.getName());
        TagTo actual = tagService.create(CREATE_DTO_TAG_4);
        assertEquals(expected, actual);
    }

    @Test
    public void updateTest() {
        TagTo expected = UPDATE_DTO_TAG_5;
        TagTo actual = tagService.update(UPDATE_DTO_TAG_5);
        assertEquals(expected, actual);
    }


}