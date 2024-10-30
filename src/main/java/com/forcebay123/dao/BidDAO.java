package com.forcebay123.dao;

import java.util.List;

import com.forcebay123.dao.GenericDAO;
import com.forcebay123.domain.Bid;





public interface BidDAO extends GenericDAO<Bid, Integer> {
  
	List<Bid> findAll();
	






}


