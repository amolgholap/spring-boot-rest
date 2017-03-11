package com.ag.restboot.services.firstdbcheck;

import com.ag.restboot.bean.firstdbcheck.TitanDMSearchParam;

public interface SearchService {

	String testAutowiring(TitanDMSearchParam searchParam);

	TitanDMSearchParam getSearchResults(TitanDMSearchParam objtm);
}
