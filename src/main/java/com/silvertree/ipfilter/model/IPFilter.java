package com.silvertree.ipfilter.model;


import java.util.Scanner;
import java.util.StringTokenizer;

import com.silvertree.ipfilter.exception.InvalidIpFilterConfigurationException;
import com.silvertree.ipfilter.exception.MalformedIpAddressException;

/**
 * Bean for holding one IpAddress filtering information and for implementing the ipAddress validation check by using it.
 * @author graski
 *
 */

public class IPFilter {

	private String pattern;
	
	private String fromAddress;
	private String toAddress;	
	
	private long fromAddressLongValue;
	private long toAddressLongValue;
	
	private final static String IP_NUMBER_REGEXP_PATTERN = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
	
	private final static String WILDCARD_REGEXP_PATTERN = "[\\s-]*"+IP_NUMBER_REGEXP_PATTERN+"\\."+IP_NUMBER_REGEXP_PATTERN+"\\."+IP_NUMBER_REGEXP_PATTERN+"\\.\\*[\\s-]*";
	
	private final static String IP_ADDRESS__REGEXP_PATTERN = IP_NUMBER_REGEXP_PATTERN+"\\."+IP_NUMBER_REGEXP_PATTERN+"\\."+IP_NUMBER_REGEXP_PATTERN+"\\."+IP_NUMBER_REGEXP_PATTERN;
	
	private final static String FROM_TO_REGEXP_PATTERN = "[\\s-]*"+IP_ADDRESS__REGEXP_PATTERN+"[\\s-]*-[\\s-]*"+IP_ADDRESS__REGEXP_PATTERN+"[\\s-]*";	
	
	public IPFilter(String pattern) {
		super();
		this.pattern = pattern.trim();
		this.setUpPatternValues();
	}
	
	public boolean isIpAddressWithinFilterRange(String ipAddress) throws MalformedIpAddressException{
		long ipAddressValue = IPFilter.getAddressLongValue(ipAddress.trim());
		return (ipAddressValue >= fromAddressLongValue && ipAddressValue <= toAddressLongValue);
	}


	public String getPattern() {
		return pattern;
	}
	
	private void handleParameterWithWildcardPattern(){
		this.fromAddress = this.pattern.replace("*", "0");
		this.toAddress = this.pattern.replace("*", "255");
		this.setUpAddressValues();
	}
	
	
	private void handleParameterWithFromToPattern(){
		StringTokenizer t = new StringTokenizer(this.pattern,"-");
		this.fromAddress = t.nextToken().trim();
		this.toAddress = t.nextToken().trim();
		this.setUpAddressValues();
	}
	
	
	private void setUpAddressValues(){
		try {
			this.toAddressLongValue = IPFilter.getAddressLongValue(this.toAddress);
			this.fromAddressLongValue = IPFilter.getAddressLongValue(this.fromAddress);
		} catch (MalformedIpAddressException e) {
			throw new InvalidIpFilterConfigurationException(this.pattern);
		}	
	}	
	
	private void setUpPatternValues(){
		
		if(pattern.matches(WILDCARD_REGEXP_PATTERN)){
			handleParameterWithWildcardPattern();
		}
		else
		if(pattern.matches(FROM_TO_REGEXP_PATTERN)){
			handleParameterWithFromToPattern();
		}
		else{
			throw new InvalidIpFilterConfigurationException(pattern);
		}


	}
	
	private static long getAddressLongValue(String ipAddress)
			throws MalformedIpAddressException {

		if (ipAddress.matches(IP_ADDRESS__REGEXP_PATTERN)) {

			Scanner sc = new Scanner(ipAddress).useDelimiter("\\.");

			Long value = (sc.nextLong() << 24) + (sc.nextLong() << 16)
					+ (sc.nextLong() << 8) + (sc.nextLong());

			return value;
		} else {
			throw new MalformedIpAddressException(ipAddress);
		}
	}
	
}
