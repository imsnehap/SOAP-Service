package com.example.demo.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {

		MessageDispatcherServlet servlet = new MessageDispatcherServlet();

		servlet.setApplicationContext(applicationContext);

		servlet.setTransformWsdlLocations(true);

		return new ServletRegistrationBean(servlet, "/SOAPProvider/*");

	}

	@Bean(name = "SOAPProvider")

	public Wsdl11Definition wsdl11Definition() {

		SimpleWsdl11Definition simpleWsdl11Definition = new SimpleWsdl11Definition();

		simpleWsdl11Definition.setWsdl(new ClassPathResource("wsdl/SOAPProvider.wsdl"));

		return simpleWsdl11Definition;

	}

}