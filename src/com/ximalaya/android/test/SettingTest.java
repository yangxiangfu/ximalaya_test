package com.ximalaya.android.test;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.zip.DataFormatException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Ignore;

import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.function.ViewOperationFunc;
import com.ximalaya.ting.android.activity.login.WelcomeActivity;
import com.ximalaya.ting.android.R;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SettingTest extends
		ActivityInstrumentationTestCase2<WelcomeActivity> {

	private Solo solo;
	private int alarmHour;
	private int alarmMinute;

	public SettingTest() {
		super("com.ximalaya.ting.android.activity.login", WelcomeActivity.class);
	}

	public void setUp() {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void tearDown() {
		solo.finishOpenedActivities();
	}

	// 叫我起床
	public void test01_PushSetting() {
		try {
			openSetting();
			ViewOperationFunc.clickonText(solo, "叫我起床");

			// 单击指定的坐标
			solo.clickOnScreen(492, 166);
			solo.sleep(10000);
			if (solo.searchText("重复")) {
				// 打开重复的设置选项
				solo.clickOnText("重复");
				solo.sleep(1000);
				// 设置为每天重复
				isCheck();
				// 点击返回按钮返回上一个页面
				ViewOperationFunc.clickById(solo, "back_img");
			}
			ViewOperationFunc.clickonText(solo, "自动播放时间");

			//设置时间
			Calendar currentTime = Calendar.getInstance();
			alarmHour = currentTime.get(Calendar.HOUR_OF_DAY);
			alarmMinute = currentTime.get(Calendar.MINUTE);

			String hourtext = String.valueOf(alarmHour);
			String mintext = String.valueOf(alarmMinute + 1);

			// 设置小时
			solo.clearEditText(0);
			ViewOperationFunc.enterTextIndex(solo, 0, hourtext);
			// 设置闹钟时间为当前时间后一分钟
			solo.clearEditText(1);
			ViewOperationFunc.enterTextIndex(solo, 1, mintext);
			solo.sleep(2000);
			ViewOperationFunc.clickonButtonText(solo, "设置");
			solo.sleep(1000);
			
			if(solo.searchText(".*?闹钟设置成功.*?")){
				System.out.println("闹钟设置成功");
			}
			
			for (int i = 0; i <= 30; i = i + 5) {
				solo.sleep(i * 2000);
				if (solo.searchText("叫我起床闹钟")) {
					ViewOperationFunc.clickonButtonText(solo, "确定");
				}
			}

			// 初始化设置项-关闭重复天数
			initIsCheck();
			// 关闭当前设置
			solo.clickOnScreen(411, 166);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 伴我入眠
	public void test02_PlanTerminate() {
		try {
			openSetting();
			ViewOperationFunc.clickonText(solo, "伴我入眠");
			// 单击指定的坐标
			solo.clickOnScreen(492, 166);
			solo.sleep(1000);

			// 设置睡眠时间为10分钟
			// ViewOperationFunc.clickonText(solo, "10分钟");

			// 获取view控件的第一个
			ListView listView = (ListView) solo.getCurrentViews().get(0);
			LinearLayout parentView = (LinearLayout) listView.getChildAt(1);
			TextView timeTextView = (TextView) parentView
					.findViewById(R.array.sleep_delay_list);
			// 点击第一个
			solo.clickOnView(timeTextView);
			// 保存结果
			ViewOperationFunc.clickonText(solo, "完成");

			// 重新进入设置初始化设置
			ViewOperationFunc.clickonText(solo, "伴我入眠");
			// 关闭当前设置
			solo.clickOnScreen(411, 166);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Ignore
	public void openSetting() {
		try {
			ViewOperationFunc.clickonText(solo, "我");
			ViewOperationFunc.clickById(solo, "back_img");
			if (solo.searchText("设置")) {
				System.out.println("设置成功");
			} else {
				solo.goBackToActivity("MainTabActivity2");
				ViewOperationFunc.clickonText(solo, "我");
				ViewOperationFunc.clickById(solo, "back_img");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 判断是否选择复选框
	public void isCheck() {
		for (int i = 0; i < 7; i++) {
			if (solo.isCheckBoxChecked(i)) {
				System.out.println("第" + i+1 + "个复选框已经被选中");
			} else {
				solo.clickOnCheckBox(i);
			}
		}

	}

	// 初始化复选框
	public void initIsCheck() {
		try {
			ViewOperationFunc.clickonText(solo, "重复");
			for (int i = 0; i < 7; i++) {
				if (solo.isCheckBoxChecked(i)) {
					solo.clickOnCheckBox(i);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void clickView(){
		// 打开一个专辑		
//		GridView gridView = (GridView) solo.getCurrentViews().get(0);
//		LinearLayout parentView = (LinearLayout) gridView.getChildAt(0);
//		RoundedImageView roundedImageView = (RoundedImageView) parentView.findViewById(R.id.album_image);	
	}
}
