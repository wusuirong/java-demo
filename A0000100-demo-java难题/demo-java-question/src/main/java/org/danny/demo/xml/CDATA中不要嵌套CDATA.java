package org.danny.demo.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class CDATA中不要嵌套CDATA {
	public static void main(String[] args) {		
		System.out.println("xml中CDATA里不能嵌套CDATA");
		try {
			xml中CDATA里不能嵌套CDATA();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static void xml中CDATA里不能嵌套CDATA() throws Exception {
		//尝试再用CDATA封装attachRef中的xml元字符，解析失败
		String xml = "<sheetDetail>" +
						"<recordInfo>" +
							"<fieldInfo>" +
								"<fieldEnName>附件</fieldEnName>" +
								"<fieldContent>" +
									"<![CDATA[<attachRef>" +
												"<attachInfo>" +
													"<attachName><![CDATA[aa&a;]]></attachName>" +
													"<attachURL>地址</attachURL>" +
													"<attachLength>1</attachLength>" +
												"</attachInfo>" +
											"</attachRef>]]>" +
								"</fieldContent>" +
							"</fieldInfo>" +
						"</recordInfo>" +
					"</sheetDetail>";
		
		String attachRefXml = null;
		{
			Document doc = null;
			doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			Element recordInfo = root.element("recordInfo");
			Element fieldInfo = recordInfo.element("fieldInfo");
			Element fieldContent = fieldInfo.element("fieldContent");
			attachRefXml = fieldContent.getStringValue();
			System.out.println(attachRefXml);
		}
		
		{
			Document doc = null;
			doc = DocumentHelper.parseText(attachRefXml);
			System.out.println("解析attachRef成功");
		}
	}
}
