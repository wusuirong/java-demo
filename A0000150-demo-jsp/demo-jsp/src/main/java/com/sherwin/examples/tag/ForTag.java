package com.sherwin.examples.tag;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ForTag extends BodyTagSupport {
	
    public int doStartTag() throws JspException {    	
    	current = 0;    	
        return EVAL_BODY_BUFFERED;
    }
	
	public int doAfterBody() throws JspException {		
		if ( current < count ) {
			current++;
			return this.EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}
	}

	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();//this.getBodyContent().getEnclosingWriter();
		
		try {
			out.println(this.getBodyContent().getString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.EVAL_PAGE;
	}
	
	private int count;
	
	private int current;

	public void setCount(int count) {
		this.count = count;
	}
}
