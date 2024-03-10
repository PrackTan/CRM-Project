package cm_project02.service;

import java.util.List;

import cm_project02.entity.Role;
import cm_project02.entity.Users;
import cm_project02.repository.RoleRepository;
import cm_project02.repository.UserRopository;

// dựa vào người dùng muốn biết gì trả ra kết quả đó
public class UserService {
	// khai bao Reponsitory
	private RoleRepository roleRepository = new RoleRepository();
	private UserRopository userRepository = new UserRopository();

	public List<Role> getAllRole() {
		return roleRepository.findAll();
	}

	// boolean là vì client muốn biết thành công thất bại
	public boolean insertUser(String fullname, String email, String pwd, String phone, int idRole) {
		// do insert trả ra int nên gán cho count
		int count = userRepository.insert(fullname, email, pwd, phone, idRole);
		return count > 0;
	}

	public List<Users> getAllUsers() {
		return userRepository.getAllUsers();
	}

	public List<Users> getAllUsersById(int id) {
		return userRepository.getAllUsersbyId(id);
	}

	// trả boolean cho client biết được hay không
	public boolean deleteById(int id) {
		int count = userRepository.deleteById(id);

		return count > 0;
	}

	public boolean updateUser(String fullname, String email, String pwd, String phone, int idRole, int id) {
		int count = userRepository.update(fullname, email, pwd, phone, idRole, id);
		return count > 0;
	}
}
