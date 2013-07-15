package com.ximalaya.android.test;

import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.function.CommoFunc;
import com.ximalaya.android.function.ScreenshotFunc;
import com.ximalaya.android.function.ViewOperationFunc;
import com.ximalaya.android.util.ScreenShot;
import com.ximalaya.ting.android.activity.login.WelcomeActivity;

import android.graphics.Bitmap;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

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
	public void test01_Login() {
		solo.waitForActivity("LoginNewActivity", 3000);
		// 输入内容后登陆
		try {
			ViewOperationFunc.clickById(solo, "lg_regist_button");
			
			Thread.sleep(5000);
			// 输入用户名和密码
			//ViewOperationFunc.enterTextIndex(solo, 0, "ooo@uuu.com");
			solo.enterText(0, "ooo@uuu.com");
			solo.sleep(1000);
			solo.clearEditText(1);
			solo.typeText(1, "111111");
			//ViewOperationFunc.enterTextIndex(solo, 1, "111111");
			Thread.sleep(5000);
			Bitmap b=ScreenShot.takeScreenShot(getActivity());
			ScreenShot.savePicture(b, "login_picture");
			ViewOperationFunc.clickById(solo, "lg_btn");
			Thread.sleep(5000);
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void test02_Qq_Login() {
		solo.waitForActivity("LoginNewActivity",2000);
		solo.scrollToTop();
		
	}
	
	public void test03_Weibo_Login() {
		solo.waitForActivity("LoginNewActivity",2000);
		solo.scrollToTop();
		
	}


}
