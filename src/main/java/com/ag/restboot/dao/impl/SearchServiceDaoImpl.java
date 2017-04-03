package com.ag.restboot.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ag.restboot.bean.firstdbcheck.SearchParam;
import com.ag.restboot.dao.SearchServiceDao;

@Component
public class SearchServiceDaoImpl implements SearchServiceDao{

	private static final Logger logger = LoggerFactory
			.getLogger(SearchServiceDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}*/
	
	@Value("${db.firstdbcheck.user.selectall}")
    private String allUser;
	
	@Override
	public String getSearchResultsTest(SearchParam searchParam) {
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
					.queryForList(allUser);
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

	@Override
	public String getUserDetails(SearchParam searchParam) {
		logger.info("ENTER:SearchServiceDaoImpl:getUserDetails");
		String result = "";
		String query = "SELECT public.showusers(?)";
		Connection conn=null;
		ResultSet rs =null;
		ResultSet rs1 = null;
		try {

			/*List<Map<String, Object>> moreInfoList = jdbcTemplate
					.queryForList(allUser);*/
			conn =jdbcTemplate.getDataSource().getConnection();
			
			conn.setAutoCommit(false);

			CallableStatement cs = conn.prepareCall(query);
			
			cs.setString(1, searchParam.getGrid());
			
			rs = cs.executeQuery();
			
			if(rs.next()){
				rs1=(ResultSet)rs.getObject(1);
				if(rs1.next()){
					result="Result from procedure : "+rs1.getString("name");
				}
			}


		} catch (Exception ex) {
			logger.error(
					"Error in SearchServiceDaoImpl : getUserDetails method... : "
							+ ex.getLocalizedMessage(), ex);
		}finally{
			try {
				rs1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.info("EXIT:SearchServiceDaoImpl:getUserDetails");
		return result;
	}
}
