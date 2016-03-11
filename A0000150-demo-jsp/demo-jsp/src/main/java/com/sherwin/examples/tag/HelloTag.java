package com.sherwin.examples.tag;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class HelloTag implements Tag {
	
	private PageContext pageContext;
	private Tag parent;

	public int doEndTag() throws JspException {
		Writer out = pageContext.getOut();
		try {
			out.write("Hello, this is a tag.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Tag.EVAL_PAGE;
	}

	public int doStartTag() throws JspException {
		return Tag.SKIP_BODY;
	}

	public Tag getParent() {
		return parent;
	}

	public void release() {
		// TODO Auto-generated method stub
	}

	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	public void setParent(Tag parent) {
		this.parent = parent;
	}

}
