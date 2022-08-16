package com.demo.dbclm.repository;

import org.springframework.data.repository.CrudRepository;
import com.demo.dbclm.domain.NaceData;

/**
 * This is repository class extending JPA repository used for applying DB 
 * crud operation with NACH domain objects.
 * 
 * @author 7338877
 *
 */

public interface NaceRepository extends CrudRepository<NaceData, Long> {

}
