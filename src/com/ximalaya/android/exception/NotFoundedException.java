package com.ximalaya.android.exception;

import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.function.ScreenshotFunc;
import com.ximalaya.android.util.Report;

public class NotFoundedException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotFoundedException() {
		super();
	}

	public NotFoundedException(Solo solo, String message) {
		super(message);
		Report.writeHTMLLog("当前页面指定文本或控件未找到异常", message, Report.FAIL,
				ScreenshotFunc.screenShot(solo));
	}

	public NotFoundedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundedException(Throwable cause) {
		super(cause);
	}
}
