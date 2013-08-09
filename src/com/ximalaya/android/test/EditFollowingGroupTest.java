package com.ximalaya.android.test;

import java.util.ArrayList;

import org.junit.Test;

import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.function.ViewOperationFunc;
import com.ximalaya.ting.android.activity.login.WelcomeActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

public class EditFollowingGroupTest extends
		ActivityInstrumentationTestCase2<WelcomeActivity> {

	private Solo solo;

	public EditFollowingGroupTest() {
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

	// 增加分组
	@Test
	public void test01_AddGroup() {
		try {

			// 点击全部关注
			ViewOperationFunc.clickById(solo, "tab_a");
			// 编辑分组
			ViewOperationFunc.clickById(solo, "edit_tv");

			ViewOperationFunc.clickonText(solo, "创建分组");
			ViewOperationFunc.clearEditText(solo, 1);
			ViewOperationFunc.enterTextIndex(solo, 1, "未命名1");

			solo.goBack();
			if (solo.searchText(".*?是否保存分组修改.*?")) {
				ViewOperationFunc.clickonButtonText(solo, "确定");
			}
			solo.sleep(2000);
			// 返回重新验证是否保存成功，点击全部关注
			// 点击全部关注
			ViewOperationFunc.clickById(solo, "tab_a");
			
			if (!solo.searchText(".*?未命名1.*?")) {
				System.out.println("新添加分组成功");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 修改分组不保存
	public void test02_AlterGroup() {
		try {
			// 点击全部关注
			ViewOperationFunc.clickById(solo, "tab_a");
			// 编辑分组
			ViewOperationFunc.clickById(solo, "edit_tv");

			// 点击删除第一个
			ViewOperationFunc.clickById(solo, "imageview");
			solo.goBack();
			if (solo.searchText(".*?是否保存分组修改.*?")) {
				System.out.println("修改不保存是有提示的");
				ViewOperationFunc.clickonButtonText(solo, "取消");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 修改保存分组
	public void test03_SaveAlterGroup() {
		try {
			// 点击全部关注
			ViewOperationFunc.clickById(solo, "tab_a");
			// 编辑分组
			ViewOperationFunc.clickById(solo, "edit_tv");

			// ViewOperationFunc.clickonText(solo, "创建分组");
			ViewOperationFunc.clearEditText(solo, 0);
			ViewOperationFunc.enterTextIndex(solo, 0, DataGroup(Random()));
			solo.goBack();
			if (solo.searchText(".*?是否保存分组修改.*?")) {
				System.out.println("修改不保存是有提示的");
				ViewOperationFunc.clickonButtonText(solo, "确定");
			}

			solo.sleep(2000);
			// 返回重新验证是否保存成功，点击全部关注
			ViewOperationFunc.clickById(solo, "tab_a");
			// 编辑分组
			ViewOperationFunc.clickById(solo, "edit_tv");
			for (int i = 0; i < 10; i++) {
				if (solo.searchText(DataGroup(i))) {
					System.out.println("修改分组保存成功");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 删除分组
	public void test04_DeleteGroup() {
		try {
			// 点击全部关注
			ViewOperationFunc.clickById(solo, "tab_a");
			// 编辑分组
			ViewOperationFunc.clickById(solo, "edit_tv");
			// 点击删除第一个
			ViewOperationFunc.clickById(solo, "imageview");

			solo.goBack();
			if (solo.searchText(".*?是否保存分组修改.*?")) {
				ViewOperationFunc.clickonButtonText(solo, "确定");
			}
			solo.sleep(2000);
			// 返回重新验证是否保存成功

			// 点击全部关注
			ViewOperationFunc.clickById(solo, "tab_a");
			if (!solo.searchText(".*?新闻.*?")) {
				System.out.println("删除分组成功");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 同名分组是否有提示
	public void test05_SameGroup() {
		try {
			// 点击全部关注
			ViewOperationFunc.clickById(solo, "tab_a");
			// 编辑分组
			ViewOperationFunc.clickById(solo, "edit_tv");
			// 点击创建新分组
			ViewOperationFunc.clickonText(solo, "创建分组");
			// 点击完成
			solo.goBack();
			if (solo.searchText(".*?是否保存分组修改.*?")) {
				ViewOperationFunc.clickonButtonText(solo, "确定");
			}
			solo.sleep(1000);
			
			//增加一个判断的循环
			if (solo.searchText(".*?分组名称重复.*?")) {
				System.out.println("分组名称重复有提示");
				solo.goBack();
				ViewOperationFunc.clickonButtonText(solo, "取消");
			}else{
				// 点击全部关注
				ViewOperationFunc.clickById(solo, "tab_a");
				// 编辑分组
				ViewOperationFunc.clickById(solo, "edit_tv");
				ViewOperationFunc.clickonText(solo, "创建分组");
				solo.goBack();
				if (solo.searchText(".*?是否保存分组修改.*?")) {
					ViewOperationFunc.clickonButtonText(solo, "确定");
					if (solo.searchText(".*?分组名称重复.*?")) {
						System.out.println("分组名称重复有提示");
						solo.goBack();
						ViewOperationFunc.clickonButtonText(solo, "取消");
					}
				}
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//判断分组名称为空是否有提示
	public void test06_GroupNameIsNull() {
		try {
			// 点击全部关注
			ViewOperationFunc.clickById(solo, "tab_a");
			// 编辑分组
			ViewOperationFunc.clickById(solo, "edit_tv");
			
			ViewOperationFunc.clearEditText(solo, 0);
			// 点击完成
			solo.goBack();
			if (solo.searchText(".*?是否保存分组修改.*?")) {
				ViewOperationFunc.clickonButtonText(solo, "确定");
			}
			solo.sleep(1000);
			if (solo.searchText(".*?分组名称不能为空.*?")) {
				System.out.println("分组名称为空是有提示的");
			}

			solo.goBack();
			if (solo.searchText(".*?是否保存分组修改.*?")) {
				ViewOperationFunc.clickonButtonText(solo, "取消");
				solo.sleep(1000);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String DataGroup(int index) {
		String[] datagroup = { "新闻测试1", "新闻测试2", "新闻测试3", "新闻测试4", "新闻测试5",
				"新闻测试6", "新闻测试7", "新闻测试8", "新闻测试9", "新闻测试10" };
		return datagroup[index];
	}

	public static int Random() {
		int a = (int) (Math.random() * 10);
		return a;
	}

	// 遍历页面元素
	public static View getViewFromImageByIndex(Solo solo, Class<View> viewType,
			int index) {
		ArrayList<View> ImageViews = solo.getCurrentViews();
		ArrayList<View> typeViews = new ArrayList<View>();
		for (View currentTypeView : ImageViews) {
			if (viewType.isInstance(currentTypeView)) {
				typeViews.add(currentTypeView);
			}
		}
		if (ImageViews.size() > 0) {
			return (View) typeViews.get(index);
		} else {
			return null;
		}
	}
}
