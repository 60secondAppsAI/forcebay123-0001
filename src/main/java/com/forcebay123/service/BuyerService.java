package com.forcebay123.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcebay123.domain.Buyer;
import com.forcebay123.dto.BuyerDTO;
import com.forcebay123.dto.BuyerSearchDTO;
import com.forcebay123.dto.BuyerPageDTO;
import com.forcebay123.dto.BuyerConvertCriteriaDTO;
import com.forcebay123.service.GenericService;
import com.forcebay123.dto.common.RequestDTO;
import com.forcebay123.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BuyerService extends GenericService<Buyer, Integer> {

	List<Buyer> findAll();

	ResultDTO addBuyer(BuyerDTO buyerDTO, RequestDTO requestDTO);

	ResultDTO updateBuyer(BuyerDTO buyerDTO, RequestDTO requestDTO);

    Page<Buyer> getAllBuyers(Pageable pageable);

    Page<Buyer> getAllBuyers(Specification<Buyer> spec, Pageable pageable);

	ResponseEntity<BuyerPageDTO> getBuyers(BuyerSearchDTO buyerSearchDTO);
	
	List<BuyerDTO> convertBuyersToBuyerDTOs(List<Buyer> buyers, BuyerConvertCriteriaDTO convertCriteria);

	BuyerDTO getBuyerDTOById(Integer buyerId);







}





