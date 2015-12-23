/**
 * 
 */
package com.mastermind.thanh.mastermind.models;

/**
 * @author DANH NGUYEN
 *
 */
public class Header {
	/**
	 * status of header
	 */
	public String status;
	/**
	 * message a message from server
	 */
	public String message;
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
