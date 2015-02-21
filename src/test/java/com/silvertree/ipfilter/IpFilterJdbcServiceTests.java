package com.silvertree.ipfilter;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.silvertree.ipfilter.dao.IIPFilterDao;
import com.silvertree.ipfilter.dao.IpFilterJDBCDAO;
import com.silvertree.ipfilter.model.IPFilter;

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

	@Autowired
	private IIPFilterDao ipFilterJDBCDAO;
	
	@Before
	public void setUpRelationalDatabaseService(){
		ipFilterJDBCDAO.addFilter(new IPFilter( IpFilterServiceTestCases.FILTER_PATTERN_01));
		ipFilterJDBCDAO.addFilter(new IPFilter( IpFilterServiceTestCases.FILTER_PATTERN_02));
		ipFilterJDBCDAO.addFilter(new IPFilter( IpFilterServiceTestCases.FILTER_PATTERN_03));
		this.service = jdbcService;
		assertNotNull(service);
	}
	
	@After
	public void tearDownRelationalDatabaseService(){
		ipFilterJDBCDAO.removeFilter(new IPFilter( IpFilterServiceTestCases.FILTER_PATTERN_01));
		ipFilterJDBCDAO.removeFilter(new IPFilter( IpFilterServiceTestCases.FILTER_PATTERN_02));
		ipFilterJDBCDAO.removeFilter(new IPFilter( IpFilterServiceTestCases.FILTER_PATTERN_03));
	}	

}
