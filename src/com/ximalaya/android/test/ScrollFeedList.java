package com.ximalaya.android.test;

import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.function.CommoFunc;
import com.ximalaya.android.function.ViewOperationFunc;
import com.ximalaya.ting.android.activity.login.WelcomeActivity;

import android.test.ActivityInstrumentationTestCase2;

public class ScrollFeedList extends
		ActivityInstrumentationTestCase2<WelcomeActivity> {

	private Solo solo;

	public ScrollFeedList() {
		super("com.ximalaya.ting.android.activity.login", WelcomeActivity.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void tearDown() {
		//solo.finishOpenedActivities();
	}

	public void test01_ScroolFeedList() {
		solo.waitForActivity("MainTabActivity2");
		try {
			Thread.sleep(2000);
			//CommoFunc.scrollPageToBottom(solo);
			ViewOperationFunc.clickById(solo, "tab_b");
			Thread.sleep(1000);
			ViewOperationFunc.clickById(solo, "tab_c");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
