package com.forcebay123.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ListingPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<ListingDTO> listings;
}





