package org.danny.demo.dubbox;


import org.apache.log4j.Logger;

/**
 *             
 * @author wusuirong
 *
 * @Description 
 * <li></li>
 *
 * @Email wusuirong@xxx.com
 *
 * www.xxx.com
 * Copyright (c) 2014 All Rights Reserved.
 */
public class DemoServiceImpl implements DemoService {

	private static final Logger logger = Logger.getLogger(DemoServiceImpl.class);

	/* (non-Javadoc)
	 * @see org.danny.demo.dubbox.DemoService#doService(java.lang.String)
	 */
	@Override
	public String doService(String param) {
		String value = "你输入的是" + param;
		System.out.println(value);
		return value;
	}
}
