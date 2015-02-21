package com.silvertree.ipfilter.exception;

/**
 * Exception thrown when the IpAddressFilter configuration can not be parsed.
 * @author graski
 *
 */

public class InvalidIpFilterConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1101624814913725892L;
	
	private String invalidConfiguration;
	
	@Override
	public String getMessage() {
		return "The IpFilter configuration parameter \"" + invalidConfiguration + "\" is invalid."+ super.getMessage();
	}
	
	public InvalidIpFilterConfigurationException(String invalidConfiguration) {
		super();
		this.invalidConfiguration = invalidConfiguration;
	}

	public String getInvalidConfiguration() {
		return invalidConfiguration;
	}


}
