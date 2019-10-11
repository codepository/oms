package com.lt.cloud.dao.jpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lt.cloud.pojo.Advorder;

public interface AdvorderRepository extends JpaRepository<Advorder, Long>{
	Page<Advorder> findAll(Specification<Advorder> specification,Pageable pageable);
}
