package com.ag.restboot.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ag.restboot.bean.firstdbcheck.TitanDMSearchParam;
import com.ag.restboot.dao.SearchServiceDao;

@Component
public class SearchServiceDaoImpl implements SearchServiceDao{

	private static final Logger logger = LoggerFactory
			.getLogger(SearchServiceDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public String getSearchResultsTest(TitanDMSearchParam searchParam) {
		//logger.info("ENTER:SearchServiceDaoImpl:getSearchResultsTest");
		String result = "";
		// String query =
		// "select * from T_LIMS_MASTER where NUMBER$='4000028339' and SAMPLE='U1122219' and MATRIX$='14' and PARAM='M-alk'";
		//String query = "select watertype,grid from T_LIMS_WATERTYPE_GRID a, T_LIMS_WATERTYPE b where a.WATERTYPEID = b.WATERTYPE_ID";
		String query = "select * from public.\"user\"";
		// String query = sQueryForGridOfWaterType;
		//logger.info("***********************\n" + query);
		// logger.info("***********************\n"+sQueryForGridOfWaterType1);
		try {

			List<Map<String, Object>> moreInfoList = jdbcTemplate
					.queryForList(query);
			HashMap<String, ArrayList<Integer>> hmWaterTypeGrid = new HashMap<String, ArrayList<Integer>>();
			int iList = 0;
			for (Map<String, Object> moreInfoMap : moreInfoList) {
				logger.info("OUtPut");
				//logger.info((String)moreInfoMap.get("id"));
				logger.info((String)moreInfoMap.get("Name"));
				result ="From DataBase: "+ (Long)moreInfoMap.get("id")+" " +(String)moreInfoMap.get("Name");
				break;
			}



		} catch (Exception ex) {
			/*
			 * prodHeader.setStatusCode(0); prodHeader.setStatusMsg("Failure");
			 */
			logger.error(
					"Error in SearchServiceDaoImpl : getSearchResultsTest method... : "
							+ ex.getLocalizedMessage(), ex);
		}
		logger.info("EXIT:SearchServiceDaoImpl:getSearchResultsTest");
		return result;
	}
}
