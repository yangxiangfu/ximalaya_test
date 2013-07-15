package com.ximalaya.android.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.util.DB;

public class ExceptionOps {
	/***
	 * 空指针检查
	 * 
	 * @param solo
	 * @param checkStr
	 *            被检查的字符串
	 * @throws Exception
	 */
	public static void nullPointerCheck(Solo solo, String checkStr)
			throws Exception {
		String info_null;
		info_null = "空指针异常,获得的值--" + checkStr + "--为空";

		if (checkStr == null || checkStr == "") {
			throw new NullException(solo, info_null);
		}
	}

	/**
	 * 检查当前页面是否显示指定控件
	 * 
	 * @param solo
	 * @param locator
	 *            指定控件ID
	 * @throws Exception
	 */
	public static void viewCheck(Solo solo, String locator) throws Exception {
		boolean flag;
		int locator_id;
		String info_not_found = "出现异常,当前页面未找到控件:" + locator;
		;

		nullPointerCheck(solo, locator);
		locator_id = DB.getID().get(locator);
		flag = solo.waitForView(solo.getView(locator_id));

		if (!flag) {
			throw new NotFoundedException(solo, info_not_found);
		}
	}

	/**
	 * 文本未找到异常判断
	 * 
	 * @param solo
	 * @param checkText
	 *            指定检查文本
	 * @throws Exception
	 */
	public static void textCheck(Solo solo, String checkText) throws Exception {
		boolean flag;
		String info_not_found = "出现异常:当前页面未找到文本:" + checkText;

		ExceptionOps.nullPointerCheck(solo, checkText);
		flag = solo.searchText(checkText);

		if (!flag) {
			throw new NotFoundedException(solo, info_not_found);
		}
	}

	/**
	 * 打印错误堆栈信息
	 * 
	 * @param t
	 * @return
	 */
	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		t.printStackTrace(pw);
		pw.flush();
		sw.flush();
		return sw.toString();
	}

}
