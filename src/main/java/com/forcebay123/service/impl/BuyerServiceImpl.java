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
import com.forcebay123.dao.BuyerDAO;
import com.forcebay123.domain.Buyer;
import com.forcebay123.dto.BuyerDTO;
import com.forcebay123.dto.BuyerSearchDTO;
import com.forcebay123.dto.BuyerPageDTO;
import com.forcebay123.dto.BuyerConvertCriteriaDTO;
import com.forcebay123.dto.common.RequestDTO;
import com.forcebay123.dto.common.ResultDTO;
import com.forcebay123.service.BuyerService;
import com.forcebay123.util.ControllerUtils;





@Service
public class BuyerServiceImpl extends GenericServiceImpl<Buyer, Integer> implements BuyerService {

    private final static Logger logger = LoggerFactory.getLogger(BuyerServiceImpl.class);

	@Autowired
	BuyerDAO buyerDao;

	


	@Override
	public GenericDAO<Buyer, Integer> getDAO() {
		return (GenericDAO<Buyer, Integer>) buyerDao;
	}
	
	public List<Buyer> findAll () {
		List<Buyer> buyers = buyerDao.findAll();
		
		return buyers;	
		
	}

	public ResultDTO addBuyer(BuyerDTO buyerDTO, RequestDTO requestDTO) {

		Buyer buyer = new Buyer();

		buyer.setBuyerId(buyerDTO.getBuyerId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		buyer = buyerDao.save(buyer);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Buyer> getAllBuyers(Pageable pageable) {
		return buyerDao.findAll(pageable);
	}

	public Page<Buyer> getAllBuyers(Specification<Buyer> spec, Pageable pageable) {
		return buyerDao.findAll(spec, pageable);
	}

	public ResponseEntity<BuyerPageDTO> getBuyers(BuyerSearchDTO buyerSearchDTO) {
	
			Integer buyerId = buyerSearchDTO.getBuyerId(); 
 			String sortBy = buyerSearchDTO.getSortBy();
			String sortOrder = buyerSearchDTO.getSortOrder();
			String searchQuery = buyerSearchDTO.getSearchQuery();
			Integer page = buyerSearchDTO.getPage();
			Integer size = buyerSearchDTO.getSize();

	        Specification<Buyer> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, buyerId, "buyerId"); 
			

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

		Page<Buyer> buyers = this.getAllBuyers(spec, pageable);
		
		//System.out.println(String.valueOf(buyers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(buyers.getTotalPages()));
		
		List<Buyer> buyersList = buyers.getContent();
		
		BuyerConvertCriteriaDTO convertCriteria = new BuyerConvertCriteriaDTO();
		List<BuyerDTO> buyerDTOs = this.convertBuyersToBuyerDTOs(buyersList,convertCriteria);
		
		BuyerPageDTO buyerPageDTO = new BuyerPageDTO();
		buyerPageDTO.setBuyers(buyerDTOs);
		buyerPageDTO.setTotalElements(buyers.getTotalElements());
		return ResponseEntity.ok(buyerPageDTO);
	}

	public List<BuyerDTO> convertBuyersToBuyerDTOs(List<Buyer> buyers, BuyerConvertCriteriaDTO convertCriteria) {
		
		List<BuyerDTO> buyerDTOs = new ArrayList<BuyerDTO>();
		
		for (Buyer buyer : buyers) {
			buyerDTOs.add(convertBuyerToBuyerDTO(buyer,convertCriteria));
		}
		
		return buyerDTOs;

	}
	
	public BuyerDTO convertBuyerToBuyerDTO(Buyer buyer, BuyerConvertCriteriaDTO convertCriteria) {
		
		BuyerDTO buyerDTO = new BuyerDTO();
		
		buyerDTO.setBuyerId(buyer.getBuyerId());

	

		
		return buyerDTO;
	}

	public ResultDTO updateBuyer(BuyerDTO buyerDTO, RequestDTO requestDTO) {
		
		Buyer buyer = buyerDao.getById(buyerDTO.getBuyerId());

		buyer.setBuyerId(ControllerUtils.setValue(buyer.getBuyerId(), buyerDTO.getBuyerId()));



        buyer = buyerDao.save(buyer);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public BuyerDTO getBuyerDTOById(Integer buyerId) {
	
		Buyer buyer = buyerDao.getById(buyerId);
			
		
		BuyerConvertCriteriaDTO convertCriteria = new BuyerConvertCriteriaDTO();
		return(this.convertBuyerToBuyerDTO(buyer,convertCriteria));
	}







}
