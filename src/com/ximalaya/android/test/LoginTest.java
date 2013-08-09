package com.ximalaya.android.test;

import org.junit.Ignore;

import com.ximalaya.ting.android.R;
import com.jayway.android.robotium.solo.By;
import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.function.ViewOperationFunc;
import com.ximalaya.android.util.ScreenShot;
import com.ximalaya.ting.android.activity.login.WelcomeActivity;

import android.graphics.Bitmap;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;

public class LoginTest extends
		ActivityInstrumentationTestCase2<WelcomeActivity> {

	private Solo solo;

	public LoginTest() {
		super("com.ximalaya.ting.android.activity.login", WelcomeActivity.class);
	}

	@Override
	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	@Override
	public void tearDown() {
		solo.finishOpenedActivities();
	}

	// 实现登录方法
	public void test01_Login() throws Throwable {
		exitLogin();
		solo.waitForActivity("LoginNewActivity", 3000);

		// 输入内容后登陆
		try {
			ViewOperationFunc.clickById(solo, "lg_regist_button");
			solo.sleep(2000);

			// 输入用户名和密码
			solo.clearEditText(0);
			ViewOperationFunc.enterTextIndex(solo, 0, "testximalaya01@163.com");
			solo.clearEditText(1);
			solo.hideSoftKeyboard();
			ViewOperationFunc.enterTextIndex(solo, 1, "123456");

			solo.sleep(2000);
			// 点击登录按钮
			ViewOperationFunc.clickById(solo, "lg_btn");
			solo.sleep(3000);

			if (solo.searchText("全部关注")) {
				Log.i("login", "登录成功");
			} else if (solo.searchText(".*?该账号密码不正确.*?")) {
				solo.clearEditText(1);
				ViewOperationFunc.clickonButtonText(solo, "确定");
				solo.enterText(1, "123456");
			} else if (solo.searchText(".*?网络.*?")) {
				solo.clickOnText("确定");
				ViewOperationFunc.clickById(solo, "lg_btn");
			} else if (solo.searchText(".*?超时.*?")) {
				solo.clickOnText("确定");
				ViewOperationFunc.clickById(solo, "lg_btn");
			}

			// 对当前页面截图
			Bitmap b = ScreenShot.takeScreenShot(getActivity());
			ScreenShot.savePicture(b, "login_picture");

			solo.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Ignore
	public void notest02_QqLogin() throws Exception {
		solo.waitForActivity("LoginNewActivity", 2000);
		exitLogin();
		try {
			// 是发现页就返回
			isFound();
			solo.sleep(2000);
			ViewOperationFunc.clickById(solo, "lg_qq_button");
			solo.sleep(5000);
			solo.clickOnWebElement(By.className("btn btn_lightblue btn_login"));
			exitLogin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void test03_WeiboLogin() throws Exception {
		exitLogin();
		solo.waitForActivity("LoginNewActivity", 2000);
		try {
			isFound();
			ViewOperationFunc.clickById(solo, "lg_sina_button");
			solo.sleep(2000);
			// 在授权页面输入用户名密码
			solo.enterTextInWebElement(By.name("userId"), "shuilan_825@126.com");
			solo.enterTextInWebElement(By.name("passwd"), "shuilan825");
			solo.clickOnWebElement(By.className("btnP"));
			solo.sleep(6000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Ignore
	// 退出登录
	public void exitLogin() throws Exception {
		if (solo.searchText(".*?全部关注.*?")) {
			ViewOperationFunc.clickonText(solo, "我");
			solo.sleep(2000);
			// 点击打开到设置页面
			ViewOperationFunc.clickById(solo, "back_img");
			solo.scrollToBottom();
			ViewOperationFunc.clickonText(solo, "注销登录");
			solo.sleep(2000);
			boolean text = solo.searchText(".*?确定要注销账号吗.*?");
			if (text) {
				ViewOperationFunc.clickonButtonText(solo, "确定");
			}
			solo.sleep(5000);
		} else {
			System.out.println("没有登录");
		}
	}

	@Ignore
	public void isFound() {
		if (solo.searchText("发现")) {
			solo.goBack();
		}
	}

}
