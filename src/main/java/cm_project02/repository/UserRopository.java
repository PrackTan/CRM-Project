package cm_project02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;

import cm_project02.config.MySqlConfig;
import cm_project02.entity.Role;
import cm_project02.entity.Users;

public class UserRopository {
	/// int là vì giá trị của excuteupdate trả ra int
	public int insert(String fullname, String email, String pwd, String phone, int idRole) {
		int count = 0;
		// bước 2: tạo câu truy vấn và truyền giá trị truy vấn vào
		String query = "INSERT INTO Users(fullName,email,pwd,phone,id_role) \n" + "VALUES(?,?,?,?,?)";

//		bước 3: mở kết nối cơ sở dữ liệu
		// mở kết nối
		Connection connection = MySqlConfig.getConnection();

		// truyền câu query vào database đã đc kết nối
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			// Gán giá trị của 4 dấu chấm ?
			statement.setString(1, fullname);
			statement.setString(2, email);
			statement.setString(3, pwd);
			statement.setString(4, phone);
			statement.setInt(5, idRole);

			// 2 loại excute :
//			1 excuteupdate: Nếu query truy vấn khác select vd insert update delete ...
//			2 excutequery: nêu câu query là select
			count = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("lỗi thêm dữ liệu user: " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("lỗi đóng kết nối" + e.getLocalizedMessage());
			}
		}
		return count;
	}

	/// do SQL trả ra 1 list nên dùng List
	public List<Users> getAllUsers() {
		// tạo list user để lưu dữ liệu
		List<Users> listUserRole = new ArrayList<Users>();
		// truyền câu query vào servlet
		String query = "SELECT u.id , u.firstName , u.lastName , u.userName, r.name FROM Users u JOIN Role r ON u.id_role  = r.id ";
		// tạo kết nối
		Connection connection = MySqlConfig.getConnection();
		// excute query
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			// Trả ra resultSet
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
//				tạo users
				Users users = new Users();
				users.setId(resultSet.getInt("id"));// lấy dữ liệu của cột id trong database
				users.setFirstname(resultSet.getString("firstName"));// lấy dữ liệu của cột firstname trong database
				users.setLastname(resultSet.getString("lastName")); // tham chiếu trong database
				users.setUsername(resultSet.getString("userName"));

				// khởi tạo role và gán giá trị Name cho đối tượng role
				Role role = new Role();
				role.setName(resultSet.getString("name"));
				// truyền role vào đối tượng Role tạo ở users
				users.setRole(role);

				listUserRole.add(users);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("lỗi statement " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Lỗi đóng connection " + e.getLocalizedMessage());
			}
		}
		return listUserRole;
	}

	public List<Users> getAllUsersbyId(int id) {
		List<Users> listUserRole = new ArrayList<Users>();
		String query = "SELECT u.fullName , u.email , u.pwd ,u.phone, r.id FROM Users u JOIN Role r ON u.id_role = r.id WHERE u.id = ?";
		Connection connection = MySqlConfig.getConnection();

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Users users = new Users();
				users.setFullname(resultSet.getString("fullname"));
				users.setEmail(resultSet.getString("email"));
				users.setPassword(resultSet.getString("pwd"));
				users.setPhone(resultSet.getString("phone"));

				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				users.setRole(role);

				listUserRole.add(users);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("lỗi thực thi " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("lỗi đóng kết nối " + e.getLocalizedMessage());
			}

		}

		return listUserRole;
	}

	public int deleteById(int id) {
		int count = 0;
//		truyền câu query vào servlet
		String query = "DELETE FROM Users u WHERE u.id = ?";
//		mở kết nối
		Connection connection = MySqlConfig.getConnection();
//		Thực thi câu query
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			// truyền tham số vào
			statement.setInt(1, id);
			// trả ra dữ liệu count > 0
			count = statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi statement" + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// nếu trả 0 thì không có giá trị nếu có lớn >0 là đúng
		return count;
	}

	public int update(String fullname, String email, String pwd, String phone, int idRole, int id) {
		int count = 0;
		String query = "UPDATE Users u SET u.email = ?, u.pwd  = ?, u.fullName = ?, u.phone = ?, u.id_role = ?  WHERE u.id = ?";

		Connection connection = MySqlConfig.getConnection();

		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, email);
			statement.setString(2, pwd);
			statement.setString(3, fullname);
			statement.setString(4, phone);
			statement.setInt(5, idRole);
			statement.setInt(6, id);
			count = statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi update dữ liệu  " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		return count;
	}
}
