package com.forcebay123.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.forcebay123.dao.GenericDAO;
import com.forcebay123.service.GenericService;
import com.forcebay123.service.impl.GenericServiceImpl;
import com.forcebay123.dao.AdministratorDAO;
import com.forcebay123.domain.Administrator;
import com.forcebay123.dto.AdministratorDTO;
import com.forcebay123.dto.AdministratorSearchDTO;
import com.forcebay123.dto.AdministratorPageDTO;
import com.forcebay123.dto.AdministratorConvertCriteriaDTO;
import com.forcebay123.dto.common.RequestDTO;
import com.forcebay123.dto.common.ResultDTO;
import com.forcebay123.service.AdministratorService;
import com.forcebay123.util.ControllerUtils;





@Service
public class AdministratorServiceImpl extends GenericServiceImpl<Administrator, Integer> implements AdministratorService {

    private final static Logger logger = LoggerFactory.getLogger(AdministratorServiceImpl.class);

	@Autowired
	AdministratorDAO administratorDao;

	


	@Override
	public GenericDAO<Administrator, Integer> getDAO() {
		return (GenericDAO<Administrator, Integer>) administratorDao;
	}
	
	public List<Administrator> findAll () {
		List<Administrator> administrators = administratorDao.findAll();
		
		return administrators;	
		
	}

	public ResultDTO addAdministrator(AdministratorDTO administratorDTO, RequestDTO requestDTO) {

		Administrator administrator = new Administrator();

		administrator.setAdministratorId(administratorDTO.getAdministratorId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		administrator = administratorDao.save(administrator);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Administrator> getAllAdministrators(Pageable pageable) {
		return administratorDao.findAll(pageable);
	}

	public Page<Administrator> getAllAdministrators(Specification<Administrator> spec, Pageable pageable) {
		return administratorDao.findAll(spec, pageable);
	}

	public ResponseEntity<AdministratorPageDTO> getAdministrators(AdministratorSearchDTO administratorSearchDTO) {
	
			Integer administratorId = administratorSearchDTO.getAdministratorId(); 
 			String sortBy = administratorSearchDTO.getSortBy();
			String sortOrder = administratorSearchDTO.getSortOrder();
			String searchQuery = administratorSearchDTO.getSearchQuery();
			Integer page = administratorSearchDTO.getPage();
			Integer size = administratorSearchDTO.getSize();

	        Specification<Administrator> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, administratorId, "administratorId"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Administrator> administrators = this.getAllAdministrators(spec, pageable);
		
		//System.out.println(String.valueOf(administrators.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(administrators.getTotalPages()));
		
		List<Administrator> administratorsList = administrators.getContent();
		
		AdministratorConvertCriteriaDTO convertCriteria = new AdministratorConvertCriteriaDTO();
		List<AdministratorDTO> administratorDTOs = this.convertAdministratorsToAdministratorDTOs(administratorsList,convertCriteria);
		
		AdministratorPageDTO administratorPageDTO = new AdministratorPageDTO();
		administratorPageDTO.setAdministrators(administratorDTOs);
		administratorPageDTO.setTotalElements(administrators.getTotalElements());
		return ResponseEntity.ok(administratorPageDTO);
	}

	public List<AdministratorDTO> convertAdministratorsToAdministratorDTOs(List<Administrator> administrators, AdministratorConvertCriteriaDTO convertCriteria) {
		
		List<AdministratorDTO> administratorDTOs = new ArrayList<AdministratorDTO>();
		
		for (Administrator administrator : administrators) {
			administratorDTOs.add(convertAdministratorToAdministratorDTO(administrator,convertCriteria));
		}
		
		return administratorDTOs;

	}
	
	public AdministratorDTO convertAdministratorToAdministratorDTO(Administrator administrator, AdministratorConvertCriteriaDTO convertCriteria) {
		
		AdministratorDTO administratorDTO = new AdministratorDTO();
		
		administratorDTO.setAdministratorId(administrator.getAdministratorId());

	

		
		return administratorDTO;
	}

	public ResultDTO updateAdministrator(AdministratorDTO administratorDTO, RequestDTO requestDTO) {
		
		Administrator administrator = administratorDao.getById(administratorDTO.getAdministratorId());

		administrator.setAdministratorId(ControllerUtils.setValue(administrator.getAdministratorId(), administratorDTO.getAdministratorId()));



        administrator = administratorDao.save(administrator);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AdministratorDTO getAdministratorDTOById(Integer administratorId) {
	
		Administrator administrator = administratorDao.getById(administratorId);
			
		
		AdministratorConvertCriteriaDTO convertCriteria = new AdministratorConvertCriteriaDTO();
		return(this.convertAdministratorToAdministratorDTO(administrator,convertCriteria));
	}







}
