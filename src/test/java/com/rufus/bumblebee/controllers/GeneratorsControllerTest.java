package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.services.GeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(GeneratorsController.class)
public class GeneratorsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GeneratorService service;

    @Test
    public void test(){

    }

}