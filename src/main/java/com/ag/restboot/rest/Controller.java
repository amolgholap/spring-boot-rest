package com.ag.restboot.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ag.restboot.bean.firstdbcheck.GridSearchParam;
import com.ag.restboot.bean.firstdbcheck.TitanDMSearchParam;
import com.ag.restboot.services.firstdbcheck.SearchService;
import com.ag.restboot.services.firstdbcheck.impl.SearchServiceImpl;

import org.springframework.web.bind.annotation.RequestMapping;


//import com.ge.titandatamining.service.configurator.impl.SearchServiceImpl;
//import com.ge.titandatamining.vo.GridSearchParam;
//import com.ge.titandatamining.vo.SavedSearchParam;
//import com.ge.titandatamining.vo.TitanDMSearchParam;

/*import com.ge.titandatamining.framework.SpringApplicationContext;
 import com.ge.titandatamining.service.configurator.impl.SearchServiceImpl;
 import com.ge.titandatamining.util.TDMConstants;
 import com.ge.titandatamining.vo.GridSearchParam;
 import com.ge.titandatamining.vo.TitanDMSearchParam;*/

/**
 * An example of creating a Rest api using Spring Annotations @RestController.
 * 
 * 
 * 
 * @author Amol N. Gholap-Predix2.0
 */
