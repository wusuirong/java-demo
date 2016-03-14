package com.sherwin.others;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpResourceDownloader {
	/**
	 * 将附件保存到文件中
	 * 
	 * @param destUrl
	 *            附件地址
	 * @param file
	 *            保存文件
	 * @throws Exception
	 *             异常
	 */
	public static void getAttachment(String destUrl, File file) throws Exception {
		BufferedInputStream bis = null;
		GetMethod getMethod = null;   
        BufferedOutputStream bos = null;   
        try {   
            URI uri = new URI(destUrl,false, "GBK");
            HttpClient hc = new HttpClient();
            getMethod = new GetMethod(uri.toString());   
            int status = hc.executeMethod(getMethod);   
            if (status == 200) {   
                bis = new BufferedInputStream(getMethod.getResponseBodyAsStream());   
                bos = new BufferedOutputStream(new FileOutputStream(file));   
  
                byte[] buffer = new byte[1024];   
                int len = 0;   
                while ((len = bis.read(buffer)) != -1) {   
                    bos.write(buffer, 0, len);   
                }   
            }   
        } finally {   
            if(getMethod != null){   
                getMethod.releaseConnection();   
            }   
            if(bis != null){   
                bis.close();   
            }   
            if(bos != null){   
                bos.close();   
            }   
        }
	}
	
	public static  byte[] getAttachment(String destUrl) throws Exception {
		
		BufferedInputStream bis = null;
		GetMethod getMethod = null;   
		ByteArrayOutputStream baos = null;
        try {   
            URI uri = new URI(destUrl,false, "GBK");
            HttpClient hc = new HttpClient();
            hc.setConnectionTimeout(3000);
            hc.setTimeout(3000);
            getMethod = new GetMethod(uri.toString());   
            int status = hc.executeMethod(getMethod);   
            if (status == 200) {   
                bis = new BufferedInputStream(getMethod.getResponseBodyAsStream());   
                baos = new ByteArrayOutputStream();
  
                byte[] buffer = new byte[1024];   
                int len = 0;   
                while ((len = bis.read(buffer)) != -1) {   
                	baos.write(buffer, 0, len);
                }   
            }   
        } finally {   
            if(getMethod != null){   
                getMethod.releaseConnection();   
            }   
            if(bis != null){   
                bis.close();   
            }   
        }
        return baos.toByteArray();
	}
	
	public static void main(String[] args) throws Exception {
		File file = null;
		String url = null;
		
		file = new File("test.xls");
		url = "http://10.212.41.130:10000/中文文件.xls";
		System.out.println(url);
		try {
			HttpResourceDownloader.getAttachment(url, file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		file = new File("prod.xls");
		url = "http://zjesop.yw.zj.chinamobile.com/business/com.ai.frame.attach.AttachAction?action=doDownload&FILE_NAME=综合接入施工单：雷甸中学20100901(68416).xls&BUSI_TYPE=bboss.mgr.soap";
		System.out.println(url);
		try {
			HttpResourceDownloader.getAttachment(url, file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
