package com.silvertree.ipfilter;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

	@Before
	public void setUpRedis(){
		setOps.add(IpFilterServiceTestCases.IP_FILTERS_KEY, IpFilterServiceTestCases.FILTER_PATTERN_01);
		setOps.add(IpFilterServiceTestCases.IP_FILTERS_KEY, IpFilterServiceTestCases.FILTER_PATTERN_02);
		setOps.add(IpFilterServiceTestCases.IP_FILTERS_KEY, IpFilterServiceTestCases.FILTER_PATTERN_03);
		this.service = redisService;
		assertNotNull(service);
	}

}
