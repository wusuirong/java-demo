package net.sherwin.javaweb.sessiondemo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionLoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = (String) req.getParameter("username");
		String password = (String) req.getParameter("password");
		if (null == username || null == password || "".equals(username) || "".equals(password)) {
			resp.sendRedirect("login.jsp");
			return;
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("usernameInSession", username);
			resp.sendRedirect("welcome.jsp");
			return;
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
