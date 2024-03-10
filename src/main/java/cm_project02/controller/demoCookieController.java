package cm_project02.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "demoCookieController", urlPatterns = { "/demoCookie" })
public class demoCookieController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy giá trị đã lưu trữ trong cookies
		Cookie[] arrayCookie = req.getCookies();
		for (Cookie cookie : arrayCookie) {
			if (cookie.getName().equals("hoten")) {
				String value = cookie.getValue();
				System.out.println(URLDecoder.decode(value, "UTF-8"));
			}
		}
	}
}
