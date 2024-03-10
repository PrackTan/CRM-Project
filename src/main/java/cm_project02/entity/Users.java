package cm_project02.entity;

public class Users {
//	entity : là nơi khai báo ra các class(đối tương) đặt tên và thuộc tinh
//	giống với tên bảng trong database

	private int id;
	private String email;
	private String password;
	private String firstname;
	private String lastname;
	private String username;
	private String fullname;
	private String phone;

	private Role role; // Nếu cột là khóa ngoại thì không khai báo biến mà sẽ chuyển thành đối tượng
						// của bản đc tham chiếu tới

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
