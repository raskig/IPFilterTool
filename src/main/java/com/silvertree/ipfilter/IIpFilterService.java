package com.silvertree.ipfilter;


import com.silvertree.ipfilter.dao.IpFilterDao;
import com.silvertree.ipfilter.exception.ForbiddenIpAddressException;
import com.silvertree.ipfilter.exception.MalformedIpAddressException;
/**
 * Interface for different IPAddress validating implementations.
 * @author graski
 *
 */

public interface IIpFilterService {
	
	void setIpFilterDao(IpFilterDao ipFilterDao);
	
	void validateIpAddress(String ipAddress) throws MalformedIpAddressException, ForbiddenIpAddressException;

}
