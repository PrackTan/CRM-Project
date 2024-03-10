package cm_project02.controller;

import java.io.IOException;
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

import com.mysql.cj.Session;

import cm_project02.config.MySqlConfig;
import cm_project02.entity.Role;
import cm_project02.entity.Users;
import cm_project02.service.UserService;

@WebServlet(name = "userController", urlPatterns = { "/user-add", "/users", "/user-update" })
public class UserController extends HttpServlet {
	// khởi tạo userService
	private UserService userService = new UserService();
	// khởi tạo servletPath

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/users":
			List<Users> listUserRole = new ArrayList<Users>();
			listUserRole = userService.getAllUsers();
			req.setAttribute("listUserRole", listUserRole);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
			break;

		case "/user-add":
//			tạo list role rỗng
			List<Role> listRole = new ArrayList<Role>();
//			gán giá trị getAllrole list role
			listRole = userService.getAllRole();
//			tham chiếu quá listrole
			req.setAttribute("listRole", listRole);
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			break;

		case "/user-update":
			listRole = userService.getAllRole();
			int id = Integer.parseInt(req.getParameter("id"));
			System.out.println(id);
			List<Users> listUserById = userService.getAllUsersById(id);
			req.setAttribute("id", id);
			req.setAttribute("listUserById", listUserById);
			req.setAttribute("listRole", listRole);
			req.getRequestDispatcher("user-update.jsp").forward(req, resp);
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/user-update")) {
			int id = Integer.parseInt(req.getParameter("id")); 
			String fullname = req.getParameter("fullname");
			String password = req.getParameter("password");
			String phone = req.getParameter("phone");
			String email = req.getParameter("email");
			int idRole = Integer.parseInt(req.getParameter("role"));
			boolean isSucess = userService.updateUser(fullname, email, password, phone, idRole, id);
			req.setAttribute("updateSuccess", isSucess);
			req.getRequestDispatcher("user-update.jsp").forward(req, resp);

		}

		if (path.equals("/user-add")) {
			// Bước 1: lấy giá trị người dùng nhập vào ô và click button submit
			String fullname = req.getParameter("fullname");
			String password = req.getParameter("password");
			String phone = req.getParameter("phone");
			String email = req.getParameter("email");
			int idRole = Integer.parseInt(req.getParameter("role"));
			boolean isSucess = userService.insertUser(fullname, email, password, phone, idRole);
			req.setAttribute("isSucess", isSucess);
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		}

//		int id = Integer.parseInt(req.getParameter("id"));
		// Gán hàm insert vào isSucess trả true false
//		boolean isSucess1 = userService.updateUser(fullname, email, email, phone, idRole, id);
		// //tạo listRole trống từ list entity
//		List<Role> listRole = new ArrayList<Role>();
//		// gán usergetAllrole cho list
//		listRole = userService.getAllRole();
//		req.setAttribute("listRole",listRole);
//		req.setAttribute("updateSuccess", isSucess1);

//		req.getRequestDispatcher("user-update.jsp").forward(req, resp);

//		bước 2: tạo câu truy vấn và truyền giá trị truy vấn vào 		
//		String query = "INSERT INTO Users(fullName,email,pwd,phone,id_role) \n"
//				+ "VALUES(?,?,?,?,?)";
////		bước 3: mở kết nối cơ sở dữ liệu
//		// mở kết nối
//		Connection connection = MySqlConfig.getConnection();
//		
//		//truyền câu query vào database đã đc kết nối
//		try {
//			PreparedStatement statement = connection.prepareStatement(query);
//			//Gán giá trị của 4 dấu chấm ?
//			statement.setString(1, fullname);
//			statement.setString(2, email);
//			statement.setString(3, password);
//			statement.setString(4, phone);
//			statement.setInt(5, idRole);
//			
//			
//			// 2 loại excute : 
////			1 excuteupdate: Nếu query truy vấn khác select vd insert update delete ...
////			2 excutequery: nêu câu query là select
//			int count  = statement.executeUpdate();
//			//kiểm tra
//			if(count > 0) {
//				//insert dữ liệu thành công
//				System.out.println("thêm thành công");
//			}
//			else {
//				//insert dữ liệu thất bại
//				System.out.println("thêm thất bại");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("lỗi thêm dữ liệu user: "+e.getLocalizedMessage());
//		}finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				System.out.println("lỗi đóng kết nối"+e.getLocalizedMessage());
//			}
//		}
//		
//		List<Role> list = new ArrayList<Role>();
//		try {
//			 list = getAllRole();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Lỗi list"+e.getLocalizedMessage());
//		}
//		req.setAttribute("listRole",list);
//		
//		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
//	private List<Role> getAllRole() throws SQLException {
//		//chuẩn bị câu query
//		String query =  "SELECT * FROM Role";
//		
//		// mở kết nối CSDL
//		Connection connection = MySqlConfig.getConnection();
//		
//		// truyền câu query vào connection
//		PreparedStatement statement = connection.prepareStatement(query);
//		
//		// thực thi câu truy vấn và đc 1 danh sách dữ liệu
//		ResultSet resultSet = statement.executeQuery();
//		
//		// tạo list lấy tham số từ CSDL gán vào trong role
//		List<Role> listRole = new ArrayList<Role>() ;
//		while(resultSet.next()) {
//			Role role = new Role();
//			role.setId(resultSet.getInt("id"));//tên tham số trong SQL
//			role.setName(resultSet.getString("name"));
//			role.setDescription(resultSet.getString("description"));
//			
//			listRole.add(role);
//			
//			
//		}
//		return listRole;
//	}
}
