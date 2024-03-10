package cm_project02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cm_project02.config.MySqlConfig;
import cm_project02.entity.Role;

//roleRopository: chứa toàn bộ câu truy vấn liên quan tới bảng Role(SQL)
public class RoleRepository {

	/// hàm insert có 2 tham số nhận vào
	public int insert(String name, String description) {
		// Chuẩn bị câu query vào servlet
		String query = "INSERT INTO Role(name,description) VALUES(?,?)";
		// mở kết nối
		Connection connection = MySqlConfig.getConnection();
		int count = 0;
		try {
			// excuted query
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, description);
			// gán statement vào count
			count = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Lỗi thực hiện" + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("lỗi đóng kết nối"+e2.getLocalizedMessage());
			}
		}

		// trả về kết quả
		return count;
	}
	public int delete(int id) {
		String query = "DELETE FROM Role r WHERE r.id = ?";
		int count = 0;
		Connection connection = MySqlConfig.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi thực hiện" + e.getLocalizedMessage());
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("lỗi đóng kết nối"+e.getLocalizedMessage());
			}
		}
		return count;
	}
	public int update(String name, String description, int id) {
		String query = "UPDATE Role r SET  r.name = ?, r.description = ? WHERE r.id = ?";
		int count = 0;
		Connection connection = MySqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2,description);
			statement.setInt(3, id);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi thực hiện" + e.getLocalizedMessage());
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("lỗi đóng kết nối"+e.getLocalizedMessage());
			}
		}
		return count;
	}
	
	public List<Role> getAllRoleById(int id){
		List<Role> listRole = new ArrayList<Role>();
		String query = "SELECT * FROM Role WHERE id = ?";
		Connection connection = MySqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));// tên tham số trong SQL
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
				listRole.add(role);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("lỗi thực hiện"+e.getLocalizedMessage());
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Lỗi đóng kết nối"+e.getLocalizedMessage());
			}
		}
				return listRole;
	}
	
	
	// điều kiện đặt tên
	/// đối với câu select tên hàm sẽ bắt đầu bằng chữ: find
	/// nếu có điều kiện Where bằng: by
	public List<Role> findAll() {
		// tạo listRole rỗng để lưu dữ liệu vào Role entity đại diện trả ra jsp
		List<Role> listRole = new ArrayList<Role>();
		// chuẩn bị câu query
		String query = "SELECT * FROM Role";

		// mở kết nối CSDL
		Connection connection = MySqlConfig.getConnection();
		try {
			// truyền câu query vào connection
			PreparedStatement statement = connection.prepareStatement(query);
			// thực thi câu truy vấn và đc 1 danh sách dữ liệu
			ResultSet resultSet = statement.executeQuery();
			// tạo list lấy tham số từ CSDL gán vào trong role

			while (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));// tên tham số trong SQL
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));

				listRole.add(role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("đóng kết nối thất bại " + e.getLocalizedMessage());
			}
		}

		return listRole;
	}

}
