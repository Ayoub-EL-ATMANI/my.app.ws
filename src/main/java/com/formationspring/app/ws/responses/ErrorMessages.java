package com.formationspring.app.ws.responses;

public enum ErrorMessages {
	
	MISSING_REQUIRED_FIELD("Missing required field."), // the fields not respected 
	RECORD_ALREADY_EXISTS("Record already exists."),// already exist in db 
	INTERNAL_SERVER_ERROR("Internal Bright coding error."),
	NO_RECORD_FOUND("Record with provied is is not found.");// object not exist 

	private String errorMessages;
	
	private ErrorMessages (String errorMessages) {
		this.errorMessages = errorMessages;
	}

	public String getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	

}
