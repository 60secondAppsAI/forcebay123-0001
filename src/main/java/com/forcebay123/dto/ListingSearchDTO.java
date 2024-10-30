package com.forcebay123.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ListingSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer listingId;
	
	private String title;
	
	private String description;
	
	private Double price;
	
	private Date createdDate;
	
	private String status;
	
}
