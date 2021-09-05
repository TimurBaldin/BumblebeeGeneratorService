package com.rufus.bumblebee.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rufus.bumblebee.controllers.requests.GeneratorsRequest;
import com.rufus.bumblebee.services.interfaces.GeneratorService;
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
import static org.junit.Assert.assertNotNull;
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
    GeneratorService<GeneratorsRequest> generatorService;

    private static final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    public void testAddGenerator() throws Exception {
        GeneratorsRequest request = new GeneratorsRequest();
        request.setCuid("1112");
        MockHttpServletResponse response = mvc.perform(post("/generatorManager/add")
                        .contentType(MediaType.APPLICATION_JSON).content(ow.writeValueAsString(request)))
                .andExpect(status().isOk()).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertNotNull(response.getContentAsString());
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