package com.demo.dbclm;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.demo.dbclm.domain.NaceData;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class NaceIntegrationTest {

	@Autowired
	TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	HttpEntity<String> entity = new HttpEntity<String>(null, new HttpHeaders());

	@Test
	public void getNaceData_byOrderId() {
		NaceData naceDetail =  new NaceData(1221l, 33, "code", "parent", "description", "itemsIncluded", "itemsAlsoIncluded", "rulings", "itemsExcluded", "refrences");
		restTemplate.postForEntity("http://localhost:" + port + "/postNaceData/", naceDetail, String.class);

		ResponseEntity<NaceData> response = restTemplate.exchange(
				"http://localhost:" + port + "/getNaceDataByorderId/1221", HttpMethod.GET, entity,
				NaceData.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(1221, response.getBody().getOrderId());
		assertEquals(33, response.getBody().getLevel());
	}

	@Test
	public void getNaceData_NaceData_NotFound() throws Exception {
		
		ResponseEntity<NaceData> response = restTemplate.exchange(
				"http://localhost:" + port + "/getNaceDataByorderId?orderId=123", HttpMethod.GET, entity,
				NaceData.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void test_postNaceData() {
		NaceData naceDetail =  new NaceData(1234l, 33, "code", "parent", "description", "itemsIncluded", "itemsAlsoIncluded", "rulings", "itemsExcluded", "refrences");
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/postNaceData/", naceDetail, String.class);
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals("NACE data added successfully",response.getBody());
        
	}
	
	@Test
	public void test_postNaceData_duplicateData_Test() {
		NaceData naceDetail =  new NaceData(123455l, 33, "code", "parent", "description", "itemsIncluded", "itemsAlsoIncluded", "rulings", "itemsExcluded", "refrences");
		restTemplate.postForEntity("http://localhost:" + port + "/postNaceData/", naceDetail, String.class);
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/postNaceData/", naceDetail, String.class);
		assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
        
	}

}
