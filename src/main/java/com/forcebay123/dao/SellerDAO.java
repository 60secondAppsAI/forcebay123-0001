package com.forcebay123.dao;

import java.util.List;

import com.forcebay123.dao.GenericDAO;
import com.forcebay123.domain.Seller;





public interface SellerDAO extends GenericDAO<Seller, Integer> {
  
	List<Seller> findAll();
	






}


