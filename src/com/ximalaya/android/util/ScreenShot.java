package com.ximalaya.android.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;

public class ScreenShot {

	private static long pushTime = 1000 * 60 * 60 * 8;

	//获取指定activity的图片,保存png图片
	public static Bitmap takeScreenShot(Activity activity) {
		System.out.println("开始进入截屏函数----->");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("等待一秒钟----->");
		// 获取view
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap b1 = view.getDrawingCache();
		System.out.println("获取view成功----->");
		// 获取状态栏高度
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		System.out.print(statusBarHeight);
		System.out.println("获取状态栏高度成功----->");
		// 获取屏幕长度和高
		int width = activity.getWindowManager().getDefaultDisplay().getWidth();
		int height = activity.getWindowManager().getDefaultDisplay()
				.getHeight();
		System.out.println("获取长度和高成功----->");
		// 去掉标题栏
		Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
				- statusBarHeight);
		System.out.println("去掉标题栏成功----->");
		view.destroyDrawingCache();
		System.out.println("截图函数运行成功----->");
		return b;
	}

	//保存文件到SD卡
	public static String savePicture(Bitmap b, String strFileName) {
		FileOutputStream fos = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date curDate = new Date(System.currentTimeMillis() + pushTime);
		// 获取当前时间
		String time = formatter.format(curDate);
		System.out.println(time);

		File f = new File("sdcard/Robotium/" + strFileName);
		if (f.exists() == false) {
			f.mkdirs();
		}
		try {
			fos = new FileOutputStream("sdcard/Robotium/" + strFileName + time
					+ ".png");
			if (null != fos) {
				b.compress(Bitmap.CompressFormat.PNG, 90, fos);
				fos.flush();
				fos.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strFileName;

	}

}
