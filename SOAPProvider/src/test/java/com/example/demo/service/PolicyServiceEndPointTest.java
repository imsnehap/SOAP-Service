package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.ws.test.server.RequestCreators.withSoapEnvelope;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

import javax.xml.transform.Source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

@SpringBootTest
public class PolicyServiceEndPointTest {
	
	@Autowired
	private ApplicationContext applicationContext;

	private MockWebServiceClient mockClient;

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.initMocks(this);

		mockClient = MockWebServiceClient.createClient(applicationContext);

	}
	
	@Test
	public void getPolicyTest() {
		
		Source requestEnvelope = new StringSource(
		        "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sch=\"http://example.com/demo/schemas\">\n" + 
		        "   <soapenv:Header>\n" + 
		        "      <sch:AuthenticationHeader>\n" + 
		        "         <sch:UserName>admin</sch:UserName>\n" + 
		        "         <sch:Password>admin</sch:Password>\n" + 
		        "      </sch:AuthenticationHeader>\n" + 
		        "   </soapenv:Header>\n" + 
		        "   <soapenv:Body>\n" + 
		        "      <sch:GetPolicyRequest>\n" + 
		        "         <sch:PolicyNumber>w</sch:PolicyNumber>\n" + 
		        "      </sch:GetPolicyRequest>\n" + 
		        "   </soapenv:Body>\n" + 
		        "</soapenv:Envelope>");

	    
	    Source responsePayload =
		        new StringSource("<ns2:GetPolicyResponse xmlns:ns2=\"http://example.com/demo/schemas\">\n" + 
		        		"         <ns2:PolicyHoderName>abc</ns2:PolicyHoderName>\n" + 
		        		"         <ns2:PolicyNumber>5508999</ns2:PolicyNumber>\n" + 
		        		"         <ns2:MaturityDate>10/10/2022</ns2:MaturityDate>\n" + 
		        		"         <ns2:PremiumAmount>100000</ns2:PremiumAmount>\n" + 
		        		"      </ns2:GetPolicyResponse>");
	    
	   	
		assertNotNull(mockClient.sendRequest(withSoapEnvelope(requestEnvelope)).andExpect(payload(responsePayload)));
		

	}
	
}
