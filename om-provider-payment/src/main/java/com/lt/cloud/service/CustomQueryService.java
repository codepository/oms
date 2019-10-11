package com.lt.cloud.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
public interface CustomQueryService<T,R> {
	public Predicate[] getPredicate(R receiver,Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder);
	public List<Selection<?>> getSelection(R receiver,Root<T> root,CriteriaBuilder criteriaBuilder);
	public List<Expression<?>> getGrouping(R receiver,Root<T> root);
}
