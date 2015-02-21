package com.silvertree.ipfilter.dao;


import java.util.List;

import com.silvertree.ipfilter.model.IPFilter;

/**
 * Interface for Data Access Object implementations for accessing IPFilter configuration data.
 * @author graski
 *
 */

public interface IIPFilterDao {
	
	List<IPFilter> selectAllFilters();

}
