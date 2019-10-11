package com.lt.cloud.service.impl;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.reflect.TypeToken;
import com.lt.cloud.dao.jpa.AdvitemRepository;
import com.lt.cloud.mapper.AdvitemMapper;
import com.lt.cloud.pojo.Advitem;
import com.lt.cloud.pojo.AdvitemReceiver;
import com.lt.cloud.service.AdvitemQueryService;
import com.lt.cloud.service.CustomQueryService;
import com.lt.cloud.utils.DateUtil;
import com.lt.cloud.utils.ExcelUtils;
import com.lt.cloud.utils.JsonUtils;
@Component
public class AdvitemQueryServiceImpl implements AdvitemQueryService,CustomQueryService<Advitem,AdvitemReceiver> ,FileService<Advitem>{
	@PersistenceContext
    private EntityManager entityManager;
	@Autowired
	private AdvitemRepository advitemRepository;
	@Autowired
	private AdvitemMapper advitemMapper;
	@Override
	public Predicate[] getPredicate(AdvitemReceiver receiver, Root<Advitem> root, CriteriaQuery<?> query,
			CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates=new ArrayList<>();
		if(receiver.getSYS_DOCUMENTID()!=null) {
			predicates.add(criteriaBuilder.equal(root.get("SYS_DOCUMENTID"), receiver.getSYS_DOCUMENTID()));
		}
		if(!StringUtils.isEmpty(receiver.getAI_Salesman())) {
			predicates.add(criteriaBuilder.equal(root.get("AI_Salesman"), receiver.getAI_Salesman()));
		}
		if (!StringUtils.isEmpty(receiver.getAI_Customer())) {
			predicates.add(criteriaBuilder.equal(root.get("AI_Customer"), receiver.getAI_Customer()));
		}
		if (!StringUtils.isEmpty(receiver.getAI_CustomerLike())) {
			predicates.add(criteriaBuilder.like(root.get("AI_Customer"), "%"+receiver.getAI_CustomerLike()+"%"));
		}
		if(receiver.getAI_OrderID()!=null) {
			predicates.add(criteriaBuilder.equal(root.get("AI_OrderID"), receiver.getAI_OrderID()));
		}
		if (receiver.getAI_Customer_ID()!=null) {
			predicates.add(criteriaBuilder.equal(root.get("AI_Customer_ID"), receiver.getAI_Customer_ID()));
		}
		if (receiver.getAI_PublishTimeStart()!=null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("AI_PublishTime"), receiver.getAI_PublishTimeStart()));
		}
		if (receiver.getAI_PublishTimeEnd()!=null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("AI_PublishEndTime"), DateUtil.addSeconds(receiver.getAI_PublishTimeEnd(), 86399)));
		}
		if (!StringUtils.isEmpty(receiver.getAI_Trade())) {
			String[] trades=receiver.getAI_Trade().split(",");
			if (trades.length==1) {
				predicates.add(criteriaBuilder.equal(root.get("AI_Trade"), receiver.getAI_Trade()));
			}else {
				In<String> in=criteriaBuilder.in(root.get("AI_Trade"));
				for (String trade : trades) {
					in.value(trade);
				}
				predicates.add(in);
			}
		}
		if (!StringUtils.isEmpty(receiver.getAI_TradeID())) {
			String[] trades=receiver.getAI_TradeID().split(",");
			if (trades.length==1) {
				predicates.add(criteriaBuilder.equal(root.get("AI_TradeID"), Integer.valueOf(receiver.getAI_TradeID())));
			}else {
				In<Integer> in=criteriaBuilder.in(root.get("AI_TradeID"));
				for (String trade : trades) {
					in.value(Integer.valueOf(trade));
				}
				predicates.add(in);
			}
		}
		if (!StringUtils.isEmpty(receiver.getAI_Publication())) {
			predicates.add(criteriaBuilder.equal(root.get("AI_Publication"), receiver.getAI_Publication()));
		}
		if (!StringUtils.isEmpty(receiver.getAI_Content())) {
			predicates.add(criteriaBuilder.like(root.get("AI_Content"), "%"+receiver.getAI_Content()+"%"));
		}
		if (!StringUtils.isEmpty(receiver.getAI_PayMode())) {
			predicates.add(criteriaBuilder.equal(root.get("AI_PayMode"), receiver.getAI_PayMode()));
		}
		if (!StringUtils.isEmpty(receiver.getAI_InvoiceNo())) {
			predicates.add(criteriaBuilder.like(root.get("AI_InvoiceNo"), "%"+receiver.getAI_InvoiceNo()+"%"));
		}
		if (!StringUtils.isEmpty(receiver.getSYS_AUTHORS())) {
			predicates.add(criteriaBuilder.like(root.get("SYS_AUTHORS"), "%"+receiver.getSYS_AUTHORS()+"%"));
		}
		if (receiver.getIsBalance()!=null && receiver.getIsBalance()==true) {
			predicates.add(criteriaBuilder.greaterThan(root.get("AI_UnbalancedMoney"), 0.0));
		}
		if (!StringUtils.isEmpty(receiver.getAI_Memo())) {
			predicates.add(criteriaBuilder.like(root.get("AI_Memo"), "%"+receiver.getAI_Memo()+"%"));
		}
		predicates.add(criteriaBuilder.notEqual(root.get("SYS_DELETEFLAG"),1));//为1，代表已经删除
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	@Override
	public List<Selection<?>> getSelection(AdvitemReceiver receiver, Root<Advitem> root,CriteriaBuilder criteriaBuilder) {
		List<Selection<?>> selectionlist=new ArrayList<>();
		if (!StringUtils.isEmpty(receiver.getAI_Customer())) {
			selectionlist.add(root.get("AI_Customer"));
		}
		if(receiver.getAI_OrderID()!=null) {
			selectionlist.add(root.get("AI_OrderID"));
		}
		if (receiver.getAI_PublishTimeStart()!=null || receiver.getAI_PublishTimeEnd()!=null) {
			selectionlist.add(root.get("AI_PublishTime"));
		}
		if (!StringUtils.isEmpty(receiver.getAI_Trade())) {
			selectionlist.add(root.get("AI_Trade"));
		}
		if (!StringUtils.isEmpty(receiver.getAI_Publication())) {
			selectionlist.add(root.get("AI_Publication"));
		}
		if (!StringUtils.isEmpty(receiver.getAI_Content())) {
			selectionlist.add(root.get("AI_Content"));
		}
		if (!StringUtils.isEmpty(receiver.getAI_PayMode())) {
			selectionlist.add(root.get("AI_PayMode"));
		}
		if (!StringUtils.isEmpty(receiver.getAI_InvoiceNo())) {
			selectionlist.add(root.get("AI_InvoiceNo"));
		}
		if (receiver.isHasReceivable()) {
			selectionlist.add(criteriaBuilder.sum(root.get("AI_AmountReceivable")));
		}
		return selectionlist;
	}
	@Override
	public List<Expression<?>> getGrouping(AdvitemReceiver receiver, Root<Advitem> root) {
		List<Expression<?>> grouping=new ArrayList<>();
		if (!StringUtils.isEmpty(receiver.getAI_Customer())) {
			grouping.add(root.get("AI_Customer"));
		}
		if(receiver.getAI_OrderID()!=null) {
			grouping.add(root.get("AI_OrderID"));
		}
		if (receiver.getAI_PublishTimeStart()!=null || receiver.getAI_PublishTimeEnd()!=null) {
			grouping.add(root.get("AI_PublishTime"));
		}
		if (!StringUtils.isEmpty(receiver.getAI_Trade())) {
			grouping.add(root.get("AI_Trade"));
		}
		if (!StringUtils.isEmpty(receiver.getAI_Publication())) {
			grouping.add(root.get("AI_Publication"));
		}
		if (!StringUtils.isEmpty(receiver.getAI_Content())) {
			grouping.add(root.get("AI_Content"));
		}
		if (!StringUtils.isEmpty(receiver.getAI_PayMode())) {
			grouping.add(root.get("AI_PayMode"));
		}
		if (!StringUtils.isEmpty(receiver.getAI_InvoiceNo())) {
			grouping.add(root.get("AI_InvoiceNo"));
		}
		return grouping;
	}

	public Specification<Advitem> getGroupSecification(AdvitemReceiver receiver){
		
		Specification<Advitem> result=new Specification<Advitem>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Advitem> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				criteriaBuilder=entityManager.getCriteriaBuilder();
				query=criteriaBuilder.createQuery(Advitem.class);
				root = query.from(Advitem.class);
				
				Predicate[] predicates=getPredicate(receiver, root, query, criteriaBuilder);
				List<Expression<?>> grouping=getGrouping(receiver, root);
				query.multiselect(criteriaBuilder.construct(Advitem.class, root.get("AI_Customer")));
				return query.where(predicates).groupBy(grouping).getRestriction();
			}
		};
		return result;
	
	}
	public Specification<Advitem> getSpecification(AdvitemReceiver receiver){
		Specification<Advitem> result=new Specification<Advitem>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Advitem> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate[] predicates=getPredicate(receiver, root, query, criteriaBuilder);
				List<Order> orders=new ArrayList<>();
				orders.add(criteriaBuilder.desc(root.get("AI_PublishTime")));
				return query.where(predicates).orderBy(orders).getRestriction();
			}
		};
		
		return result;
	}
	public  Pageable getPageRequest(AdvitemReceiver receiver) {
		return PageRequest.of(receiver.getPageIndex()==null?0:(receiver.getPageIndex()-1), 
				receiver.getPageSize()==null?10:receiver.getPageSize());
	}


	@Override
	public String findAll(AdvitemReceiver receiver) {
		
		return JsonUtils.formatPageForPagination(this.advitemRepository.findAll(getSpecification(receiver), getPageRequest(receiver)));
	}
	@Override
	public Advitem selectById(long id) {
		Optional<Advitem> result = this.advitemRepository.findById(id);
		return result.isPresent()?result.get():null;
	}
	@Override
	public String findStatistics(String json) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Advitem> query = criteriaBuilder.createQuery(Advitem.class);
		AdvitemReceiver receiver=JsonUtils.getGson().fromJson(json, AdvitemReceiver.class);
		Root<Advitem> root = query.from(Advitem.class);
		Predicate[] predicates=getPredicate(receiver, root, query, criteriaBuilder);
		query.multiselect(criteriaBuilder.sum(root.get("AI_AmountReceivable")),
				criteriaBuilder.sum(root.get("AI_AmountPaid")),
				criteriaBuilder.sum(root.get("AI_AmountReceived")),
				criteriaBuilder.sum(root.get("AI_Debt")),
				criteriaBuilder.sum(root.get("AI_AdvPages"))).where(predicates).getRestriction();
		List<?> list = entityManager.createQuery(query).getResultList();
		String result=JsonUtils.getGson().toJson(list);
		return result;
	}
	@Override
	public String findByAdvids(String json) {
		List<Long> ids = JsonUtils.getGson().fromJson(json, new TypeToken<List<Long>>() {}.getType());
		return JsonUtils.getGson().toJson(this.advitemRepository.findAllById(ids));
	}
	@Override
	public List<Advitem> findByAdvids(List<Long> content) {
		List<Long> result=JsonUtils.getGson().fromJson(JsonUtils.getGson().toJson(content), new TypeToken<List<Long>>() {}.getType());
		return this.advitemRepository.findAllById(result);
	}

	@Override
	public List<Advitem> findAllAndNotPage(String json) {
		AdvitemReceiver receiver=JsonUtils.getGson().fromJson(json, AdvitemReceiver.class);
		List<Advitem> result=this.advitemMapper.findAll(receiver);
		return result;
	}

	public HttpHeaders getHttpHeaders(String filename){
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//下载显示的文件名，解决中文名称乱码问题
		String downloadFileName;
		try {
			downloadFileName = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
			//通知浏览器以attachment(下载方式）打开
			headers.setContentDispositionFormData("attachment", downloadFileName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return headers;
	}
	@Override
	public List<HashMap<String, Object>> findAllAsHashMap(AdvitemReceiver receiver) {
		
		return this.advitemMapper.findAllAsHashMap(receiver);
	}
	@Override
	public String findAllReports(String json) {
		AdvitemReceiver receiver=JsonUtils.getGson().fromJson(json, AdvitemReceiver.class);
		boolean pageable=receiver.getPageIndex()!=null && receiver.getPageSize()!=null;
		if (pageable) {
			PageHelper.startPage(receiver.getPageIndex(), receiver.getPageSize());
		}
		List<Advitem> list=this.findAllReports(receiver);
		if (pageable) {
			return this.formatListForPagination(list, receiver.getPageIndex(), receiver.getPageSize());
		}
		return JsonUtils.getGson().toJson(list);
	}
	private List<Advitem> findAllReports(AdvitemReceiver receiver) {
		
		return this.advitemMapper.findAllReports(receiver);
	}
	public  String formatListForPagination(List<?> list,Integer pageIndex,Integer pageSize){
		
		PageInfo<?> pageInfo=new PageInfo(list);
		return JsonUtils.formatDataForPagination(JsonUtils.getGson().toJson(list), pageInfo.getTotal(), pageIndex, pageSize);
	}
	@Override
	public ResponseEntity<byte[]> exportResponseEntity(String json) {
		AdvitemReceiver receiver=JsonUtils.getGson().fromJson(json, AdvitemReceiver.class);
		receiver.setOrderby(" order by AI_PublishTime desc");
		HttpHeaders headers=getHttpHeaders(receiver.getFilename());
		List<HashMap<String, Object>> result = this.findAllAsHashMap(receiver);
//		System.out.println(JsonUtils.getGson().toJson(result));
		return new ResponseEntity<byte[]>(ExcelUtils.exportDataToExcelAsByteArray(result, receiver.getColnames()),headers,HttpStatus.OK);
	}
	@Override
	public ResponseEntity<byte[]> exportAllReports(String json) {
		AdvitemReceiver receiver=JsonUtils.getGson().fromJson(json, AdvitemReceiver.class);
		HttpHeaders headers=getHttpHeaders(receiver.getFilename()==null?"报表.xls":receiver.getFilename());
		List<HashMap<String, Object>> result = this.advitemMapper.findAllReportsAsHashMap(receiver);
		return new ResponseEntity<byte[]>(ExcelUtils.exportDataToExcelAsByteArray(result, receiver.getColnames()==null?receiver.getColumns():receiver.getColnames()),headers,HttpStatus.OK);
	}
	@Override
	public List<Advitem> findByAI_OrderID(Long AI_OrderID) {
		
		return this.advitemRepository.findByAI_OrderID(AI_OrderID);
	}	
}
