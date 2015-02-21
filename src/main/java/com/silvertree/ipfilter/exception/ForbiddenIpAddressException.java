package com.silvertree.ipfilter.exception;

/**
 * Exception thrown when the ipAddress is forbidden and must be rejected.
 * @author graski
 *
 */

public class ForbiddenIpAddressException extends Exception {

	@Override
	public String getMessage() {
		return "The ipAddress " + forbiddenIpAddress + " is forbidden and must be rejected."+ super.getMessage();
	}

	private static final long serialVersionUID = -2636004335493065272L;
	
	private String forbiddenIpAddress;

	public ForbiddenIpAddressException(String invalidIpAddress) {
		super();
		this.forbiddenIpAddress = invalidIpAddress;
	}

	public String getInvalidIpAddress() {
		return forbiddenIpAddress;
	}

}
