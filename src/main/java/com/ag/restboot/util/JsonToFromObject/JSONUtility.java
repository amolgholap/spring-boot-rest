package com.ag.restboot.util.JsonToFromObject;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONUtility {

	public static Object jsonToObject(String jsonString,Object classObject){
		ObjectMapper mapper= new ObjectMapper();
			try {
				classObject= mapper.readValue(jsonString, classObject.getClass());
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return classObject;
	}
	
	public static String objectToJson(Object obj){
		ObjectMapper mapper= new ObjectMapper();
		String jsonValue="";
		
		try {
			jsonValue= mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonValue;
	}
}
