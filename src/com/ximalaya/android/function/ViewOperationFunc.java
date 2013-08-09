package com.ximalaya.android.function;

import java.util.ArrayList;

import android.app.Activity;
import android.widget.EditText;

import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.exception.ExceptionOps;
import com.ximalaya.android.exception.NotFoundedException;
import com.ximalaya.android.util.DB;

/**
 * @author chrisandy
 * @createDate 2013/05/28 常用的公共方法
 * */
public class ViewOperationFunc {

	// 构造方法
	public ViewOperationFunc() {

	}

	private static int timeout = 3;

	/**
	 * 等待文本，等待时间内获取不到文本抛出异常
	 * 
	 * @param text
	 *            等待的目标文本值
	 * */
	public static void waitForText(Solo solo, String text) throws Exception {
		int num = 0;
		String info_not_found = "出现异常未找到文本" + text;

		while (!ViewOperationFunc.searchText(solo, text, true) && num < timeout) {
			solo.sleep(1000);
			CommoFunc.searchDialogScreenShot(solo);
			CommoFunc.scroolUpPage(solo);
			num++;
		}
		if (timeout == 0) {
			throw new NotFoundedException(solo, info_not_found);
		}
	}


	
	/**
	 * 等待控件，等待时间内不出现抛出异常
	 * 
	 * @throws Exception
	 * */
	public static void waitforView(Solo solo, String locator) throws Exception {
		int locatorID;
		int num = 0;
		String testName = "等待当前页面中的指定控件";

		String info_not_found = testName + "出现异常,未找到控件:" + locator;
		locatorID = DB.getID().get(locator);
		System.out.println("---" + locatorID);
		while (!solo.waitForView(solo.getView(locatorID)) && num < timeout) {
			CommoFunc.scroolUpPage(solo);
			CommoFunc.searchDialogScreenShot(solo);
			num++;
		}
		if (num == timeout) {
			throw new NotFoundedException(solo, info_not_found);
		}
	}

	/**
	 * 搜索当前页面的view
	 * 
	 * @param solo
	 * @param locatorID
	 *            目标控件的ID
	 * */
	public static Boolean searchView(Solo solo, String locatorID) {
		Boolean flag = false;
		if (solo.getView(DB.getID().get(locatorID)) != null) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 搜索当前页面是否存在特定的文本
	 * 
	 * */
	public static Boolean searchText(Solo solo, String value) {
		Boolean flag;
		flag = solo.searchText(value);
		return flag;
	}

	/**
	 * 搜索页面可见的文本
	 * */
	public static Boolean searchText(Solo solo, String value, Boolean isVisiable)
			throws Exception {
		Boolean flag;
		flag = solo.searchText(value, isVisiable);
		return flag;// 返回布尔值

	}

	/***
	 * 搜索当前页面存在特定文本
	 * 
	 * @param solo
	 * @param value
	 *            文本值
	 * @param num
	 *            匹配的次数
	 * @param isScroll
	 *            是否滚动屏幕
	 * @param isVisible
	 *            是否可见文本
	 * @return
	 */
	public static Boolean searchText(Solo solo, String value, int num,
			Boolean isScroll, Boolean isVisiable) {
		Boolean flag;
		flag = solo.searchText(value, num, isScroll, isVisiable);
		return flag;
	}

	// 搜索当前页面可见文本，不做下拉操作
	public static Boolean searchTextNotScroll(Solo solo, String value) {
		Boolean flag;
		flag = searchText(solo, value, 1, false, true);
		return flag;
	}

	/**
	 * 往当前页面指定框输入值
	 * 
	 * @param index
	 *            编辑框下标
	 * @param value
	 *            编辑框的值
	 * @param exception
	 * @throws Exception
	 * */
	public static void enterTextIndex(Solo solo, int index, String value)
			throws Exception {
		ExceptionOps.nullPointerCheck(solo, value);
		solo.clearEditText(index);
		solo.enterText(index, value);
	}

	/**
	 * 点击特定的文字
	 * 
	 * @throws Exception
	 * */
	public static void clickonText(Solo solo, String text) throws Exception {
		waitForText(solo, text);
		solo.clickOnText(text);
	}

	/**
	 * 长按指定的文字
	 * 
	 * @param solo
	 * @throws Exception
	 * */
	public static void clickLongonText(Solo solo, String text) throws Exception {
		waitForText(solo, text);
		solo.clickLongOnText(text);
	}

	/***
	 * 判断是否需要点击目标文本
	 * 
	 * @param solo
	 * @param textArray
	 * @throws Exception
	 */
	public static void ifClickOnText(Solo solo, ArrayList<String> textArray)
			throws Exception {
		String clickAfterText = textArray.get(0);
		String targetText = textArray.get(1);

		// 判断是否存在已被点击过的文本，如果已被点击则不做任何操作
		if (ViewOperationFunc.searchText(solo, clickAfterText)) {
		} else {
			clickonText(solo, targetText);
		}

	}

	/***
	 * 给以ID来定位的EditorText来输入给定的文本值
	 * 
	 * @param solo
	 * @param locatorId
	 *            目标文本的控件定位ID
	 * @param text
	 *            输入的文本值
	 * @throws Exception
	 */
	public static void enterTextLoc(Solo solo, String text, String locatorID)
			throws Exception {
		int locatorid = DB.getID().get(locatorID);
		ExceptionOps.viewCheck(solo, locatorID);
		solo.enterText((EditText) solo.getView(locatorid), text);
	}
	
	

	/***
	 * 根据给定的locator点击对应的控件
	 * 
	 * @param solo
	 * @param locator
	 *  目标控件定位ID
	 * @throws Exception
	 */
	public static void clickonView(Solo solo, String locator) throws Exception {
		waitforView(solo, locator);
		int locatorID = DB.getID().get(locator);
		solo.clickOnView(solo.getView(locatorID));
	}

	

	/***
	 * 根据给定的locator点击对应的控件
	 * 无源码
	 * @param solo
	 * @param locator
	 *            目标控件定位ID
	 * @throws Exception
	 */
	public static void clickById(Solo solo, String locator) throws Exception {
		Activity act=solo.getCurrentActivity();
		int locatorID = act.getResources().getIdentifier(locator, "id",solo.getCurrentActivity().getPackageName());
		solo.clickOnView(solo.getView(locatorID));
		solo.sleep(400);
	}
	
	
	/**
	 * 点击指定序列号的按钮
	 * */
	public static void clickonButtonIndex(Solo solo, int index) {
		solo.clickOnButton(index);
	}

	/**
	 * 点击指定文本的按钮
	 * @throws Exception
	 * */
	public static void clickonButtonText(Solo solo, String value)
			throws Exception {
		ExceptionOps.nullPointerCheck(solo, value);
		solo.clickOnButton(value);
	}

	/***
	 * 清空特定序列号的编辑文本框
	 * 
	 * @param solo
	 * @param index
	 *            将要被清空的编辑文本框序列
	 */
	public static void clearEditText(Solo solo, int index) {
		solo.clearEditText(index);
	}

}
