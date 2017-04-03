package com.ag.restboot.dao;

import com.ag.restboot.bean.firstdbcheck.SearchParam;

public interface SearchServiceDao {
	String getSearchResultsTest(SearchParam searchParam);

	String getUserDetails(SearchParam searchParam);
}
