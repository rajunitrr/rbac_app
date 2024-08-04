package com.rbac.beans;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class DBOperationResponse {
	
	String status;
	String errorMessage;
	String errorCode;

}
