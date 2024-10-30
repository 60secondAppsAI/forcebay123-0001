package com.forcebay123.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcebay123.domain.Administrator;
import com.forcebay123.dto.AdministratorDTO;
import com.forcebay123.dto.AdministratorSearchDTO;
import com.forcebay123.dto.AdministratorPageDTO;
import com.forcebay123.dto.AdministratorConvertCriteriaDTO;
import com.forcebay123.service.GenericService;
import com.forcebay123.dto.common.RequestDTO;
import com.forcebay123.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AdministratorService extends GenericService<Administrator, Integer> {

	List<Administrator> findAll();

	ResultDTO addAdministrator(AdministratorDTO administratorDTO, RequestDTO requestDTO);

	ResultDTO updateAdministrator(AdministratorDTO administratorDTO, RequestDTO requestDTO);

    Page<Administrator> getAllAdministrators(Pageable pageable);

    Page<Administrator> getAllAdministrators(Specification<Administrator> spec, Pageable pageable);

	ResponseEntity<AdministratorPageDTO> getAdministrators(AdministratorSearchDTO administratorSearchDTO);
	
	List<AdministratorDTO> convertAdministratorsToAdministratorDTOs(List<Administrator> administrators, AdministratorConvertCriteriaDTO convertCriteria);

	AdministratorDTO getAdministratorDTOById(Integer administratorId);







}





