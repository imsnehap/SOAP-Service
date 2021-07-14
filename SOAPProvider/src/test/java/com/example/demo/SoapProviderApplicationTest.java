package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.SoapProviderApplication;


@SpringBootTest
public class SoapProviderApplicationTest {

	@Test
	void contextLoads() {
	}

	@Test
	public void testMain() {
		SoapProviderApplication.main(new String[] {});
	}
}
