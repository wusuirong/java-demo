package com.sherwin.others;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * 遍历目录中的cvs文件，修改cvs服务器的ip地址
 * @author Administrator
 *
 */
public class 遍历目录的cvs文件并修改内容 {
	static String oldIp = "135.251.14.34";
	static String newIp = "135.251.223.174";

	static int count = 0;
	
	public static void main(String[] args) throws Exception {
		String root = "E:\\dba88\\workspace_irm_branch";//head 3156 branch 2771
		doSth(new File(root));
		System.out.println(count);
	}
	
	public static void doSth(File file) throws Exception {
		if (null != file) {
			if (file.isFile()
					&& matchFileName(file)) {
				replaceContent(file, oldIp, newIp);
			} else {
				File[] files = file.listFiles();
				if (null != files) {
					for (int i=0; i<files.length; i++) {
						if (files[i].isFile()
								&& matchFileName(files[i])) {
							replaceContent(files[i], oldIp, newIp);
						} else {
							doSth(files[i]);
						}
					}
				}				
			}
		}
	}
	
	public static boolean matchFileName(File f) {
		if ("Root".equals(f.getName())) {
			System.out.println(f.getAbsolutePath());
			return true;
		} else {
			return false;
		}
	}
	
	public static void replaceContent(File f, String origStr, String newStr) throws Exception {
		BufferedReader br = null;
		BufferedWriter bw = null;
		List<String> bufs = new ArrayList<String>();
		
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			String s = null;
			while (null != (s = br.readLine())) {
				if (s.contains(origStr)) {
					s = s.replace(origStr, newStr);
				}
				bufs.add(s);
			}			
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
			for (String s : bufs) {
				bw.write(s);
			}
		} finally {
			if (null != bw) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		count++;
	}
}
