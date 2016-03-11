package org.sherwin.net.post;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import sun.misc.BASE64Encoder;

public class PostSender {
	/**
	 * 将消息POST给对方url
	 * 
	 * @param destURL
	 *            目的地址
	 * @param paramStr
	 *            传递的参数
	 */
	private int businessMTtoWapdm(String destURL, String paramStr) {
		int Result = 0;
		HttpURLConnection httpConn = null;
		PrintWriter out = null;
		BufferedReader reader = null;
		try {
			// 与对方建立一个连接
			URL url = new URL(destURL);
			// 打开连接
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestMethod("POST");
			String userName = "";
			String passWord = "";
			String userPassword = userName + ":" + passWord;
			userPassword = new BASE64Encoder().encode(userPassword.getBytes());
			// 设置身份验证
			httpConn.setRequestProperty("Authorization", "Basic " + userPassword);

			httpConn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			out = new PrintWriter(httpConn.getOutputStream());
			out.print(paramStr);
			out.flush();
			httpConn.connect();
			reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));

			StringBuffer buf = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				buf.append(line);
			}
			ByteArrayInputStream bis = new ByteArrayInputStream(buf.toString().getBytes());

			// ////////////////

		} catch (MalformedURLException ex) {

		} catch (IOException ex) {

		} finally {

		}
		return Result;
	}

}
