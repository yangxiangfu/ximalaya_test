package com.ximalaya.android.exception;

import com.jayway.android.robotium.solo.Solo;

public class NotFoundedException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotFoundedException() {
		super();
	}

	public NotFoundedException(Solo solo, String message) {
		super(message);
	}

	public NotFoundedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundedException(Throwable cause) {
		super(cause);
	}
}
