package com.ag.restboot.services.firstdbcheck.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ag.restboot.bean.firstdbcheck.SearchParam;
import com.ag.restboot.dao.SearchServiceDao;
import com.ag.restboot.dao.impl.SearchServiceDaoImpl;
import com.ag.restboot.services.firstdbcheck.SearchService;

@Component
public class SearchServiceImpl implements SearchService{
	private static final Logger logger = LoggerFactory
			.getLogger(SearchServiceDaoImpl.class);
	
	@Autowired
	private SearchServiceDao searchDAO;
	
	@Override
	public String testAutowiring(SearchParam searchParam) {
		String result="";
		logger.info("testAutowiring");
		try {
			//searchDAO = (SearchServiceDao) SpringApplicationContext.getBean("SearchServiceDaoImpl");
			result = searchDAO.getSearchResultsTest(searchParam);
			logger.info(" out put "+result);

		} catch (Exception e) {
			logger.error("error in ProductConfiguratorDataServiceImpl: testAutowiring() exception  :  " + e);
		}
		logger.info("EXIT:SearchServiceImpl:testAutowiring");
		return result;
	}

	@Override
	public SearchParam getSearchResults(SearchParam objtm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserDetails(SearchParam searchParam) {
		String result="";
		logger.info("ENTER:SearchServiceImpl:getUserDetails");
		try {
			//searchDAO = (SearchServiceDao) SpringApplicationContext.getBean("SearchServiceDaoImpl");
			result = searchDAO.getUserDetails(searchParam);
			logger.info(" out put "+result);

		} catch (Exception e) {
			logger.error("error in SearchServiceImpl: getUserDetails() exception  :  " + e);
		}
		logger.info("EXIT:SearchServiceImpl:getUserDetails");
		return result;
	}

}
