package com.ximalaya.android.function;

import android.app.Dialog;
import android.view.ViewTreeObserver;

import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.exception.DialogErrorException;
import com.ximalaya.android.util.Report;
import com.ximalaya.android.v2.AutoRobotium;

/*
 * @author chrisandy
 * @createDate 2013/05/28
 * 常用的公共方法
 * */
public class CommoFunc {
	// 从config文件内获取超时时间
	public static int timeout = Integer.parseInt(AutoRobotium.iniFile
			.get("timeout"));

	// 从config文件内获取重试的次数
	public static int retries = Integer.parseInt(AutoRobotium.iniFile
			.get("retries"));

	// 默认的构造方法
	public CommoFunc() {

	}

	/**
	 * 上拉页面至顶
	 * 
	 * @param solo
	 * */
	public static void scroolUpPage(Solo solo) {
		String testName = "上拉页面至顶";
		boolean scroolFlag = true;
		while (scroolFlag) {
			scroolFlag = solo.scrollUp();
			Report.writeHTMLLog(testName, "scrool up", Report.DONE, "");
		}
		Report.writeHTMLLog(testName, "已scroll页面至顶", Report.DONE, "");
	}

	/***
	 * 上拉页面至顶
	 * 
	 * @param solo
	 */
	public static void scrollPageToTop(Solo solo) {
		String testName = "上拉页面至顶";
		solo.scrollToTop();
		Report.writeHTMLLog(testName, "已scroll页面到顶", Report.DONE, "");
	}

	/**
	 * 下拉页面置底
	 * */
	public static void scroolDownPage(Solo solo) {
		String testName = "下拉页面至底";
		boolean scroolFlag = true;
		while (scroolFlag) {
			scroolFlag = solo.scrollDown();
			Report.writeHTMLLog(testName, "scrool down", Report.DONE, "");
		}
		Report.writeHTMLLog(testName, "已scroll页面置底", Report.DONE, "");
	}

	/**
	 * 上拉页面至顶
	 * 
	 * @param solo
	 */
	public static void scrollPageToBottom(Solo solo) {
		String testName = "上拉页面至顶";
		solo.scrollToBottom();
		Report.writeHTMLLog(testName, "已scroll页面至顶", Report.DONE, "");
	}

	/**
	 * 选择列表下拉页面
	 * 
	 * @param solo
	 * */
	public static void scrollDownlist(Solo solo, int index) {
		String testName = "选择列表下拉页面";
		solo.scrollDownList(index);
		Report.writeHTMLLog(testName, "下拉当前页面指定下标" + index + "的控件",
				Report.DONE, "");
	}

	/**
	 * 点击信息提示框的确定按钮
	 * 
	 * @param solo
	 * @throws Exception
	 * */
	public static void alertConfimOk(Solo solo) {
		solo.waitForText("请选择需要的操作");
		if (ViewOperationFunc.searchText(solo, "请选择需要的操作")) {
			Report.writeHTMLLog("", "", Report.DONE, "");
			solo.clickOnButton("确定");
		}
	}

	/**
	 * 点击信息提示框的确定按钮
	 * 
	 * @param solo
	 * @throws Exception
	 * */
	public static void alertConfimCancel(Solo solo) {
		solo.waitForText("请选择需要的操作");
		if (ViewOperationFunc.searchText(solo, "请选择需要的操作")) {
			Report.writeHTMLLog("", "", Report.DONE, "");
			solo.clickOnButton("取消");
		}
	}
	
	/**
	 * 账户登录
	 * 
	 * @param solo
	 * @throws exception
	 * */
	public static void login(Solo solo) throws Exception {
		String userName = AutoRobotium.iniFile.get("username");
		String passWord = AutoRobotium.iniFile.get("password");
		login(solo, userName, passWord);
	}

	/**
	 * 账户登录
	 * 
	 * @param solo
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param exception
	 * */
	public static void login(Solo solo, String userName, String passWord)
			throws Exception {
		String testName = "登录功能";
		// 进入喜马拉雅登录主页
		ViewOperationFunc.waitforView(solo, "lg_visite_tv");
		Report.writeHTMLLog("", "喜马拉雅登录主页加载成功", Report.DONE, "");
		ViewOperationFunc.clickById(solo, "lg_regist_button");
		if (ViewOperationFunc.searchTextNotScroll(solo, "登录")) {
			// 用户名密码
			ViewOperationFunc.enterTextIndex(solo, 0, userName);
			ViewOperationFunc.enterTextIndex(solo, 1, passWord);
			ViewOperationFunc.clickById(solo, "lg_btn");
			if (ViewOperationFunc.searchTextNotScroll(solo, "网络连接不可用，请检查网络设置")) {
				Report.writeHTMLLog(testName, "网络连接失败，用例停止运行", Report.FAIL, "");
			}
			// 连接超时
			if (ViewOperationFunc.searchTextNotScroll(solo, "连接超时")) {
				ViewOperationFunc.clickonText(solo, "确定");
			}
			Report.writeHTMLLog(testName, "登录成功", Report.DONE, "");
		}
		// CommoFunc.goback(solo);
		ViewOperationFunc.waitForText(solo, "全部");
		Report.writeHTMLLog(testName, "成功加载到主页面", Report.DONE, "");
	}

	/**
	 * 搜索弹出框，找到的话就截图
	 * 
	 * @param solo
	 * */
	public static void searchDialogScreenShot(Solo solo) throws Exception {
		String testName = "搜索弹出框截图";
		// 搜索当前页面有没有弹出框title提示信息出现
		if (ViewOperationFunc.searchText(solo, "提示信息", true)
				&& !ViewOperationFunc.searchText(solo, "恭喜您")) {
			ScreenshotFunc.screenshotSolo(solo);
			// 需要抛出异常
			throw new DialogErrorException(solo, "软件弹出框显示异常信息");

		} else {
			Report.writeHTMLLog(testName, "没有弹出框出现", Report.DONE, "");
		}
	}

	/**
	 * 返回到上级界面
	 * */
	public static void goback(Solo solo) {
		String testName = "返回上级界面";
		solo.goBack();
		Report.writeHTMLLog(testName, "成功返回上级界面", Report.DONE, "");
	}
}
