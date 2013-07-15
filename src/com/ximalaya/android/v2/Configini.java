package com.ximalaya.android.v2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import android.os.Environment;

public class Configini {
	protected HashMap<String, Properties> sections = new HashMap<String, Properties>();

	public Configini() {

	}

	final public static Map<String, String> readConfig() {
		BufferedReader input = null;
		Map<String, String> map = new HashMap<String, String>();
		String text = null;
		FileInputStream file = null;
		String envPath = Environment.getExternalStorageDirectory().getPath();

		String filepath = envPath.concat("/config.ini");
		try {
			file = new FileInputStream(filepath);
			input = new BufferedReader(new InputStreamReader(file, "UTF-8"));
			while ((text = input.readLine()) != null) {
				if (text.length() >= 1 && text.substring(0, 1).equals("#")) {
					continue;
				}
				int number = text.indexOf("=");
				if (number != -1) {
					map.put(text.substring(0, number),
							text.substring(number + 1, text.length()));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return map;
	}

}
