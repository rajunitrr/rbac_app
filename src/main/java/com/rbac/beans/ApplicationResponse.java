package com.rbac.beans;

import java.io.Serializable;

import lombok.Data;

@Data
public class ApplicationResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long applicationId;
	
	private String applicationName;
}
