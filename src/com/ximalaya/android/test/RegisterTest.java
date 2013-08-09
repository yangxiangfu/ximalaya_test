package com.ximalaya.android.test;

import static org.junit.Assert.*;
import com.ximalaya.ting.android.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.function.CommoFunc;
import com.ximalaya.android.function.ScreenshotFunc;
import com.ximalaya.android.function.ViewOperationFunc;
import com.ximalaya.ting.android.activity.login.RegisterActivity;
import com.ximalaya.ting.android.activity.login.WelcomeActivity;

import android.provider.ContactsContract.CommonDataKinds.Nickname;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class RegisterTest extends
		ActivityInstrumentationTestCase2<WelcomeActivity> {

	private Solo solo;
	private String nickname = "testregister" + Random();
	private String email = "uuu" + Random() + "ooo@www.com";

	public RegisterTest() {
		super("com.ximalaya.ting.android.activity.login", WelcomeActivity.class);
		// TODO Auto-generated constructor stub
	}

	@Before
	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	@After
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	// 不输入昵称的情况
	public void test01_NoNickName() {
		try {
			exitLogin();
			ViewOperationFunc.clickById(solo, "lg_phone_button");
			solo.sleep(2000);
			solo.waitForActivity(RegisterActivity.class);
			// 点击邮箱注册
			ViewOperationFunc.clickById(solo, "btn_02");
			solo.sleep(2000);
			// 昵称为空
			solo.clearEditText(0);
			// 邮箱
			solo.clearEditText(1);
			ViewOperationFunc.enterTextIndex(solo, 1, email);
			// 密码
			solo.clearEditText(2);
			ViewOperationFunc.enterTextIndex(solo, 2, "123456");

			// 提交注册内容
			ViewOperationFunc.clickById(solo, "reg_sbmt_btn");
			if (solo.searchText(".*?请输入昵称.*?")) {
				System.out.println("----->昵称为空提示正确!");
				ViewOperationFunc.clickonButtonText(solo, "确定");
			}
			solo.sleep(2000);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 不输入邮箱的情况
	public void test02_NoEmail() {
		try {
			solo.sleep(1000);
			if(solo.searchText("发现")){
				solo.goBack();
			}
			ViewOperationFunc.clickById(solo, "lg_phone_button");
			solo.sleep(2000);
			solo.waitForActivity(RegisterActivity.class);
			// 点击邮箱注册
			ViewOperationFunc.clickById(solo, "btn_02");
			solo.sleep(2000);
			// 昵称
			solo.clearEditText(0);
			ViewOperationFunc.enterTextIndex(solo, 0, nickname);
			// 邮箱为空
			solo.clearEditText(1);
			// 密码
			solo.clearEditText(2);
			ViewOperationFunc.enterTextIndex(solo, 2, "123456");

			// 提交注册内容
			ViewOperationFunc.clickById(solo, "reg_sbmt_btn");
			if (solo.searchText(".*?请输入邮箱.*?")) {
				System.out.println("----->邮箱为空提示正确!");
				ViewOperationFunc.clickonButtonText(solo, "确定");
			}
			solo.sleep(2000);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 不输入密码的情况
	public void test03_NoPassWord() {
		try {
			solo.sleep(1000);
			if(solo.searchText("发现")){
				solo.goBack();
			}
			ViewOperationFunc.clickById(solo, "lg_phone_button");
			solo.sleep(2000);
			solo.waitForActivity(RegisterActivity.class);
			// 点击邮箱注册
			ViewOperationFunc.clickById(solo, "btn_02");
			solo.sleep(2000);
			// 昵称
			solo.clearEditText(0);
			ViewOperationFunc.enterTextIndex(solo, 0, nickname);
			// 邮箱
			solo.clearEditText(1);
			ViewOperationFunc.enterTextIndex(solo, 1, email);
			// 密码为空
			solo.clearEditText(2);

			// 提交注册内容
			ViewOperationFunc.clickById(solo, "reg_sbmt_btn");
			if (solo.searchText(".*?请输入密码.*?")) {
				System.out.println("----->密码为空提示正确!");
				ViewOperationFunc.clickonButtonText(solo, "确定");
			}
			solo.sleep(2000);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 查看注册协议
	public void test04_RegisterAgreement() {
		try {
			solo.sleep(1000);
			if(solo.searchText("发现")){
				solo.goBack();
			}
			ViewOperationFunc.clickById(solo, "lg_phone_button");
			solo.sleep(2000);
			solo.waitForActivity(RegisterActivity.class);
			// 点击邮箱注册
			ViewOperationFunc.clickById(solo, "btn_02");
			solo.sleep(2000);
			ViewOperationFunc.clickonText(solo, "注册协议");
			solo.sleep(2000);
			if (solo.searchText(".*?完全了解.*?")) {
				System.out.println("打开注册协议成功");
				solo.goBack();
				if (solo.searchText(".*?注册协议.*?")) {
					System.out.println("返回注册页面成功");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	// 成功注册账号
	public void test05_EmailRegister() throws Exception {
		try {
			if(solo.searchText("发现")){
				solo.goBack();
			}
			ViewOperationFunc.clickById(solo, "lg_phone_button");
			solo.sleep(2000);
			solo.waitForActivity(RegisterActivity.class);
			// 点击邮箱注册
			ViewOperationFunc.clickById(solo, "btn_02");
			solo.sleep(2000);
			// 昵称
			solo.clearEditText(0);
			ViewOperationFunc.enterTextIndex(solo, 0, nickname);
			// 邮箱
			solo.clearEditText(1);
			ViewOperationFunc.enterTextIndex(solo, 1, email);
			// 密码
			solo.clearEditText(2);
			ViewOperationFunc.enterTextIndex(solo, 2, "123456");

			// 提交注册内容
			ViewOperationFunc.clickById(solo, "reg_sbmt_btn");
			if (solo.searchText(".*?注册中.*?")) {
				System.out.println("注册内容提交成功");
			} else if (solo.searchText(".*?已被注册.*?")) {
				solo.clearEditText(1);
				ViewOperationFunc.enterTextIndex(solo, 1, email);
			}
			solo.sleep(2000);

			// 选择分类			
			ViewOperationFunc.clickById(solo, "meimage_item");
			solo.scrollToBottom();
			// 开始截图
			ScreenshotFunc.screenShot(solo);
			ScreenshotFunc.screenshotSolo(solo);
			// 点击下一步
			ViewOperationFunc.clickById(solo, "next_btn3");
			solo.sleep(5000);

			// 开始截图
			ScreenshotFunc.screenShot(solo);
			ScreenshotFunc.screenshotSolo(solo);

			// 立即进入
			ViewOperationFunc.clickById(solo, "next_btn4");
			solo.sleep(6000);
			if (solo.searchText("全部关注")) {
				System.out.println("注册新账号成功");
			}else{
				System.out.println("注册新账号失败");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 产生一个随机数
	public static int Random() {
		int a = (int) (Math.random() * 100) + 50;
		return a;
	}

	// 退出登录
	public void exitLogin() throws Exception {
		if (solo.searchText(".*?全部关注.*?")) {
			ViewOperationFunc.clickonText(solo, "下载听");
			ViewOperationFunc.clickonText(solo, "我");
			solo.sleep(2000);
			// 点击打开到设置页面
			ViewOperationFunc.clickById(solo, "back_img");
			solo.sleep(2000);
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
}
