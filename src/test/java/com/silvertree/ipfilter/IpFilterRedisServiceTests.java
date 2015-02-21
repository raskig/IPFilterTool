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

import com.silvertree.ipfilter.dao.IpFilterDao;

/**
 * Test for checking the functions of IpFilter service.
 * @author graski
 *
 */

@ContextConfiguration({"classpath:com/silvertree/ipfilter/IpFilterRedisServiceTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class IpFilterRedisServiceTests extends IpFilterServiceTestCases {

	@Before
	public void setUpRedis(){
		super.setUp();
	}
	
	@After
	public void tearDownRedis(){
		super.tearDown();
	}

}
