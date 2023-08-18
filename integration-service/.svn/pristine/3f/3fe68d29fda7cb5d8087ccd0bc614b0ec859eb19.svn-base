package com.gg.gpos.integration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import com.gg.rk7.xml.RK7QueryResult;
import com.gg.rk7.xml.RK7QueryResult.CommandResult.RK7Reference.Items.Item;

@RunWith(SpringRunner.class)
public class RK7IntegrationTest {

	private static final String URL = "https://118.71.251.188:9977/rk7api/v0/xmlinterface.xml";
	
	// Define Beans that used for unit test
    @TestConfiguration
    public static class InnerConfiguration{
    	@Bean
    	public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
    	    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
    		 
    	    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
    	                    .loadTrustMaterial(null, acceptingTrustStrategy)
    	                    .build();
            final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
            final Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                    .register("https", sslsf)
                    .register("http", new PlainConnectionSocketFactory())
                    .build();
            final BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager(socketFactoryRegistry);
            final CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .setConnectionManager(connectionManager)
                    .build();	    

            HttpComponentsClientHttpRequestFactory requestFactory =  new HttpComponentsClientHttpRequestFactory();
    	    requestFactory.setHttpClient(httpClient);
    		
    		return new RestTemplate(requestFactory);
    	}
    }
    
    @Autowired
    private RestTemplate restTemplate;
	
	@Test
	public void testSyncModifier() {
		final String xmlCommand = "<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"Modifiers\" IgnoreEnums=\"1\"  /></RK7Query>";
		final String loginPassword = "httpapi:1";
		
//	    CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
//	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//	    requestFactory.setHttpClient(httpClient);
//	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    
	    //Create a list for the message converters
	    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	    //Add the String Message converter
	    messageConverters.add(new StringHttpMessageConverter());
	    //Add the message converters to the restTemplate
	    restTemplate.setMessageConverters(messageConverters);


	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_XML);
	    headers.setBasicAuth(Base64Utils.encodeToString(loginPassword.getBytes()));
	    HttpEntity<String> request = new HttpEntity<String>(xmlCommand, headers);	    
	    
	    ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
	    System.out.println("STATUS:" + response.getStatusCodeValue());
	    System.out.println("BODY: " + response.getBody());
	    
	    
	    
	    
	    
	    try {
		    JAXBContext jaxbContext = JAXBContext.newInstance(RK7QueryResult.class);
		    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        //use ByteArrayInputStream to get the bytes of the String and convert them to InputStream.
	        InputStream inputStream = new ByteArrayInputStream(response.getBody().getBytes(Charset.forName("UTF-8")));
	        RK7QueryResult result = (RK7QueryResult) unmarshaller.unmarshal(inputStream);
	        
	        List<Item> items = result.getCommandResult().getRK7Reference().getItems().getItem();
	        items.stream().forEach(i -> System.out.println("NAME: " + i.getName()));
	        
	    	
//	    	Path storagePath = Paths.get("src/test/resources/modifier.xml");
//	    	Files.createDirectories(storagePath.getParent());
//	    	//Files.createFile(storagePath);
//	    	Files.write(storagePath, response.getBody().getBytes());
	    	
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	    
	    
	}

	@Test
	public void testSyncMenuItems() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException  {
		final String xmlCommand = "<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"MenuItems\" IgnoreEnums=\"1\"  /></RK7Query>";
		final String loginPassword = "httpapi:1";
		
//	    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//		 
//	    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
//	                    .loadTrustMaterial(null, acceptingTrustStrategy)
//	                    .build();
//        final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
//        final Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
//                .register("https", sslsf)
//                .register("http", new PlainConnectionSocketFactory())
//                .build();
//        final BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager(socketFactoryRegistry);
//        final CloseableHttpClient httpClient = HttpClients.custom()
//                .setSSLSocketFactory(sslsf)
//                .setConnectionManager(connectionManager)
//                .build();	    
//	    
//
//        HttpComponentsClientHttpRequestFactory requestFactory =  new HttpComponentsClientHttpRequestFactory();
//	    requestFactory.setHttpClient(httpClient);
//	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    
	    
	    //Create a list for the message converters
	    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	    //Add the String Message converter
	    messageConverters.add(new StringHttpMessageConverter());
	    //Add the message converters to the restTemplate
	    restTemplate.setMessageConverters(messageConverters);
	
	
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_XML);
	    headers.setBasicAuth(Base64Utils.encodeToString(loginPassword.getBytes()));
	    HttpEntity<String> request = new HttpEntity<String>(xmlCommand, headers);	    
	    
	    
	    ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
	    System.out.println("STATUS:" + response.getStatusCodeValue());
	    System.out.println("BODY: " + response.getBody());
	    
	    try {
	    	Path storagePath = Paths.get("src/test/resources/menu.xml");
	    	Files.createDirectories(storagePath.getParent());
	    	//Files.createFile(storagePath);
	    	Files.write(storagePath, response.getBody().getBytes());
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
	
	@Test
	public void testSyncCategory() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException  {
		final String xmlCommand = "<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"CategList\" WithChildItems=\"0\" WithMacroProp=\"1\" IgnoreEnums=\"1\"/></RK7Query>";
		final String loginPassword = "httpapi:1";
		
	    
	    //Create a list for the message converters
	    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	    //Add the String Message converter
	    messageConverters.add(new StringHttpMessageConverter());
	    //Add the message converters to the restTemplate
	    restTemplate.setMessageConverters(messageConverters);
	
	
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_XML);
	    headers.setBasicAuth(Base64Utils.encodeToString(loginPassword.getBytes()));
	    HttpEntity<String> request = new HttpEntity<String>(xmlCommand, headers);	    
	    
	    
	    ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
	    System.out.println("STATUS:" + response.getStatusCodeValue());
	    System.out.println("BODY: " + response.getBody());
	    
	    try {
	    	Path storagePath = Paths.get("src/test/resources/category.xml");
	    	Files.createDirectories(storagePath.getParent());
	    	//Files.createFile(storagePath);
	    	Files.write(storagePath, response.getBody().getBytes());
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}

	
	@Test
	public void testSyncOrderCategory() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException  {
		final String xmlCommand = "<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"UnchangeableOrderTypes\" IgnoreEnums=\"1\" WithMacroProp=\"1\" IgnoreEnums=\"1\" OnlyActive = \"1\" /></RK7Query>";
		final String loginPassword = "httpapi:1";
		
	    
	    //Create a list for the message converters
	    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	    //Add the String Message converter
	    messageConverters.add(new StringHttpMessageConverter());
	    //Add the message converters to the restTemplate
	    restTemplate.setMessageConverters(messageConverters);
	
	
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_XML);
	    headers.setBasicAuth(Base64Utils.encodeToString(loginPassword.getBytes()));
	    HttpEntity<String> request = new HttpEntity<String>(xmlCommand, headers);	    
	    
	    
	    ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
	    System.out.println("STATUS:" + response.getStatusCodeValue());
	    System.out.println("BODY: " + response.getBody());
	    
	    try {
	    	Path storagePath = Paths.get("src/test/resources/order_category.xml");
	    	Files.createDirectories(storagePath.getParent());
	    	//Files.createFile(storagePath);
	    	Files.write(storagePath, response.getBody().getBytes());
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSyncHallplans() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException  {
		final String xmlCommand = "<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"HallPlans\"  WithChildItems=\"0\" WithMacroProp=\"1\" IgnoreEnums=\"1\"  PropMask=\"items.(Ident,MainParentIdent,Name,Code,Status,Items.(*))\"/></RK7Query>";
		final String loginPassword = "httpapi:1";
		
	    
	    //Create a list for the message converters
	    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	    //Add the String Message converter
	    messageConverters.add(new StringHttpMessageConverter());
	    //Add the message converters to the restTemplate
	    restTemplate.setMessageConverters(messageConverters);
	
	
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_XML);
	    headers.setBasicAuth(Base64Utils.encodeToString(loginPassword.getBytes()));
	    HttpEntity<String> request = new HttpEntity<String>(xmlCommand, headers);	    
	    
	    
	    ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
	    System.out.println("STATUS:" + response.getStatusCodeValue());
	    System.out.println("BODY: " + response.getBody());
	    
	    try {
	    	Path storagePath = Paths.get("src/test/resources/hallplans.xml");
	    	Files.createDirectories(storagePath.getParent());
	    	//Files.createFile(storagePath);
	    	Files.write(storagePath, response.getBody().getBytes());
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void testSyncCurrency() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException  {
		final String xmlCommand = "<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7CMD CMD=\"GetRefData\" RefName=\"Currencies\"/></RK7Query>";
		final String loginPassword = "httpapi:1";
	    
	    //Create a list for the message converters
	    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	    //Add the String Message converter
	    messageConverters.add(new StringHttpMessageConverter());
	    //Add the message converters to the restTemplate
	    restTemplate.setMessageConverters(messageConverters);
	
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_XML);
	    headers.setBasicAuth(Base64Utils.encodeToString(loginPassword.getBytes()));
	    HttpEntity<String> request = new HttpEntity<String>(xmlCommand, headers);	    
	    
	    ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
	    System.out.println("STATUS:" + response.getStatusCodeValue());
	    System.out.println("BODY: " + response.getBody());
	    
	    try {
	    	Path storagePath = Paths.get("src/test/resources/currency.xml");
	    	Files.createDirectories(storagePath.getParent());
	    	//Files.createFile(storagePath);
	    	Files.write(storagePath, response.getBody().getBytes());
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
	
}