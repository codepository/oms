package com.lt.cloud.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lt.cloud.pojo.Salesman;

public interface SalesmanRepository extends JpaRepository<Salesman, Long>{
	List<Salesman> findByNameContaining(String name);
	List<Salesman> findByName(String name);
	boolean existsByName(String name);
}
