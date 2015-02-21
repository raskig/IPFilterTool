package com.silvertree.ipfilter;

import java.util.List;

import com.silvertree.ipfilter.dao.IIPFilterDao;
import com.silvertree.ipfilter.exception.ForbiddenIpAddressException;
import com.silvertree.ipfilter.exception.MalformedIpAddressException;
import com.silvertree.ipfilter.model.IPFilter;

public class IIpFilterServiceImpl implements IIpFilterService {

	private IIPFilterDao ipFilterDao;

	public void setIpFilterDao(IIPFilterDao ipFilterDao) {
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
