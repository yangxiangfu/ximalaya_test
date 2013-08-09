package com.ximalaya.android.exception;

import com.jayway.android.robotium.solo.Solo;

public class NullException extends Exception {
	private static final long serialVersionUID = 1L;

	public NullException() {
		super();
	}

	public NullException(Solo solo, String message) {
		super(message);
	}

	public NullException(String message, Throwable cause) {
		super(message, cause);
	}

	public NullException(Throwable cause) {
		super(cause);
	}

}
