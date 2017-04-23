package com.ag.restboot.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ag.restboot.bean.firstdbcheck.GridSearchParam;
import com.ag.restboot.bean.firstdbcheck.SearchParam;
import com.ag.restboot.services.firstdbcheck.SearchService;
import com.ag.restboot.services.firstdbcheck.impl.SearchServiceImpl;
import com.ag.restboot.util.resttemplate.HandleRestCalls;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


//import com.ge.titandatamining.service.configurator.impl.SearchServiceImpl;
//import com.ge.titandatamining.vo.GridSearchParam;
//import com.ge.titandatamining.vo.SavedSearchParam;
//import com.ge.titandatamining.vo.SearchParam;

/*import com.ge.titandatamining.framework.SpringApplicationContext;
 import com.ge.titandatamining.service.configurator.impl.SearchServiceImpl;
 import com.ge.titandatamining.util.TDMConstants;
 import com.ge.titandatamining.vo.GridSearchParam;
 import com.ge.titandatamining.vo.SearchParam;*/

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
	 * SearchParam searchParam) { // TODO Auto-generated method stub
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
	public String testAutowiring(@RequestBody SearchParam searchParam) {
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
	public String readJsonTest(@RequestBody SearchParam searchParam) {
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
	SearchParam getSearchJsonTest() {
		SearchParam SearchParam = new SearchParam();
		GridSearchParam gridSearchParam = new GridSearchParam();
		gridSearchParam.setMin(0);
		gridSearchParam.setMax(0);
		gridSearchParam.setParam("pH");
		GridSearchParam gridSearchParam1 = new GridSearchParam();
		gridSearchParam1.setMin(1);
		gridSearchParam1.setMax(1);
		gridSearchParam1.setParam("Alk");

		SearchParam.setGrid("99");
		SearchParam.setWaterType("Boiling System");
		ArrayList<GridSearchParam> alGridSearchParam = new ArrayList<GridSearchParam>();
		alGridSearchParam.add(gridSearchParam);
		alGridSearchParam.add(gridSearchParam1);
		SearchParam.setGridSearchParamList(alGridSearchParam);

		return SearchParam;
	}
	@RequestMapping(value = "uploadFileWithFormData", method = RequestMethod.POST,consumes={MediaType.MULTIPART_FORM_DATA_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody
	SearchParam uploadFileWithFormData(@RequestPart("uploadfile")MultipartFile uploadfile,HttpServletRequest request) {
		String fileName=uploadfile.getOriginalFilename();
		System.out.println(" fileName "+fileName);
		System.out.println("Payload "+ request.getParameter("jsonpayload"));
		return null;
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
	//@Scheduled(cron = "45 23 1/1 * ? *")
	@Scheduled(cron = "0 52 23 * * ?")
    public void reportCurrentTime() {
		// TODO Auto-generated method stub
		logger.info("ENTER:ProductConfiguratorDataServiceImpl:reportCurrentTime");
		try {
			SearchParam searchParam= new SearchParam();
			logger.info("Search Parameter: " + searchParam);
			//result = searchService.testAutowiring(searchParam);

		} catch (Exception e) {
			logger.error("error in ProductConfiguratorDataServiceImpl: reportCurrentTime() exception  :  "
					+ e);
		}
		logger.info("EXIT:ProductConfiguratorDataServiceImpl:reportCurrentTime");
	}
	@RequestMapping(value = "/getUserDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getUserDetails(@RequestBody SearchParam searchParam) {
		logger.info("ENTER:ProductConfiguratorDataServiceImpl: getUserDetails");
		try {
			logger.info("Search Parameter: " + searchParam);
			result = searchService.getUserDetails(searchParam);

		} catch (Exception e) {
			logger.error("error in ProductConfiguratorDataServiceImpl: getUserDetails() exception  :  "
					+ e);
		}
		logger.info("EXIT:ProductConfiguratorDataServiceImpl: getUserDetails");
		return result;
	}
	@RequestMapping(value = "/getUserDetailsFromRestCall", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getUserDetailsFromRestCall(@RequestBody SearchParam searchParam) {
		logger.info("ENTER:ProductConfiguratorDataServiceImpl: getUserDetailsFromRestCall");
		try {
			logger.info("Search Parameter: " + searchParam);
			HandleRestCalls restcalls = new HandleRestCalls();
			result = restcalls.Execute("http://localhost:8090/getUserDetails", HttpMethod.POST, searchParam);
			
		} catch (Exception e) {
			logger.error("error in ProductConfiguratorDataServiceImpl: getUserDetailsFromRestCall() exception  :  "
					+ e);
		}
		logger.info("EXIT:ProductConfiguratorDataServiceImpl: getUserDetailsFromRestCall");
		return result;
	}

}