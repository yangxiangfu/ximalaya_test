package com.ximalaya.android.function;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jayway.android.robotium.solo.Solo;
import com.ximalaya.android.util.Report;
import com.ximalaya.android.util.ScreenShot;

/**
 * @author chrisandy
 * @createDate 2013/05/28
 * */
public class ScreenshotFunc {

	/**
	 * 封装截图函数
	 * 
	 * @param solo
	 * */
	public static String screenShot(Solo solo) {
		System.out.println("截图开始------>");
		return ScreenShot.savePicture(
				ScreenShot.takeScreenShot(solo.getCurrentActivity()),
				Report.getNowDateTime());
	}

	/**
	 * 用robotium自带的函数来封装
	 * */
	public static void screenshotSolo(Solo solo) {
		long plusTime = 1000 * 60 * 60 * 8;
		String strFileName = "";
		String filename = "";
		String interceptor = "&";

		System.out.println("截图开始------>");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date curDate = new Date(System.currentTimeMillis() + plusTime);

		// 获取当前时间
		String time = formatter.format(curDate);
		System.out.println(time);

		// 获取截图文件夹路径名
		strFileName = time;
		System.out.println("截图文件路径为：---->" + filename);

		// 用solo自带的截图方法来进行截图
		solo.takeScreenshot(strFileName);
		System.out.println("截图结束---->");
		strFileName = interceptor.concat(strFileName);
		Report.writeHTMLLog("弹出框截图", "出现弹出框，截图时间为:" + strFileName, Report.DONE,
				strFileName);
	}
}
