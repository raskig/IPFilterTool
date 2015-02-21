package com.silvertree.ipfilter;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.silvertree.ipfilter.dao.IpFilterDao;

/**
 * Test for checking the functions of IpFilter service.
 * @author graski
 *
 */

@ContextConfiguration({"classpath:com/silvertree/ipfilter/IpFilterJdbcServiceTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class IpFilterJdbcServiceTests extends IpFilterServiceTestCases {


	@Autowired
	private IIpFilterServiceImpl service;

	@Autowired
	private IpFilterDao ipFilterJDBCDAO;
	
	@Before
	public void setUpRelationalDatabaseService(){
		this.dao = ipFilterJDBCDAO;
		super.setUp();
	}
	
	@After
	public void tearDownRelationalDatabaseService(){
		super.tearDown();
	}

}
