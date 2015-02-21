package com.silvertree.ipfilter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import com.silvertree.ipfilter.model.IPFilter;

/**
 * Data Access Object implementation for getting IpFilter configuration data from Redis database.
 * @author graski
 *
 */

@Component
public class IpFilterRedisDAO implements IpFilterDao {

	protected final static String IP_FILTERS_KEY = "IP_FILTERS";
	
    @Autowired
    private RedisTemplate<String, String> template;

    @Resource(name="redisTemplate")
    private SetOperations<String, String> setOps;

    public void addFilter(IPFilter newIpfilter){
    	setOps.add(IP_FILTERS_KEY, newIpfilter.getPattern());
	}
    
	public List<IPFilter> selectAllFilters() {
		Set<String> filters = setOps.members("IP_FILTERS");
		ArrayList<IPFilter> filterList = new ArrayList<IPFilter>();
		for (String pattern : filters) {
			filterList.add(new IPFilter(pattern));
		}
		return filterList;
	}

	public void removeFilter(IPFilter newIpfilter) {
		setOps.remove(IP_FILTERS_KEY, newIpfilter.getPattern());
	}

}
