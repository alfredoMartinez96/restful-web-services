package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;
//BEAN DE RESPUESTA DE EXCEPCIONES SIMPLE BASICAMENTE ES LO QUE SE CREO
public class ExceptionResponse {
	//MARCA DE TIEMPO PARA VER DONDE OCURRIO LA EXCEPCION
	private Date timestamp;
	//MENSAJE DE EXCEPCION
	private String message;
	//DETALLES DE LA EXCEPCION
	private String details;

	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
