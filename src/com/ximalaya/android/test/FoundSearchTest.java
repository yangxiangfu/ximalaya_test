package com.ximalaya.android.test;

import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.function.ScreenshotFunc;
import com.ximalaya.android.function.ViewOperationFunc;
import com.ximalaya.ting.android.activity.login.WelcomeActivity;

import android.test.ActivityInstrumentationTestCase2;

public class FoundSearchTest extends
		ActivityInstrumentationTestCase2<WelcomeActivity> {

	private Solo solo;

	public FoundSearchTest() {
		super("com.ximalaya.ting.android.activity.login", WelcomeActivity.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void tearDown() {
		solo.finishOpenedActivities();
	}

	// 无内容的时候搜索
	public void test01_NoTextSearch() {

		String notext = "不输入任何内容直接查找";
		// solo.clickOnText("");
		try {
			solo.sleep(2000);
			ViewOperationFunc.clickonText(solo, "发现");
			boolean hinttext = true;
			boolean actual = solo.searchText(".*?搜索声音、专辑、人.*?");
			assertEquals("搜索框默认值不正确", hinttext, actual);
			// 打开到搜索页面
			ViewOperationFunc.clickById(solo, "search_tv");
			solo.hideSoftKeyboard();
			ViewOperationFunc.clickById(solo, "search_button");
			solo.sleep(2000);
			boolean isnotext = solo.searchText(".*?请输入搜索关键词.*?");
			if (isnotext) {
				System.out.println("the tip is true");
			} else
				System.out.println("未检测到此提示语");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 搜索声音
	public void test02_SoundSearch() {

		// solo.clickOnText("");
		try {
			ViewOperationFunc.clickonText(solo, "发现");
			ViewOperationFunc.clickById(solo, "search_tv");
			solo.sleep(1000);
			ViewOperationFunc.clickById(solo, "today");
			ViewOperationFunc.enterTextIndex(solo, 0, "老梁");
			ViewOperationFunc.clickById(solo, "search_button");
			solo.sleep(2000);

			// 调用截图函数开始截图
			ScreenshotFunc.screenShot(solo);
			ScreenshotFunc.screenshotSolo(solo);
			solo.goBack();
			ViewOperationFunc.clearEditText(solo, 0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 搜索专辑
	public void test03_AlbumSearch() {

		// solo.clickOnText("");
		try {
			ViewOperationFunc.clickonText(solo, "发现");
			ViewOperationFunc.clickById(solo, "search_tv");
			solo.sleep(1000);
			ViewOperationFunc.clickById(solo, "necessary");
			ViewOperationFunc.enterTextIndex(solo, 0, "老梁");
			ViewOperationFunc.clickById(solo, "search_button");
			solo.sleep(2000);

			// 调用截图函数开始截图
			ScreenshotFunc.screenShot(solo);
			ScreenshotFunc.screenshotSolo(solo);
			solo.goBack();
			ViewOperationFunc.clearEditText(solo, 0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 搜索人
	public void test04_PersonSearch() {

		// solo.clickOnText("");
		try {
			ViewOperationFunc.clickonText(solo, "发现");
			ViewOperationFunc.clickById(solo, "search_tv");
			solo.sleep(1000);
			ViewOperationFunc.clickById(solo, "hot");
			ViewOperationFunc.enterTextIndex(solo, 0, "老梁");
			ViewOperationFunc.clickById(solo, "search_button");
			solo.sleep(2000);

			// 调用截图函数开始截图
			ScreenshotFunc.screenShot(solo);
			ScreenshotFunc.screenshotSolo(solo);
			solo.goBack();
			ViewOperationFunc.clearEditText(solo, 0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
