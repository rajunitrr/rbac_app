package com.rbac.beans;

import lombok.Data;

@Data
public class DBOperationResponse {
	
	String status;
	String errorMessage;
	String errorCode;

}
