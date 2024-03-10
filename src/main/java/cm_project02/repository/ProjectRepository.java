package cm_project02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cm_project02.config.MySqlConfig;
import cm_project02.entity.Project;
import cm_project02.entity.Role;

public class ProjectRepository {
	/// INT vì excuteupdate trả ra int
	public int insert(String name, String startDate, String endDate) {
		int count = 0;
		// truyền query vào servletam
		String query = "INSERT INTO Project (name ,startDate ,endDate) VALUES (?,?,?)";
		// mở cổng kết nối
		Connection connection = MySqlConfig.getConnection();

		try {
			// thực thi query trong sql bằng câu prepare
			PreparedStatement statement = connection.prepareStatement(query);
			// gán giá trị của dấu ?
			statement.setString(1, name);
			statement.setString(2, startDate);
			statement.setString(3, endDate);
//			excuteupdate trả ra int
			count = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi query " + e.getLocalizedMessage());

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Lỗi đóng kết nối" + e.getLocalizedMessage());
			}
		}
		// trả ra count int (count > 0) thì trả ra đúng
		return count;

	}
	
	public int delete(int id) {
		String query = "DELETE FROM  Project p  WHERE p.id = ?";
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
	public int update(String name, String startDate, String endDate , int id) {
		String query = "UPDATE Project p SET  p.name = ?, p.startDate = ?, p.endDate = ? WHERE p.id = ?";
		int count = 0;
		Connection connection = MySqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2,startDate);
			statement.setString(3, endDate);
			statement.setInt(4, id);
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
	
	public List<Project> getAllProjectById(int id){
		List<Project> listProject = new ArrayList<Project>();
		String query = "SELECT * FROM Project p WHERE id = ?";
		Connection connection = MySqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Project project = new Project();
				project.setId(resultSet.getInt("id"));// tên tham số trong SQL
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getString("startDate"));
				project.setEndDate(resultSet.getString("endDate"));
				listProject.add(project);
				
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
				return listProject;
	}
	
	
	// điều kiện đặt tên
	/// đối với câu select tên hàm sẽ bắt đầu bằng chữ: find
	/// nếu có điều kiện Where bằng: by
	public List<Project> findAll() {
		// tạo listRole rỗng để lưu dữ liệu vào Role entity đại diện trả ra jsp
		List<Project> listProject = new ArrayList<Project>();
		// chuẩn bị câu query
		String query = "SELECT * FROM Project p";

		// mở kết nối CSDL
		Connection connection = MySqlConfig.getConnection();
		try {
			// truyền câu query vào connection
			PreparedStatement statement = connection.prepareStatement(query);
			// thực thi câu truy vấn và đc 1 danh sách dữ liệu
			ResultSet resultSet = statement.executeQuery();
			// tạo list lấy tham số từ CSDL gán vào trong role

			while (resultSet.next()) {
				Project project = new Project();
				project.setId(resultSet.getInt("id"));// tên tham số trong SQL
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getString("startDate"));
				project.setEndDate(resultSet.getString("endDate"));
				listProject.add(project);
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

		return listProject;
	}
}
