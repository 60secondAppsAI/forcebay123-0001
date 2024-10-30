package com.forcebay123.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ReviewDTO {

	private Integer reviewId;

	private Integer rating;

	private String comment;

	private Date reviewDate;






}
