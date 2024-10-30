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

import com.forcebay123.domain.Administrator;
import com.forcebay123.dto.AdministratorDTO;
import com.forcebay123.dto.AdministratorSearchDTO;
import com.forcebay123.dto.AdministratorPageDTO;
import com.forcebay123.service.AdministratorService;
import com.forcebay123.dto.common.RequestDTO;
import com.forcebay123.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/administrator")
@RestController
public class AdministratorController {

	private final static Logger logger = LoggerFactory.getLogger(AdministratorController.class);

	@Autowired
	AdministratorService administratorService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Administrator> getAll() {

		List<Administrator> administrators = administratorService.findAll();
		
		return administrators;	
	}

	@GetMapping(value = "/{administratorId}")
	@ResponseBody
	public AdministratorDTO getAdministrator(@PathVariable Integer administratorId) {
		
		return (administratorService.getAdministratorDTOById(administratorId));
	}

 	@RequestMapping(value = "/addAdministrator", method = RequestMethod.POST)
	public ResponseEntity<?> addAdministrator(@RequestBody AdministratorDTO administratorDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = administratorService.addAdministrator(administratorDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/administrators")
	public ResponseEntity<AdministratorPageDTO> getAdministrators(AdministratorSearchDTO administratorSearchDTO) {
 
		return administratorService.getAdministrators(administratorSearchDTO);
	}	

	@RequestMapping(value = "/updateAdministrator", method = RequestMethod.POST)
	public ResponseEntity<?> updateAdministrator(@RequestBody AdministratorDTO administratorDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = administratorService.updateAdministrator(administratorDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
