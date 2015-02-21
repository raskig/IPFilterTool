package com.silvertree.ipfilter;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test for checking the functions of IpFilter service.
 * @author graski
 *
 */

@ContextConfiguration({"classpath:com/silvertree/ipfilter/IpFilterServiceTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class IpFilterJdbcServiceTests extends IpFilterServiceTestCases {


	@Autowired
	private IIpFilterServiceImpl jdbcService;

	@Before
	public void setUpRelationalDatabase(){
		this.service = jdbcService;
		assertNotNull(service);
	}

}
