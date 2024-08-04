package com.rbac.beans;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
public class RbacResponse {

	String status;
	String errorCode;
	String errorMessage;
	
	Object response;
	
}
