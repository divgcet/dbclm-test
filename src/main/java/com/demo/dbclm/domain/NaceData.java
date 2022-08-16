package com.demo.dbclm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Domain object representing POJO to insert NACH record in DB. 
 * 
 * @author 7338877
 *
 */
@Getter @AllArgsConstructor @ToString
@Entity
@Table(name="NACE")
public class NaceData {
	
	@Id
	private Long orderId;
	
	@Column
	private Integer level;
	
	@Column
	private String code;
	
	@Column
	private String parent;
	
	@Column
	private String description;
	
	@Column
	private String itemsIncluded;
	
	@Column
	private String itemsAlsoIncluded;
	
	@Column
	private String rulings;
	
	@Column
	private String itemsExcluded;
	
	@Column
	private String refrences;
	
	public NaceData() {}
	
}
