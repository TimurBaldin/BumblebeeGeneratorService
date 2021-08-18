package com.rufus.bumblebee.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rufus.bumblebee.controllers.requests.GeneratorsRequest;
import com.rufus.bumblebee.services.GeneratorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = GeneratorsController.class)
public class GeneratorsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GeneratorServiceImpl service;

    private static final Gson gson = new GsonBuilder().serializeNulls().create();

    @Test
    public void testAddGenerator() throws Exception {
        MockHttpServletResponse response = mvc.perform(post("/generatorManager/add")
                        .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(new GeneratorsRequest())))
                .andExpect(status().isOk()).
                andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    public void testGetGenerators() throws Exception {
        MockHttpServletResponse response = mvc.perform(get("/generatorManager/information")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).
                andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

}