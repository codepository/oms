package com.lt.cloud.dao.jpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lt.cloud.pojo.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
	Page<Invoice> findAll(Specification<Invoice> specification,Pageable pageable);
	boolean existsByInvoiceno(String invoiceno);
	@Query(value="select * from invoice where I_InvoiceNo=?1 and SYS_DELETEFLAG=0",nativeQuery=true)
	Invoice findinvoicebyInvoiceno(String invoiceno);
	@Query(value="select I_InvoiceNo from invoice where SYS_DELETEFLAG=0 order by SYS_CREATED desc limit 1", nativeQuery=true)
	String getLastInvoiceno();
}
