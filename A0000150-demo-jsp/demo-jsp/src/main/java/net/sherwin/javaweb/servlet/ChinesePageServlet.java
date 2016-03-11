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
			 * ����������������ģ���Ҫ���ñ������ͣ����԰ѷ������ݸ�Ϊ����ã�world������
			 * һ��Ҫ�ڵ���resp.getWriterǰ����resp.setContentType
			 * ����õ���writer��Ȼ��Ĭ�ϱ���
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
