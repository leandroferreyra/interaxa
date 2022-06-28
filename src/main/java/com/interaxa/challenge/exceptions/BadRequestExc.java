package com.interaxa.challenge.exceptions;

public class BadRequestExc extends Exception {
	
	public BadRequestExc(String msj) {
		super(msj);
	}
	
	public BadRequestExc(String msj, Exception e) {
		super(msj, e);
	}

}
