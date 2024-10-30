package com.forcebay123.dao;

import java.util.List;

import com.forcebay123.dao.GenericDAO;
import com.forcebay123.domain.Buyer;





public interface BuyerDAO extends GenericDAO<Buyer, Integer> {
  
	List<Buyer> findAll();
	






}


