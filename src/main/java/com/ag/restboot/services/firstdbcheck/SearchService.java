package com.ag.restboot.services.firstdbcheck;

import com.ag.restboot.bean.firstdbcheck.SearchParam;

public interface SearchService {

	String testAutowiring(SearchParam searchParam);

	SearchParam getSearchResults(SearchParam objtm);
}
