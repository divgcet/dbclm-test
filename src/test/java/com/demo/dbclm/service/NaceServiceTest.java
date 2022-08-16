package com.demo.dbclm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.demo.dbclm.domain.NaceData;
import com.demo.dbclm.repository.NaceRepository;
import com.demo.dbclm.service.exception.NaceDataNotFoundException;
import com.demo.dbclm.service.exception.NaceIdAlreadyExistsException;

public class NaceServiceTest {

    @Mock
    NaceRepository naceRepository;

    @InjectMocks
    NaceService naceService = new NaceServiceImpl();
    
    NaceData naceDetail;

    @BeforeEach
    public void setUp(){
    	naceDetail = new NaceData(1221l, 33, "code", "parent", "description", "itemsIncluded", "itemsAlsoIncluded", "rulings", "itemsExcluded", "refrences");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getNaceData_byOrderId() throws Exception{
        given(naceRepository.findById(123l)).willReturn(Optional.of(naceDetail));
        NaceData naceDetailResponse = naceService.getNaceDataByorderId(123l);

        assertNotNull(naceDetailResponse);
        assertEquals(1221l,naceDetailResponse.getOrderId());
        assertEquals("code",naceDetailResponse.getCode());

    }

    @Test
    public void getNaceData_NaceData_NotFound(){
        given(naceRepository.findById(1234l)).willReturn(Optional.empty());
        assertThrows(NaceDataNotFoundException.class, ()-> naceService.getNaceDataByorderId(1234l));
    }
    
    @Test
    public void test_postNaceData() {
        given(naceRepository.save(Mockito.any())).willReturn(naceDetail);

        String response = naceService.postNaceData(naceDetail);
        assertEquals("NACE data added successfully",response);
    }

    @Test
    public void test_postNaceData_duplicateData_Test() {
        given(naceRepository.findById(Mockito.anyLong())).willReturn(Optional.of(naceDetail));
        assertThrows(NaceIdAlreadyExistsException.class, ()-> naceService.postNaceData(naceDetail));
    }

}