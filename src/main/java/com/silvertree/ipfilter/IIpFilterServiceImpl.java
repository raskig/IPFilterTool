package com.silvertree.ipfilter;

import java.util.List;

import com.silvertree.ipfilter.dao.IpFilterDao;
import com.silvertree.ipfilter.exception.ForbiddenIpAddressException;
import com.silvertree.ipfilter.exception.MalformedIpAddressException;
import com.silvertree.ipfilter.model.IPFilter;

public class IIpFilterServiceImpl implements IIpFilterService {

	private IpFilterDao ipFilterDao;

	public void setIpFilterDao(IpFilterDao ipFilterDao) {
		this.ipFilterDao = ipFilterDao;
	}

	public void validateIpAddress(String ipAddress)
			throws MalformedIpAddressException, ForbiddenIpAddressException {
		
		List<IPFilter> filters =  ipFilterDao.selectAllFilters();
		
		for (IPFilter ipFilter : filters) {
			if(ipFilter.isIpAddressWithinFilterRange(ipAddress)){
				return;
			}
				
		}
		
		throw new ForbiddenIpAddressException(ipAddress);
		

	}

}
