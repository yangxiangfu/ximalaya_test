package com.ximalaya.android.util;

import java.util.HashMap;
import java.util.Map;

import com.ximalaya.ting.android.R;

/**
 * @author chrisandy
 * @createDate 2013/05/28
 * */

public class DB {
	public static Map<String, Integer> getID() {
		Map<String, Integer> IdMap = new HashMap<String, Integer>();

		// IdMap.put("menu_add", R.id.menu_add);
		IdMap.put("about_list", R.id.about_list);

		return IdMap;
	}

}
