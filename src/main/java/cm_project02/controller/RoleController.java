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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cm_project02.config.MySqlConfig;
import cm_project02.entity.Role;
import cm_project02.service.RoleService;

@WebServlet(name = "roleController", urlPatterns = { "/role-add", "/role" ,"/role-update"}) // servletPath
public class RoleController extends HttpServlet {
	private RoleService roleService = new RoleService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy ra url mà người dùng đang gọi
		String path = req.getServletPath();
		// kiểm tra URL người dùng gọi là cái nào để hiển thị giao diện tương ứng
		if (path.equals("/role-add")) {
			req.getRequestDispatcher("/role-add.jsp").forward(req, resp);
		} 
		else if (path.equals("/role")) {
			List<Role> list = new ArrayList<Role>();
			list = roleService.getAllRole();
			

////			Tạo listRole rỗng để lưu dữ liệu vào Role enity đại diện trả ra jsp
//			List<Role> listRole = new ArrayList<Role>();
//			
////			Chuẩn bị câu query ở Servlet
//			String query = "SELECT * FROM Role";
//			
////			mở cổng kết nối tới csdl
//			Connection connection = MySqlConfig.getConnection();
//			
//			try {
////				Truyền câu query vào
//				PreparedStatement statement = connection.prepareStatement(query);
//
//				//trả resultset truy vấn câu query
//				ResultSet resultSet = statement.executeQuery();//Resultset là đại diện cho danh sách kết quả trả ra
//				
//				while(resultSet.next()) {
//					Role role = new Role();//Role.entity  đối tượng là th cho phép lưu đc nhiều giá trị(bắt buộc phải tạo)
//					role.setId(resultSet.getInt("id"));// lấy giá trì "id" (trong CSDL) gán vào id trong Role entity
//					role.setName(resultSet.getString("name"));
//					role.setDescription(resultSet.getString("description"));
//					
//					listRole.add(role);
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				System.out.println("Lỗi không get list"+e.getLocalizedMessage());
//			}
			req.setAttribute("listRole", list);
			req.getRequestDispatcher("/role-table.jsp").forward(req, resp);
		}
		else if(path.equals("/role-update")) {
			int id = Integer.parseInt(req.getParameter("id"));
			List<Role> roleListById = roleService.getAllRoleById(id);
			req.setAttribute("id", id);
			req.setAttribute("roleListById", roleListById);
			req.getRequestDispatcher("/role-update.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/role-add")) {
			// Nhận tham số
			String roleName = req.getParameter("name");
			String roleDescription = req.getParameter("description");
			/// trả giao diện
			boolean isSucess = roleService.addRole(roleName, roleDescription);
			req.setAttribute("isSucess", isSucess);
			req.getRequestDispatcher("/role-add.jsp").forward(req, resp);

		}
		else if(path.equals("/role-update")){
			int id = Integer.parseInt(req.getParameter("id"));
			String roleName = req.getParameter("name");
			String roleDescription = req.getParameter("description");
			boolean isSuccess = roleService.updateRole(roleName, roleDescription, id);
			req.setAttribute("updateSucess", isSuccess);
			req.getRequestDispatcher("/role-update.jsp").forward(req, resp);
		}
		/*
		 * 
		 * //Chuẩn bị câu query String query =
		 * "INSERT INTO Role(name,description) VALUES(?,?)";
		 * 
		 * //Kết nối với cơ sở dữ liệu Connection connection =
		 * MySqlConfig.getConnection();
		 * 
		 * boolean isSucess = false; //Thêm câu query vào trong database try {
		 * PreparedStatement statement = connection.prepareStatement(query);
		 * 
		 * //truyền tham số vào câu query statement.setString(1, roleName);
		 * statement.setString(2, roleDescription);
		 * 
		 * // excuted câu query int count = statement.executeUpdate(); if(count > 0) {
		 * isSucess = true; }
		 * 
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * System.out.println("Không thêm đc câu query"+e.getLocalizedMessage());
		 * }finally { try { if(connection!=null) { connection.close(); } } catch
		 * (SQLException e) { // TODO Auto-generated catch block
		 * System.out.println("Lỗi không đóng được cổng kết nối"+e.getLocalizedMessage()
		 * ); } }
		 */
	}

}
