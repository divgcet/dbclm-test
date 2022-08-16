package com.demo.dbclm.service;

import com.demo.dbclm.domain.NaceData;

/**
 * This is the service class used for applying any business logic on data fetched from 
 * repository layer.
 * 
 * 
 * @author 7338877
 *
 */

public interface NaceService {
	public NaceData getNaceDataByorderId(long orderId);
	public String postNaceData(NaceData naceDTO);

}
