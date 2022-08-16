package com.demo.dbclm.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.demo.dbclm.domain.NaceData;

@DataJpaTest
@ActiveProfiles("test")
public class NaceRepositoryTest  {
	
	@Autowired
    private NaceRepository naceRepository;
	
	NaceData naceData = new NaceData(1221l, 33, "code", "parent", "description", "itemsIncluded", "itemsAlsoIncluded", "rulings", "itemsExcluded", "refrences");
	
	@Test
	public void test_postNaceData()  {
		
	  NaceData naceDataResponse = naceRepository.save(naceData);
	  Optional<NaceData> returnNaceData = naceRepository.findById(1221l);
      assertTrue(returnNaceData.isPresent());
      assertEquals(naceDataResponse,returnNaceData.get());
      assertEquals(1221l,returnNaceData.get().getOrderId());
        
	}
	
	@Test
	public void getNaceData_NaceData_NotFound() {
		
	  Optional<NaceData> naceData = naceRepository.findById(1231l);
      assertFalse(naceData.isPresent());
	}

}