package com.demo.dbclm.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.dbclm.domain.NaceData;
import com.demo.dbclm.service.NaceService;
import com.demo.dbclm.service.exception.NaceDataNotFoundException;
import com.demo.dbclm.service.exception.NaceIdAlreadyExistsException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = NaceResource.class)
public class NaceResourceTest {  

	@Autowired
    MockMvc mockMvc;

    @MockBean
    NaceService naceService;
    
    NaceData naceDetail = new NaceData(1221l, 33, "code", "parent", "description", "itemsIncluded", "itemsAlsoIncluded", "rulings", "itemsExcluded", "refrences");

    @Test
    public void getNaceData_byOrderId()  throws Exception {
        given(naceService.getNaceDataByorderId(Mockito.anyLong())).willReturn(naceDetail);
        mockMvc.perform(
        		 get("/getNaceDataByorderId/1221"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("code").value("code"))
                .andExpect(jsonPath("parent").value("parent"))
                .andExpect(jsonPath("orderId").value("1221"));
    }
    

	@Test
	public void getNaceData_NaceData_NotFound() throws Exception {
		 given(naceService.getNaceDataByorderId(Mockito.anyLong())).willThrow(new NaceDataNotFoundException(""));
         mockMvc.perform(
                 get("/getNaceDataByorderId?orderId="+1221)
                 )
                 .andExpect(status().isNotFound());
	}
	
	@Test
	public void test_postNaceData() throws Exception  {
      given(naceService.postNaceData(Mockito.any())).willReturn(Mockito.anyString());
      mockMvc.perform(
              post("/postNaceData/")
              .content(asJsonString(naceDetail))
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON))
              .andExpect(status().isCreated());
        
	}
	
	@Test
	public void test_postNaceData_duplicateData_Test() throws Exception  {
		 given(naceService.postNaceData(Mockito.any())).willThrow(new NaceIdAlreadyExistsException(""));
         mockMvc.perform(
        		 post("/postNaceData/")
                 .content(asJsonString(naceDetail))
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON))
                 .andExpect(status().isConflict());
		
	}
	
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
