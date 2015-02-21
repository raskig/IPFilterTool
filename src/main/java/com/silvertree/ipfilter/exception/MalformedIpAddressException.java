package com.silvertree.ipfilter.exception;

/**
 * Exception thrown when the ipAddress is malformed and must be rejected.
 * @author graski
 *
 */

public class MalformedIpAddressException extends Exception {

	private static final long serialVersionUID = 7816042859272511166L;

	@Override
	public String getMessage() {
		return "The ipAddress " + forbiddenIpAddress + " is malformed and can't be processed."+ super.getMessage();
	}


	
	private String forbiddenIpAddress;

	public MalformedIpAddressException(String invalidIpAddress) {
		super();
		this.forbiddenIpAddress = invalidIpAddress;
	}

	public String getInvalidIpAddress() {
		return forbiddenIpAddress;
	}

}
