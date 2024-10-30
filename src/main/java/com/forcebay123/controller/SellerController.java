package com.forcebay123.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.forcebay123.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.forcebay123.domain.Seller;
import com.forcebay123.dto.SellerDTO;
import com.forcebay123.dto.SellerSearchDTO;
import com.forcebay123.dto.SellerPageDTO;
import com.forcebay123.service.SellerService;
import com.forcebay123.dto.common.RequestDTO;
import com.forcebay123.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/seller")
@RestController
public class SellerController {

	private final static Logger logger = LoggerFactory.getLogger(SellerController.class);

	@Autowired
	SellerService sellerService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Seller> getAll() {

		List<Seller> sellers = sellerService.findAll();
		
		return sellers;	
	}

	@GetMapping(value = "/{sellerId}")
	@ResponseBody
	public SellerDTO getSeller(@PathVariable Integer sellerId) {
		
		return (sellerService.getSellerDTOById(sellerId));
	}

 	@RequestMapping(value = "/addSeller", method = RequestMethod.POST)
	public ResponseEntity<?> addSeller(@RequestBody SellerDTO sellerDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = sellerService.addSeller(sellerDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/sellers")
	public ResponseEntity<SellerPageDTO> getSellers(SellerSearchDTO sellerSearchDTO) {
 
		return sellerService.getSellers(sellerSearchDTO);
	}	

	@RequestMapping(value = "/updateSeller", method = RequestMethod.POST)
	public ResponseEntity<?> updateSeller(@RequestBody SellerDTO sellerDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = sellerService.updateSeller(sellerDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
