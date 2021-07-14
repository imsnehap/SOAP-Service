package com.example.demo.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;

import com.example.demo.schemas.AuthenticationHeader;
import com.example.demo.schemas.GetPolicyRequest;
import com.example.demo.schemas.GetPolicyResponse;
import com.example.demo.schemas.ObjectFactory;

@Endpoint
public class PolicyServiceEndPoint {

	private static final String NAMESPACE_URI = "http://example.com/demo/schemas";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPolicyRequest")
	@ResponsePayload
	public JAXBElement<GetPolicyResponse> getPolicyDetails(@RequestPayload GetPolicyRequest request,

			@SoapHeader(value = "{http://example.com/demo/schemas}AuthenticationHeader") SoapHeaderElement soapHeaderElement) {

		JAXBElement<GetPolicyResponse> getPolicyResponse = null;

		ObjectFactory factory = new ObjectFactory();

		GetPolicyResponse res = factory.createGetPolicyResponse();

		try {

			JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);

			javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();

			@SuppressWarnings("unchecked")

			JAXBElement<AuthenticationHeader> headers = (JAXBElement<AuthenticationHeader>) unmarshaller

					.unmarshal(soapHeaderElement.getSource());

			if (headers.getValue().getPassword().equalsIgnoreCase("admin")

					&& headers.getValue().getUserName().equalsIgnoreCase("admin")) {

				res.setPolicyHoderName("abc");

				res.setPolicyNumber("5508999");

				res.setPremiumAmount("100000");

				res.setMaturityDate("10/10/2022");

			} else {

				com.example.demo.schemas.Exception exception = factory.createException();

				JAXBElement<com.example.demo.schemas.Exception> exceptionJAXB = null;

				exception.setErrorMessage("UnAuthorized");

				exceptionJAXB = factory.createGetPolicyResponseException(exception);

				res.setException(exceptionJAXB);

			}

			getPolicyResponse = factory.createGetPolicyResponse(res);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return getPolicyResponse;

	}

}