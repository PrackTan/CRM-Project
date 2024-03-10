package cm_project02.config;
//Cấu hình jdbc kết nối tới sever MySQL

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConfig {

	public static Connection getConnection() {
		try {
			// Khai báo Driver sử dụng cho jdbc tương ứng với CSDL cần kết nối
			Class.forName("com.mysql.cj.jdbc.Driver");
			// khai báo Driver sẽ mở kết nối tới CSDL nào
			return DriverManager.getConnection("jdbc:mysql://localhost:3307/layoutcrm?useUnicode=yes&characterEncoding=UTF-8", "root", "admin123");
		} catch (Exception e) {
			// TODO: handle exception
			// lỗi xảy ra sẽ chạy ra đây
			System.out.println("Lỗi kết nối database" + e.getLocalizedMessage());
			return null;
		}

	}
}