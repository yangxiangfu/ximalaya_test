package com.ximalaya.android.exception;

import com.jayway.android.robotium.solo.Solo;

public class DialogErrorException extends Exception {

	private static final long serialVersionUID = 1L;

	public DialogErrorException() {
		super();
	}

	public DialogErrorException(Solo solo, String message) {
		super(message);
	}

	public DialogErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public DialogErrorException(Throwable cause) {
		super(cause);
	}
}
