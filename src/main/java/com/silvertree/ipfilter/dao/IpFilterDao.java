package com.silvertree.ipfilter.dao;


import java.util.List;

import com.silvertree.ipfilter.model.IPFilter;

/**
 * Interface for Data Access Object implementations for accessing IPFilter configuration data.
 * @author graski
 *
 */

public interface IpFilterDao {
	
	List<IPFilter> selectAllFilters();
	
	public void addFilter(IPFilter newIpfilter);
	
	public void removeFilter(IPFilter newIpfilter);
}
