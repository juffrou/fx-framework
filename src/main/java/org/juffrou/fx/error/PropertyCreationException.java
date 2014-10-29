package org.juffrou.fx.error;

public class PropertyCreationException extends RuntimeException {

	private static final long serialVersionUID = -5245769067560002049L;

	public PropertyCreationException() {
		super();
	}

	public PropertyCreationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PropertyCreationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PropertyCreationException(String message) {
		super(message);
	}

	public PropertyCreationException(Throwable cause) {
		super(cause);
	}
	
}
