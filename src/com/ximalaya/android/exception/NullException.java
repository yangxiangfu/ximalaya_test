package com.ximalaya.android.exception;

import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.function.ScreenshotFunc;
import com.ximalaya.android.util.Report;

public class NullException extends Exception {
	private static final long serialVersionUID = 1L;

	public NullException() {
		super();
	}

	public NullException(Solo solo, String message) {
		super(message);
		Report.writeHTMLLog("空指针异常", message, Report.FAIL,
				ScreenshotFunc.screenShot(solo));
	}

	public NullException(String message, Throwable cause) {
		super(message, cause);
	}

	public NullException(Throwable cause) {
		super(cause);
	}

}
