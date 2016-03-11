package net.sherwin.javaweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChinesePageServlet extends HttpServlet {

	protected  void doGet(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		
		String salutatory = "welcome to servlet's world, " + username;
		
		PrintWriter pw;
		try {
			/*
			 * 如果返回内容有中文，则要设置编码类型，试试把返回内容改为“你好，world”试试
			 * 一定要在调用resp.getWriter前调用resp.setContentType
			 * 否则得到的writer仍然是默认编码
			 */
//			resp.setContentType("text/html;charset=gbk");
			
			pw = resp.getWriter();
			
			pw.write("<html>");
			pw.write("	<head>");
			pw.write("		<title></title>");
			pw.write("	</head>");
			pw.write("	<body>");
			pw.write(salutatory);
			pw.write("	</body>");
			pw.write("</html>");
			
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
