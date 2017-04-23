package com.ag.restboot.util.resttemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.client.RestTemplate;

import com.ag.restboot.util.JsonToFromObject.JSONUtility;

public class HandleRestCalls {

	/**
	 * execute rest calls with Oauth2 implementation and pass request entity json object as string payload
	 * @param url
	 * @param method
	 * @param jsonObject
	 * @param resourseDetails
	 * @return String of response entity body
	 */
	public String Execute(String url,HttpMethod method,Object jsonObject,ClientCredentialsResourceDetails resourseDetails){
		/*ClientCredentialsResourceDetails resourseDetails=new ClientCredentialsResourceDetails();
		resourseDetails.setAccessTokenUri("");//URI
		resourseDetails.setClientId("");//client Id
		resourseDetails.setClientSecret("");//client Secret
		resourseDetails.setId("");///client Id
		resourseDetails.setClientAuthenticationScheme(AuthenticationScheme.header);*/
		
		ClientCredentialsAccessTokenProvider provider = new ClientCredentialsAccessTokenProvider();
		
		OAuth2AccessToken accessToken = provider.obtainAccessToken(resourseDetails, new DefaultAccessTokenRequest());
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourseDetails, new DefaultOAuth2ClientContext(accessToken));
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(JSONUtility.objectToJson(jsonObject),headers);
		
		final ResponseEntity<String> responseEntity = restTemplate.exchange(url,method,entity,String.class);
		
		String outPut= responseEntity.getBody();
		return outPut;
	}
	/**
	 * execute simple rest calls and pass request entity json object as string payload
	 * @param url
	 * @param method
	 * @param jsonObject
	 * @return String of response entity body
	 */
	public String Execute(String url,HttpMethod method,Object jsonObject){
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(JSONUtility.objectToJson(jsonObject),headers);
		
		final ResponseEntity<String> responseEntity = restTemplate.exchange(url,method,entity,String.class);
		
		String outPut= responseEntity.getBody();
		return outPut;
	}
}
