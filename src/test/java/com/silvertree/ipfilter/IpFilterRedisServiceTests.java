package com.silvertree.ipfilter;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.silvertree.ipfilter.dao.IIPFilterDao;
import com.silvertree.ipfilter.model.IPFilter;

/**
 * Test for checking the functions of IpFilter service.
 * @author graski
 *
 */

@ContextConfiguration({"classpath:com/silvertree/ipfilter/IpFilterServiceTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class IpFilterRedisServiceTests extends IpFilterServiceTestCases {

	
    @Autowired
    private RedisTemplate<String, String> template;

    @Resource(name="redisTemplate")
    private SetOperations<String, String> setOps;
	
	@Autowired
	private IIpFilterServiceImpl redisService;
	
	@Autowired
	private IIPFilterDao ipFilterRedisDAO;

	@Before
	public void setUpRedis(){
		ipFilterRedisDAO.addFilter(new IPFilter( IpFilterServiceTestCases.FILTER_PATTERN_01));
		ipFilterRedisDAO.addFilter(new IPFilter( IpFilterServiceTestCases.FILTER_PATTERN_02));
		ipFilterRedisDAO.addFilter(new IPFilter( IpFilterServiceTestCases.FILTER_PATTERN_03));
		this.service = redisService;
		assertNotNull(service);
	}
	
	@After
	public void tearDownRelationalDatabaseService(){
		ipFilterRedisDAO.removeFilter(new IPFilter( IpFilterServiceTestCases.FILTER_PATTERN_01));
		ipFilterRedisDAO.removeFilter(new IPFilter( IpFilterServiceTestCases.FILTER_PATTERN_02));
		ipFilterRedisDAO.removeFilter(new IPFilter( IpFilterServiceTestCases.FILTER_PATTERN_03));
	}

}
