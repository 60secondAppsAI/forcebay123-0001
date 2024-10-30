package com.forcebay123.dao;

import java.util.List;

import com.forcebay123.dao.GenericDAO;
import com.forcebay123.domain.Administrator;





public interface AdministratorDAO extends GenericDAO<Administrator, Integer> {
  
	List<Administrator> findAll();
	






}


