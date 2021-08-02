package com.rufus.bumblebee.controllers;

/*
@RunWith(SpringRunner.class)
@WebMvcTest(ContainerController.class)
public class ContainerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContainerService service;

    private static final Gson gson = new GsonBuilder().serializeNulls().create();
    private static final String TEST_VALUE = "TEST";

    @Test
    public void testAddContainer() throws Exception {

        BaseResponse<ContainerDto> baseResponse = new BaseResponse<>();
        ContainerDto dto = getContainerDto();
        baseResponse.setResponse(dto);
        given(service.createTestDataContainer(TEST_VALUE)).willReturn(dto);

        MockHttpServletResponse response = mvc.perform(post("/containerManager/add/" + TEST_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).
                        andReturn().getResponse();
        assertEquals(response.getContentAsString(), gson.toJson(baseResponse));


    }

    @Test
    public void testRemoveContainer() throws Exception {
        MockHttpServletResponse response = mvc.perform(delete("/containerManager/remove/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).
                        andReturn().getResponse();
        assertEquals(response.getContentAsString(), gson.toJson(new BaseResponse<>()));
    }

    private ContainerDto getContainerDto() {
        ContainerDto dto = new ContainerDto();
        dto.setId(1L);
        dto.setName(TEST_VALUE);
        dto.setStatus(ContainerStatus.NEW);
        return dto;
    }

}

 */
