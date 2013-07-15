package com.ximalaya.android.exception;

import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.function.ScreenshotFunc;
import com.ximalaya.android.util.Report;

public class DialogErrorException extends Exception {

	private static final long serialVersionUID = 1L;

	public DialogErrorException() {
		super();
	}

	public DialogErrorException(Solo solo, String message) {
		super(message);
		String testName = "错误弹出框异常";
		Report.writeHTMLLog(testName, "弹出非预期弹出框，当前用例停止运行", Report.FAIL,
				ScreenshotFunc.screenShot(solo));
	}

	public DialogErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public DialogErrorException(Throwable cause) {
		super(cause);
	}
}
