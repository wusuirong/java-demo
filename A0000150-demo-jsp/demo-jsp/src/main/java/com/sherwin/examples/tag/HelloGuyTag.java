package com.sherwin.examples.tag;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class HelloGuyTag extends TagSupport {

	public int doEndTag() throws JspException {
		Writer out = pageContext.getOut();
		try {
			out.write("Hello, " + name + ".");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Tag.EVAL_PAGE;
	}
	
	private String name;

	public void setName(String name) {
		this.name = name;
	}
}
