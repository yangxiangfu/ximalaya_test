package com.ximalaya.android.test;

import com.jayway.android.robotium.solo.By;
import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.function.CommoFunc;
import com.ximalaya.android.function.ViewOperationFunc;
import com.ximalaya.android.util.ScreenShot;
import com.ximalaya.ting.android.activity.login.WelcomeActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;

import com.ximalaya.ting.android.R;

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

	// public void test01_IsLoading(){
	// boolean text1=solo.searchText("直接收听");
	// Activity activity =solo.getCurrentActivity();
	// System.out.println(activity);
	// solo.assertCurrentActivity("当前activity是正确的","WelcomeActivity");
	//
	// //拖动屏幕滚动
	// solo.scrollToSide(Solo.RIGHT);
	// solo.scrollToSide(Solo.RIGHT);
	// solo.scrollToSide(Solo.RIGHT);
	// solo.scrollToSide(Solo.RIGHT);
	// //引导到最后一张
	// solo.clickOnImage(0);
	// solo.sleep(1000);
	// if(!text1){
	// Log.i("", "引导页结束");
	// }
	// else{
	// Log.i("", "不是新登录页面");
	// }
	//
	// }

	// 实现登录方法
	public void test01_Login() {
		solo.waitForActivity("LoginNewActivity", 3000);
		// 输入内容后登陆
		try {
			ViewOperationFunc.clickById(solo, "lg_regist_button");

			Thread.sleep(5000);
			// 输入用户名和密码
			ViewOperationFunc.enterTextIndex(solo, 0, "ooo@uuu.com");
			solo.sleep(1000);
			solo.clearEditText(1);
			ViewOperationFunc.enterTextIndex(solo, 1, "111111");
			Thread.sleep(5000);
			// 对当前页面截图
			Bitmap b = ScreenShot.takeScreenShot(getActivity());
			ScreenShot.savePicture(b, "login_picture");
			ViewOperationFunc.clickById(solo, "lg_btn");
			Thread.sleep(5000);

			boolean IsLogin = solo.searchText("全部关注");
			if (IsLogin) {
				Log.i("login", "登录成功");
			} else {
				Log.i("login", "登录失败");
			}
			exitSign();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void test02_QqLogin() {
		solo.waitForActivity("LoginNewActivity", 2000);
		try {
			ViewOperationFunc.clickById(solo, "lg_qq_button");
			solo.sleep(5000);
			solo.clickOnWebElement(By.className("btn btn_lightblue btn_login"));
			exitSign();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void test03_WeiboLogin() {
		solo.waitForActivity("LoginNewActivity", 2000);
		try {
			ViewOperationFunc.clickById(solo, "lg_sina_button");
			solo.sleep(2000);
			// 在授权页面输入用户名密码
			solo.enterTextInWebElement(By.name("userId"), "shuilan_825@126.com");
			solo.enterTextInWebElement(By.name("passwd"), "shuilan825");
			solo.clickOnWebElement(By.className("btnP"));
			solo.sleep(2000);
			solo.clickOnWebElement(By.name("允许"));
			solo.sleep(5000);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 退出登录
	public void exitSign() throws Exception {
		ViewOperationFunc.clickonText(solo, "我");
		solo.sleep(2000);
		// 点击打开到设置页面
		ViewOperationFunc.clickById(solo, "back_img");
		CommoFunc.scrollPageToTop(solo);
		ViewOperationFunc.clickonText(solo, "注销登录");
		boolean text = solo.searchText(".*?确定要注销账号吗.*?");
		if (text) {
			ViewOperationFunc.clickonText(solo, "确定");
		}
	}

}
