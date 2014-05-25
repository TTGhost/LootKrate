package com.caved_in.lootkrate.exceptions;

public class InsufficientLootLocationsException extends Exception {
	public InsufficientLootLocationsException() {
		super();
	}

	public InsufficientLootLocationsException(String message) {
		super(message);
	}

	public InsufficientLootLocationsException(String message, Throwable cause) {
		super(message,cause);
	}

	public InsufficientLootLocationsException(Throwable cause) {
		super(cause);
	}
}
