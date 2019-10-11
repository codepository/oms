package com.lt.cloud.service;

public interface ReportService {

	String save(String json);

	String update(String json);

	String findAll(String json);

	String findAllByCreatoridAndTable(Long creatorid, String table);

	String delete(Long id);

}
