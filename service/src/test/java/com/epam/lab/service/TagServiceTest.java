package com.epam.lab.service;

import com.epam.lab.configuration.ServiceTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceTestConfig.class)
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Test
    public void test() {

    }


}