@RestController
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	/**
     * 
     */
	public Controller() {
		super();
	}

	/**
	 * Sample Endpoint which returns a Welcome Message
	 * 
	 * @param echo
	 *            - the string to echo back
	 * @return -
	 */
	//@SuppressWarnings("nls")
	@RequestMapping("/")
	public String index(
			@RequestParam(value = "echo", defaultValue = "echo") String echo) {
		return "Greetings from Predix Spring Boot! -Product Config"
				+ (new Date());
	}

	@Autowired
	private SearchService searchService;
	String result = "";

	// public ApplicationContext SpringApplicationContext =Application.ctx;

	/*
	 * @RequestMapping(value="/searchProduct",method =
	 * RequestMethod.POST,consumes
	 * =MediaType.APPLICATION_JSON_VALUE,produces=MediaType
	 * .APPLICATION_JSON_VALUE) public String getSearchResults(@RequestBody
	 * TitanDMSearchParam searchParam) { // TODO Auto-generated method stub
	 * logger.info("ENTER:ProductConfiguratorDataServiceImpl:getSearchResults");
	 * try { //SpringApplicationContext =Application.ctx; searchService =
	 * (SearchServiceImpl)
	 * SpringApplicationContext.getBean("SearchServiceImpl");
	 * logger.info("Search Parameter: " + searchParam); result =
	 * searchService.getSearchResults(searchParam);
	 * 
	 * } catch (Exception e) { logger.error(
	 * "error in ProductConfiguratorDataServiceImpl: getSearchResults() exception  :  "
	 * + e); }
	 * logger.info("EXIT:ProductConfiguratorDataServiceImpl:getSearchResults");
	 * return result; }
	 */
	@RequestMapping(value = "/testAutowiring", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String testAutowiring(@RequestBody TitanDMSearchParam searchParam) {
		// TODO Auto-generated method stub
		logger.info("ENTER:ProductConfiguratorDataServiceImpl:getSearchResults");
		try {
			// SpringApplicationContext =Application.ctx;
			/*searchService = (SearchServiceImpl) SpringApplicationContext
					.getBean("SearchServiceImpl");*/
			logger.info("Search Parameter: " + searchParam);
			result = searchService.testAutowiring(searchParam);

		} catch (Exception e) {
			logger.error("error in ProductConfiguratorDataServiceImpl: getSearchResults() exception  :  "
					+ e);
		}
		logger.info("EXIT:ProductConfiguratorDataServiceImpl:getSearchResults");
		return result;
	}

	@RequestMapping(value = "/readJsonTest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String readJsonTest(@RequestBody TitanDMSearchParam searchParam) {
		// TODO Auto-generated method stub
		logger.info("ENTER:ProductConfiguratorDataServiceImpl:getSearchResults");
		try {
			// SpringApplicationContext =Application.ctx;
			logger.info("Search Parameter: " + searchParam);
			System.out.println("Object Detail");
			System.out.println(searchParam.getGrid());
			System.out.println(searchParam.getWaterType());
			System.out.println("Detail of Params");
			ArrayList<GridSearchParam> alGridParam = searchParam
					.getGridSearchParamList();
			for (int i = 0; i < alGridParam.size(); i++) {
				GridSearchParam gridSearchParam = alGridParam.get(i);
				System.out.println(gridSearchParam.getParam());
				System.out.println(gridSearchParam.getMin());
				System.out.println(gridSearchParam.getMax());
			}

		} catch (Exception e) {
			logger.error("error in ProductConfiguratorDataServiceImpl: getSearchResults() exception  :  "
					+ e);
		}
		logger.info("EXIT:ProductConfiguratorDataServiceImpl:getSearchResults");
		return result;
	}

	@RequestMapping(value = "getSearchTest", method = RequestMethod.GET)
	public @ResponseBody
	TitanDMSearchParam getSearchJsonTest() {
		TitanDMSearchParam titanDMSearchParam = new TitanDMSearchParam();
		GridSearchParam gridSearchParam = new GridSearchParam();
		gridSearchParam.setMin(0);
		gridSearchParam.setMax(0);
		gridSearchParam.setParam("pH");
		GridSearchParam gridSearchParam1 = new GridSearchParam();
		gridSearchParam1.setMin(1);
		gridSearchParam1.setMax(1);
		gridSearchParam1.setParam("Alk");

		titanDMSearchParam.setGrid("99");
		titanDMSearchParam.setWaterType("Boiling System");
		ArrayList<GridSearchParam> alGridSearchParam = new ArrayList<GridSearchParam>();
		alGridSearchParam.add(gridSearchParam);
		alGridSearchParam.add(gridSearchParam1);
		titanDMSearchParam.setGridSearchParamList(alGridSearchParam);

		return titanDMSearchParam;
	}
	/*
	 * @RequestMapping(value = "getGridOfWaterType", method = RequestMethod.GET)
	 * public @ResponseBody String getGridOfWaterType() {
	 * logger.info("ENTER:ProductConfiguratorDataServiceImpl:getGridOfWaterType"
	 * ); try { searchService = (SearchServiceImpl)
	 * SpringApplicationContext.getBean("SearchServiceImpl"); result =
	 * searchService.getGridOfWaterType();
	 * 
	 * } catch (Exception e) { logger.error(
	 * "error in ProductConfiguratorDataServiceImpl: getGridOfWaterType() exception  :  "
	 * + e); }
	 * logger.info("EXIT:ProductConfiguratorDataServiceImpl:getGridOfWaterType"
	 * );
	 * 
	 * return result; }
	 * 
	 * @RequestMapping(value = "getParamOfWaterType", method =
	 * RequestMethod.GET) public @ResponseBody String getParamOfWaterType() {
	 * logger
	 * .info("ENTER:ProductConfiguratorDataServiceImpl:getParamOfWaterType");
	 * try { searchService = (SearchServiceImpl)
	 * SpringApplicationContext.getBean("SearchServiceImpl"); result =
	 * searchService.getParamOfWaterType();
	 * 
	 * } catch (Exception e) { logger.error(
	 * "error in ProductConfiguratorDataServiceImpl: getParamOfWaterType() exception  :  "
	 * + e); }
	 * logger.info("EXIT:ProductConfiguratorDataServiceImpl:getParamOfWaterType"
	 * );
	 * 
	 * return result; }
	 * 
	 * @RequestMapping(value = "/fetchLastSavedSearch", method =
	 * RequestMethod.GET) public String getLastSavedSearch(String sso) {
	 * logger.info
	 * ("ENTER:ProductConfiguratorDataServiceImpl:fetchLastSavedSearch"); try {
	 * searchService = (SearchServiceImpl)
	 * SpringApplicationContext.getBean("SearchServiceImpl");
	 * logger.info("Search Parameter: " + sso);
	 * result=searchService.getLastSavedSearch(sso); } catch (Exception e) {
	 * logger.error(
	 * "error in ProductConfiguratorDataServiceImpl: fetchLastSavedSearch exception  :  "
	 * + e); }
	 * logger.info("EXIT:ProductConfiguratorDataServiceImpl:fetchLastSavedSearch"
	 * ); return result; }
	 * 
	 * @RequestMapping(value = "/getShipToDetails", method = RequestMethod.GET)
	 * public String getShipToDetails(String shipto) {
	 * logger.info("ENTER:ProductConfiguratorDataServiceImpl:getShipToDetails");
	 * try { searchService = (SearchServiceImpl)
	 * SpringApplicationContext.getBean("SearchServiceImpl"); result=
	 * searchService.getShipToDetails(shipto); logger.info("Search Parameter: "
	 * + shipto); } catch (Exception e) { logger.error(
	 * "error in ProductConfiguratorDataServiceImpl: fetchLastSavedSearch exception  :  "
	 * + e); }
	 * logger.info("EXIT:ProductConfiguratorDataServiceImpl:getShipToDetails");
	 * return result; }
	 */
	@Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
		// TODO Auto-generated method stub
		logger.info("ENTER:ProductConfiguratorDataServiceImpl:getSearchResults");
		try {
			TitanDMSearchParam searchParam= new TitanDMSearchParam();
			logger.info("Search Parameter: " + searchParam);
			//result = searchService.testAutowiring(searchParam);

		} catch (Exception e) {
			logger.error("error in ProductConfiguratorDataServiceImpl: getSearchResults() exception  :  "
					+ e);
		}
		logger.info("EXIT:ProductConfiguratorDataServiceImpl:getSearchResults");
	}

}