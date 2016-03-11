package net.sherwin.javaweb.sessiondemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionLoginByCookieServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		//登录检查
		if ("login".equals(action)) {
			String name = req.getParameter("user");
			String pwd = req.getParameter("password");

			if (null != name && null != pwd
					&& !"".equals(name) && !"".equals(pwd)) {
				StringBuffer sb = new StringBuffer();
				sb.append("username=");
				sb.append(name);
				sb.append("&password=");
				sb.append(pwd);
				Cookie cookie = new Cookie("userinfo", sb.toString());
				cookie.setMaxAge(30);//正数表示cookie存活时间，0表示删除cookie，负数表示cookie不保存在客户机硬盘
				resp.addCookie(cookie);
				resp.sendRedirect("sessionLoginByCookieServlet?action=greet");
				return;
			} else {
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter out = resp.getWriter();
				out.println("用户名或密码错误，请<a href=sessionLoginByCookieServlet>重新登录</a>");
				return;
			}

		}
		//欢迎页面
		else if ("greet".equals(action)) {
			Cookie[] cookies = req.getCookies();
			if (null != cookies && cookies.length != 0) {
				String name = null;
				String pwd = null;
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					String cName = cookie.getName();

					if (cName.equals("userinfo")) {
						String cValue = cookie.getValue();
						String[] userInfo = cValue.split("&");
						for (int j = 0; j < userInfo.length; j++) {
							String[] value = userInfo[j].split("=");
							if (value[0].equals("username")) {
								name = value[1];
							}
							if (value[0].equals("password")) {
								pwd = value[1];
							}
						}
					}
				}
				if (null != name && null != pwd
						&& !"".equals(name) && !"".equals(pwd)) {
					resp.setContentType("text/html;charset=utf-8");
					PrintWriter out = resp.getWriter();
					out.println("<html>");
					out.println("<meta http-equiv=\"Pragma\" content=\"no-cache\">");
					out.println("<head><title>欢迎页面</title></head>");
					out.println("<body>");
					out.println(name + ",欢迎你");
					out.println("<a href=sessionLoginByCookieServlet>重新登录</a>");
					out.println("</body></html>");
					out.close();
					return;
				}
			}

			RequestDispatcher rd = req.getRequestDispatcher("sessionLoginByCookieServlet");
			rd.forward(req, resp);
		}
		//登录首页
		else {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<meta http-equiv=\"Pragma\" content=\"no-cache\">");
			out.println("<head><title>登录页面</title></head>");
			out.println("<body>");
			out.println("<p>");
			out.println("<form method=post action=sessionLoginByCookieServlet?action=login>");

			out.println("<table>");

			out.println("<tr>");
			out.println("<td>请输入用户名</td>");
			out.println("<td><input type=text name=user></td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>请输入密码</td>");
			out.println("<td><input type=password name=password></td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td><input type=reset value=重填></td>");
			out.println("<td><input type=submit value=登录></td>");
			out.println("</tr>");

			out.println("</table></form></body></html>");
			out.close();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
