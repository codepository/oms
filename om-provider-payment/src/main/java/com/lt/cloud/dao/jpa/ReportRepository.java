package com.lt.cloud.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lt.cloud.pojo.Report;

public interface ReportRepository extends JpaRepository<Report, Long>{
	boolean existsByCreatoridAndTitle(Long creatorid,String title);
	List<Report> findBycreatoridAndTablename(Long creatorid,String tablename);
}
