package cm_project02.service;

import java.util.List;

import cm_project02.entity.Role;
import cm_project02.repository.RoleRepository;

//SERVICE chứa những class chuyên đi xử lý logic cho CONTROLLER 
//Cách đặt tên: Giống với CONTROLLER : Ví dụ RoleController => RoleService
//Cách đặt tên: đặt tên hàm ứng với lại chức năng sẽ làm bên giao diện/bên CONTROLLER
public class RoleService {
//	Khởi tạo
	private RoleRepository roleRepository = new RoleRepository();

	public boolean addRole(String name, String description) {
		// ROLE trả ra int tạo biến count lưu lại
		int count = roleRepository.insert(name, description);
		return count > 0;
	}
	public boolean updateRole(String name, String description, int id) {
		int count = roleRepository.update(name, description, id);
		return count >0;
	}
	public boolean deleteRole(int id) {
		int count = roleRepository.delete(id);
		return count > 0;
	}

	public List<Role> getAllRole() {
		return roleRepository.findAll();
	}
	public List<Role> getAllRoleById(int id){
		return roleRepository.getAllRoleById(id);
	}
	
}
