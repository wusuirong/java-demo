package com.sherwin.examples.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * 测试有和没有缓存的差别
 */
public class BufferSpeed {
	
	public void copyWithoutBuffer(String fromfile, String tofile) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(fromfile);
			os = new FileOutputStream(tofile);
			
			int b = 0;
			while ( -1 != (b = is.read()) ) {
				os.write(b);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void copyWithBuffer(String fromfile, String tofile) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new BufferedInputStream(new FileInputStream(fromfile));
			os = new BufferedOutputStream(new FileOutputStream(tofile));
			
			int b = 0;
			while ( -1 != (b = is.read()) ) {
				os.write(b);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	static public void main(String[] args) {
		BufferSpeed copy = new BufferSpeed();
		
		long start = System.currentTimeMillis();
		copy.copyWithoutBuffer("e:/test.pdf", "e:/1.pdf");
		long end = System.currentTimeMillis();
		long period = end - start;
		System.out.println(period/1000);
		
		start = System.currentTimeMillis();
		copy.copyWithBuffer("e:/test.pdf", "e:/2.pdf");
		end = System.currentTimeMillis();
		period = end - start;
		System.out.println(period/1000);
	}
}
