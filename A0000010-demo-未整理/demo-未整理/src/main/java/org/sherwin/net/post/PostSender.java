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
	 * ����ϢPOST���Է�url
	 * 
	 * @param destURL
	 *            Ŀ�ĵ�ַ
	 * @param paramStr
	 *            ���ݵĲ���
	 */
	private int businessMTtoWapdm(String destURL, String paramStr) {
		int Result = 0;
		HttpURLConnection httpConn = null;
		PrintWriter out = null;
		BufferedReader reader = null;
		try {
			// ��Է�����һ������
			URL url = new URL(destURL);
			// ������
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestMethod("POST");
			String userName = "";
			String passWord = "";
			String userPassword = userName + ":" + passWord;
			userPassword = new BASE64Encoder().encode(userPassword.getBytes());
			// ���������֤
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
