package com.epam.lab.service;

import com.epam.lab.configuration.AppConfig;
import com.epam.lab.configuration.AppServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class, AppServiceConfig.class})
public class NewsServiceTest {

    @Autowired
    private NewsService newsService;

    @Test
    public void createNews() {
    }

    @Test
    public void addNewBoundsNewsTag() {
    }

    @Test
    public void countAllNews() {
    }

    @Test
    public void findNewsToById() {
    }
}