package org.danny.demo.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Xml中不要嵌套Xml {
	public static void main(String[] args) {
		System.out.println("xml中嵌套子xml不是好实践_子xml中有元字符会无法解析");
		try {
			xml中嵌套子xml不是好实践_子xml中有元字符会无法解析();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static void xml中嵌套子xml不是好实践_子xml中有元字符会无法解析() throws Exception {
		//attachRef有xml元字符，解析失败
		String xml = "<sheetDetail>" +
		"<recordInfo>" +
				"<fieldInfo>" +
					"<fieldEnName>附件</fieldEnName>" +
					"<fieldContent>" +
						"<![CDATA[<attachRef>" +
									"<attachInfo>" +
										"<attachName>aa&a;</attachName>" +
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
