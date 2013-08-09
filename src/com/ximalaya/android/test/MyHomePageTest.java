package com.ximalaya.android.test;

import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.function.ViewOperationFunc;
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
		try {
			ViewOperationFunc.clickonText(solo, "我");
			ViewOperationFunc.clickById(solo, "back_img");
			if(solo.searchText("")){
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//私聊发送私信
	public void test02_SendMessage(){
		
	}
}
