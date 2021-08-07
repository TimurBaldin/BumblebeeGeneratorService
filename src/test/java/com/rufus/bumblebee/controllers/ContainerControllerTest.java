package com.rufus.bumblebee.controllers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rufus.bumblebee.controllers.requests.ContainerRequest;
import com.rufus.bumblebee.controllers.requests.ReportType;
import com.rufus.bumblebee.controllers.responses.ContainerDto;
import com.rufus.bumblebee.repository.ContainerStatus;
import com.rufus.bumblebee.services.ContainerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = ContainerController.class)
public class ContainerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContainerService service;

    private static final Gson gson = new GsonBuilder().serializeNulls().create();
    private static final String TEST_VALUE = "TEST";

    @Test
    public void testAddContainer() throws Exception {
        ContainerDto dto = getContainerDto();
        ResponseEntity<ContainerDto> baseResponse = new ResponseEntity<ContainerDto>(dto, HttpStatus.OK);
        given(service.createTestDataContainer(TEST_VALUE, false, ReportType.EXCEL_TYPE)).willReturn(dto);

        ContainerRequest request=new ContainerRequest();
        request.setAuth(false);
        request.setReportType(ReportType.EXCEL_TYPE);
        request.setName(TEST_VALUE);

        MockHttpServletResponse response = mvc.perform(post("/containerManager/add")
                        .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(request)))
                .andExpect(status().isOk()).
                andReturn().getResponse();
        assertEquals(response.getContentAsString(), gson.toJson(baseResponse.getBody()));
        assertEquals(response.getStatus(), baseResponse.getStatusCode().value());
    }


    @Test
    public void testRemoveContainer() throws Exception {
        MockHttpServletResponse response = mvc.perform(delete("/containerManager/remove/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).
                andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    private ContainerDto getContainerDto() {
        ContainerDto dto = new ContainerDto();
        dto.setId(1L);
        dto.setName(TEST_VALUE);
        dto.setStatus(ContainerStatus.NEW);
        return dto;
    }

}


