package cm_project02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cm_project02.config.MySqlConfig;
import cm_project02.entity.Job;
import cm_project02.entity.Project;
import cm_project02.entity.Role;
import cm_project02.entity.Status;
import cm_project02.entity.Users;

public class JobsRepository {
	public int insert(String name, String projectName,String userName , String starDate, String endDate) {
		Connection connection = MySqlConfig.getConnection();
		int count = 0;
		try {
			// excuted query
			PreparedStatement statement = connection.prepareCall("{call InsertJobWithDetails(?, ?, ?, ?, ?)}");
			statement.setString(1, name);
			statement.setString(2, starDate);
			statement.setString(3, endDate);
			statement.setString(4, projectName);
			statement.setString(5, userName);
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
		String query = "DELETE FROM Job j WHERE j.id = ?";
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
	public int update(String name, String projectName,String userName , String starDate, String endDate,int id) {
		int count = 0;
		String query = "CALL UpdateJobWithDetails(?,?,?,?,?,?)";
		Connection connection = MySqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareCall("{call UpdateJobWithDetails(?, ?, ?, ?, ?, ?)}");;
			statement.setString(2, name);
			statement.setString(3, starDate);
			statement.setString(4, endDate);
			statement.setString(5, userName);
			statement.setString(6, projectName);
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
	
	public List<Job> getAllJobById(int id){
		List<Job> listJob = new ArrayList<Job>();
		String query = "SELECT j.id ,j.name , p.name , u.fullName ,j.startDate ,j.endDate,s.name"
				+ "FROM Job j"
				+ "INNER JOIN Project_Users pu ON j.id_project = pu.id_project"
				+ "INNER JOIN Job_Status_Users jsu ON j.id = jsu.id_job"
				+ "INNER JOIN Users u  ON pu.id_user = u.id"
				+ "INNER JOIN Status s ON jsu.id_status = s.id"
				+ "INNER JOIN Project p ON j.id_project = p.id"
				+ "WHERE j.id = ?";

		Connection connection = MySqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(id, 1);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Job job = new Job();
				
				job.setId(resultSet.getInt("id"));// tên tham số trong SQL
				job.setName(resultSet.getString("name"));
				job.setStartDate(resultSet.getString("startDate"));
				job.setEndDate(resultSet.getString("endDate"));
				
				Users users = new Users();
				users.setFullname(resultSet.getString("fullname"));
				job.setUsers(users);
				
				Project project = new Project();
				project.setName(resultSet.getString("name"));
				job.setProject(project);
				
				Status status = new Status();
				status.setName(resultSet.getString("name"));
				job.setStatus(status);
				
				listJob.add(job);
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

		return listJob;
	}
	
	
	
	public List<Job> findAll() {
		List<Job> listJob = new ArrayList<Job>();
		String query = "SELECT j.id ,j.name , p.name , u.fullName ,j.startDate ,j.endDate,s.name\r\n"
				+ "				 FROM Job j\r\n"
				+ "				 INNER JOIN Project_Users pu ON j.id_project = pu.id_project\r\n"
				+ "				 INNER JOIN Job_Status_Users jsu ON j.id = jsu.id_job\r\n"
				+ "				 INNER JOIN Users u  ON pu.id_user = u.id\r\n"
				+ "				 INNER JOIN Status s ON jsu.id_status = s.id\r\n"
				+ "				 INNER JOIN Project p ON j.id_project = p.id\r\n";

		Connection connection = MySqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Job job = new Job();
				
				job.setId(resultSet.getInt("id"));// tên tham số trong SQL
				job.setName(resultSet.getString("name"));
				job.setStartDate(resultSet.getString("startDate"));
				job.setEndDate(resultSet.getString("endDate"));
				
				Users users = new Users();
				users.setFullname(resultSet.getString("fullname"));
				job.setUsers(users);
				
				Project project = new Project();
				project.setName(resultSet.getString("name"));
				job.setProject(project);
				
				Status status = new Status();
				status.setName(resultSet.getString("name"));
				job.setStatus(status);
				
				listJob.add(job);
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

		return listJob;
	}
}
