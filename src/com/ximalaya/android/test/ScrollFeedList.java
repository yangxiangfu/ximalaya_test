package com.ximalaya.android.test;

import java.util.ArrayList;
import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.function.ScreenshotFunc;
import com.ximalaya.android.function.ViewOperationFunc;
import com.ximalaya.ting.android.activity.login.WelcomeActivity;
import com.ximalaya.ting.android.view.RoundedImageView;
import com.ximalaya.ting.android.R;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ToggleButton;

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
		// solo.finishOpenedActivities();
	}

	// 滚动刷新列表
	public void test01_ScroolFeedList() {
		solo.waitForActivity("MainTabActivity2");
		try {
			solo.sleep(2000);
			// 刷新列表
			solo.scrollListToTop(0);

			// 点击全部关注
			ViewOperationFunc.clickById(solo, "tab_a");
			// 编辑分组
			ViewOperationFunc.clickById(solo, "edit_tv");
			solo.sleep(1000);
			// ViewOperationFunc.clickonButtonText(solo, "完成");

			ViewOperationFunc.clickonView(solo, "bg_done_btn_on");
			solo.clickOnImageButton(0);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 操作展开节目
	public void test02_Download() {
		try {

			// 初始化清空下载列表
			//initDownloadList();

			solo.waitForActivity("MainTabActivity2");

			for (int n = 0; n < 10; n++) {
				ViewOperationFunc.clickById(solo, "expandable_toggle_button");
				solo.sleep(2000);
				
				Log.i("open", "节目展开成功");

				// 判断当前节目是否下载
				if (solo.searchText("下载")) {
					ViewOperationFunc.clickById(solo, "download_tv");
					solo.sleep(1500);
				} else if (solo.searchText("正在播放")) {
					ViewOperationFunc.clickById(solo, "back_img");
				} else if (solo.searchText("已下载")) {
					System.out.println("节目已经下载");
				}
				// 收起展开项
				ViewOperationFunc.clickById(solo, "expandable_toggle_button");
				// 向上拖动列表
				solo.drag(499, 260, 504, 110, 2);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 收藏专辑
	public void test03_Album() {
		try {
			solo.sleep(2000);
			initDownloadList();
			// 收藏专辑
			ViewOperationFunc.clickonText(solo, "发现");
			solo.sleep(2000);
			// 打开热门专辑
			ViewOperationFunc.clickById(solo, "category_name4");

			// 打开一个专辑		
//			GridView gridView = (GridView) solo.getCurrentViews().get(0);
//			LinearLayout parentView = (LinearLayout) gridView.getChildAt(0);
//			RoundedImageView roundedImageView = (RoundedImageView) parentView.findViewById(R.id.album_image);			
//			
//			ViewOperationFunc.clickonView(solo, "roundedImageView");
			ViewOperationFunc.clickById(solo, "album_image");
			
			// 点击收藏
			ViewOperationFunc.clickById(solo, "collect_img");
			solo.sleep(1000);
			// 回到主菜单
			ViewOperationFunc.clickById(solo, "home_img");
			ViewOperationFunc.clickonText(solo, "定制听");
			// 打开收藏
			ViewOperationFunc.clickById(solo, "tab_b");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 播放历史
	public void test04_PlayHistory() {
		try {
			ViewOperationFunc.clickById(solo, "tab_c");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	// 初始化下载列表
	public void initDownloadList() {
		try {
			
			
			View view = solo.getView(R.id.empty_view);
			
			ViewOperationFunc.clickonText(solo, "下载听");
			ViewOperationFunc.clickonText(solo, "声音");
			if (solo.searchText(".*?分钟")||solo.searchText(".*?小时")||solo.searchText(".*?天")) {
				ViewOperationFunc.clickById(solo, "img_batchMake");
				solo.sleep(2000);
				if (solo.searchText("请选择需要的操作")) {
					ViewOperationFunc.clickonText(solo, "一键清空");
					solo.sleep(2000);
					ViewOperationFunc.clickonButtonText(solo, "确定");
					solo.sleep(10000);
				}
			} else if(solo.searchText("")){
				ViewOperationFunc.clickonText(solo, "定制听");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
