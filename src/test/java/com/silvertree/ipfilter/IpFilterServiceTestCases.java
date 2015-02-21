package com.silvertree.ipfilter;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.silvertree.ipfilter.dao.IpFilterDao;
import com.silvertree.ipfilter.exception.ForbiddenIpAddressException;
import com.silvertree.ipfilter.exception.MalformedIpAddressException;
import com.silvertree.ipfilter.model.IPFilter;

/**
 * Test for checking the functions of IpFilter service.
 * @author graski
 *
 */

@ContextConfiguration({"classpath:com/silvertree/ipfilter/IpFilterServiceTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class IpFilterServiceTestCases extends TestCase {
	
	@Autowired
	protected IIpFilterServiceImpl service;
	
	@Autowired
	protected IpFilterDao dao;
	
	protected final static String FILTER_PATTERN_01 = "188.221.174.101 - 188.221.174.199";
	protected final static String FILTER_PATTERN_02 = "82.165.97.11 - 82.165.97.79";
	protected final static String FILTER_PATTERN_03 = "82.132.141.*";

	
	protected void setUp(){
		assertNotNull(service);
		assertNotNull(dao);
		dao.addFilter(new IPFilter( FILTER_PATTERN_01));
		dao.addFilter(new IPFilter( FILTER_PATTERN_02));
		dao.addFilter(new IPFilter( FILTER_PATTERN_03));
	}
	
	protected void tearDown(){
		dao.removeFilter(new IPFilter( FILTER_PATTERN_01));
		dao.removeFilter(new IPFilter( FILTER_PATTERN_02));
		dao.removeFilter(new IPFilter( FILTER_PATTERN_03));
	}
	
	//Tests with malformed input
	@Test(expected=MalformedIpAddressException.class)
	public void testMalformedAddress01() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("82.165.97.10invalid");
	}
		
	@Test(expected=MalformedIpAddressException.class)
	public void testMalformedAddress02() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("999.999.999.999");
	}
	
	@Test(expected=MalformedIpAddressException.class)
	public void testMalformedAddress03() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("82.132.141.256");
	}		
	
	//Tests with wildcard
	
	@Test
	public void testValidAddress01() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("82.132.141.0");
	}

	@Test
	public void testValidAddress02() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("82.132.141.255");
	}

	@Test
	public void testValidAddress11() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("82.132.141.100");
	}
	
	//Tests with range values
	
	@Test
	public void testValidAddress03() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("  188.221.174.101	");
	}
	
	@Test
	public void testValidAddress04() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress(" 188.221.174.199");
	}
	
	@Test
	public void testValidAddress05() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("82.165.97.11");
	}
	
	@Test
	public void testValidAddress06() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("82.165.97.79");
	}	
	
	@Test
	public void testValidAddress07() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("188.221.174.102");
	}
	
	@Test
	public void testValidAddress08() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("188.221.174.198");
	}
	
	@Test
	public void testValidAddress09() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("82.165.97.12");
	}
	
	@Test
	public void testValidAddress10() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("82.165.97.78");
	}	

	@Test
	public void testValidAddress12() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("188.221.174.121");
	}	
	

	@Test
	public void testValidAddress13() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("82.165.97.65");
	}		
	
	@Test(expected=ForbiddenIpAddressException.class)
	public void testForbiddenAddress01() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("188.221.174.100");
	}
	
	@Test(expected=ForbiddenIpAddressException.class)
	public void testForbiddenAddress02() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("188.221.174.200");
	}
	
	@Test(expected=ForbiddenIpAddressException.class)
	public void testForbiddenAddress03() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("82.165.97.10");
	}
	
	@Test(expected=ForbiddenIpAddressException.class)
	public void testForbiddenAddress04() throws ForbiddenIpAddressException, MalformedIpAddressException {
		service.validateIpAddress("82.165.97.80");
	}	

}
