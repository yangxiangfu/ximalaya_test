package com.ximalaya.android.test;

import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.ting.android.activity.login.WelcomeActivity;

import android.test.ActivityInstrumentationTestCase2;

public class MyHomePageTest extends ActivityInstrumentationTestCase2<WelcomeActivity> {

	private Solo solo;
	public MyHomePageTest(){
		super("com.ximalaya.ting.android.activity.login",WelcomeActivity.class);
	}
	
	public void setUp(){
		solo = new Solo(getInstrumentation(),getActivity());
	}
	
	public void tearDown(){
		solo.finishOpenedActivities();
	}
	
	public void test01_ViewMyHomePage(){
		
	}
}
