package com.forcebay123.dao;

import java.util.List;

import com.forcebay123.dao.GenericDAO;
import com.forcebay123.domain.Listing;





public interface ListingDAO extends GenericDAO<Listing, Integer> {
  
	List<Listing> findAll();
	






}


