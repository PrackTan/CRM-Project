package cm_project02.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cm_project02.config.MySqlConfig;
import cm_project02.entity.Users;

//Package Controller:
// là nơi chứa toàn bộ file liên quan tới khai báo đường dẫn 
//và xử lý đường dẫn
//cookie là nơi lưu trữ đơn giản là nơi lưu trữ trên máy tính khách hàng
//tính năng login:
//bước 1 : lấy dữ liệu người dùng nhập vào ô usernam và password
//bước 2 :viết 1 câu query kiểm tra usernam và password người dùng có tồn tại trong database hay không
//bước 3: dùng jdbc kết nối tới csdl và truyền câu query ở bước 2 cho csdl thực thi
//bước 4: kiểm tra dữ liệu. nếu có dữ liệu thì đăng nhập thành công và ngược lại thì đăng nhập thất bại

@WebServlet(name = "loginController", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/// tạo cookie lưu trữ
		Cookie cookie = new Cookie("hoten", URLEncoder.encode("Nguyễn Văn A", "UTF-8"));
		int maxAge = 8 * 60 * 60;
		cookie.setMaxAge(maxAge);
		// yêu cầu client tạo cookie
		resp.addCookie(cookie);
		req.getRequestDispatcher("login.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// bước 1 : lấy dữ liệu người dùng nhập vào ô usernam và password
		String username = req.getParameter("email");
		String password = req.getParameter("password");

		// bước 2 :viết 1 câu query kiểm tra usernam và password người dùng có tồn tại
		// trong database hay không
		// ? sẽ là tham số đc truyền vào jdbc
		String query = "SELECT * FROM Users u WHERE u.email = ? AND u.password = ?";

		// bước 3: dùng jdbc kết nối tới csdl và truyền câu query ở bước 2 cho csdl thực
		// thi

		// Mở kết nối
		Connection connection = MySqlConfig.getConnection();
		try {
			// chuẩn bị câu query truyền xuống csdl qua prepareStatement
			PreparedStatement statement = connection.prepareStatement(query);
			// gán giá trị cho tham số trong câu query (?)
			statement.setString(1, username);
			statement.setString(2, password);

			// thực thi câu query và lấy kết quả

			// 2 loại excute :
//			1 excuteupdate: Nếu query truy vấn khác select vd insert update delete ...
//			2 excutequery: nêu câu query là select

			ResultSet resultSet = statement.executeQuery();
			List<Users> listUsers = new ArrayList<Users>();
			// khi nào resultSet còn tiếp theo thì l
			while (resultSet.next()) {
//				duyệt qua từng dòng query đc trong database
				Users users = new Users();
//				lấy dữ liệu từ cột duyệt đc và lưu vào thuộc tính đối tượng
				users.setId(resultSet.getInt("id"));
				users.setEmail(resultSet.getString("email"));

				listUsers.add(users);
			}
			// nếu listusers có giá trị thì listusers tồn tại
			if (listUsers.size() > 0) {
				// user tồn tại đăng nhập thành công
				System.out.println("đăng nhập thành công");
			} else {
				// không tồn tại đăng nhập thất bại
				System.out.println("đăng nhập thất bại");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
