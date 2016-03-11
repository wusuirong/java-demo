package net.sherwin.javaweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardUsageServlet extends HttpServlet {

	protected  void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String username = req.getParameter("username");//获取表单的参数值

		if ("sherwin".equals(username)) {
			/*
			 * 第一种得到RequestDispatcher的方式
			 * 用ServletRequest生成的RequestDispatcher访问servlet比较方便
			 * 因为既能用web应用的绝对路径也可以使用相对路径
			 */
			
			//../security2/validatePasswd是web.xml里url映射路径
			RequestDispatcher rd = req.getRequestDispatcher("chinesePage");
			rd.forward(req, resp);
		} else if ("test".equals(username)) {
			/*
			 * 这里的测试说明了一些问题
			 * 浏览器发送请求后，浏览器和服务器通过socket建立了一个连接
			 * 这个socket的输入接request对象的inputstream和reader，输出接response的outputstream和writer
			 * 所以服务器把返回的html文本发送到response对象的writer里，然后close socket断开连接，浏览器就可以显示html了
			 * 为了保证返回的html的正确性，response对输出流封装了一些状态，如果用writer输出了一部分html，且刷新到浏览器了，
			 * 则web服务器不会允许后面使用forward或redirect方法，否则会导致返回的html页面是个错误的格式，
			 * 同理调用了forward就不能redirect
			 * 
			 * 只要把输出流关闭了，浏览器就能显示输出的页面，因为socket关闭后浏览器和服务器就没关系了，而服务器可以在socket关闭后继续一些业务处理
			 */
			PrintWriter pw = resp.getWriter();
			pw.write("<html>hello</html>");
			pw.flush();
			System.out.println("flush");

//			ServletContext context = this.getServletContext();
//			RequestDispatcher rd = context.getRequestDispatcher("/welcome.html");
//			rd.forward(req, resp);
			
//			resp.sendRedirect("welcome.html");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw.close();
			System.out.println("close");
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("finish");
		} else {
			/*
			 * 推荐这种得到RequestDispatcher的方式
			 * 用ServletContext生成的RequestDispatcher虽然不能用相对路径定位资源
			 * 但可以访问容器里别的web应用的资源，即跨应用访问
			 */
			ServletContext context = this.getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/welcome.html");
			rd.forward(req, resp);
		}
	}
}
