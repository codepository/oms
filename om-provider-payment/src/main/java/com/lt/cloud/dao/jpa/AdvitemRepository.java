package com.lt.cloud.dao.jpa;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.lt.cloud.pojo.Advitem;
public interface AdvitemRepository extends JpaRepository<Advitem, Long> {
	Page<Advitem> findAll(Specification<Advitem> specification,Pageable pageable);
	@Modifying
	@Transactional
	@Query("delete from Advitem a where a.AI_OrderID=?1")
	void deleteByOrderId(Long id);
	List<Advitem> findAll(Specification<Advitem> groupSecification);
	@Query(value="select * from advitem  where AI_OrderID=?1", nativeQuery=true)
	List<Advitem> findByAI_OrderID(Long AI_OrderID);
}
